import React from "react";

const Home = () => {

    const containerStyle = {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        backgroundImage: "url(https://media.istockphoto.com/id/168718452/photo/darts-on-target.jpg?s=170667a&w=0&k=20&c=2FyQkfVKo_H8HtbFNamHBj-5IgP8PEABI405VK7xev8=)",
        backgroundRepeat: 'no-repeat',
        backgroundSize: 'cover',
        margin: 0,
        padding: 0,
        height: '90vh',
        opacity: 0.9

    };
    return (
        <div className="content-container" style={containerStyle}>
        <div style={{ maxWidth: "700px", textAlign: "center", color: 'white', textShadow: '1px 1px 2px rgba(0, 0, 0, 0.5)',
        fontSize: '1.5rem'}}>
            Dobrodošli na stranicu na kojoj se možete prijaviti na pikado turnire koji se trenutno održavaju. Na stranici Turniri
            dostupni su oni koji se tek trebaju održati. Isto tako, na stranici Održani turniri nalaze se oni koji su
            održani i o čijim se rezultatima možete informirati.
        </div>
        </div>
    );
};

export default Home;