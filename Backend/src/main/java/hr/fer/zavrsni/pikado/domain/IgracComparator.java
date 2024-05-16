package hr.fer.zavrsni.pikado.domain;

import java.util.Comparator;

public class IgracComparator implements Comparator<Igrac> {

    @Override
    public int compare(Igrac igrac1, Igrac igrac2) {
        // Compare based on the id field
        return Long.compare(igrac1.getId(), igrac2.getId());
    }
}
