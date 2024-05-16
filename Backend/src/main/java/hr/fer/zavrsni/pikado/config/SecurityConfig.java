package hr.fer.zavrsni.pikado.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.beans.BeanProperty;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("*"));
                    corsConfiguration.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setExposedHeaders(List.of(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE));
                    return corsConfiguration;
                }))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/odrzani", "/odrzani/**", "/turniri", "/vrste",
                            "/organizatori", "/novi", "/objekti", "/igraci/turnir/**",
                            "/sviTurniri", "/uredi", "/uredi/**", "/igraci", "/pobjednik", "/pobjednik/**",
                            "/drzave", "/turniri/**", "/sviTurniri", "/pobjedniciTablica",
                            "/igrac/**", "/turnirOmjeri", "/turniriPoMjesecima").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/novi", "/pobjednik/**", "/zahtjev/**", "/postojeci/**").permitAll();
                    auth.requestMatchers(HttpMethod.PATCH, "/uredi/**").permitAll();
                    auth.requestMatchers(HttpMethod.DELETE, "/obrisi/**").permitAll();
                    auth.requestMatchers("/welcome").authenticated();
                }).httpBasic(Customizer.withDefaults())
                .build();

    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("*******")
                .password(passwordEncoder().encode("*****"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}
