package dtos.BoatDTOs;

import entities.Boat;

import java.util.List;
import java.util.Objects;

public class BoatDTOs {
    private List<BoatDTO> boats;

    public int getSize() {
        int counter = 0;
        for (BoatDTO b : boats) {
            counter++;
        }
        return counter;
    }

    //making a list of the dto class.
    public BoatDTOs(List<Boat> boats) {
        this.boats = BoatDTO.getFromList(boats);
    }

    public List<BoatDTO> getBoats() {
        return boats;
    }

    public void setBoats(List<BoatDTO> boats) {
        this.boats = boats;
    }


    //The three methods below are used to compare objects to do unit test and rest assurance test.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoatDTOs boatDTOs = (BoatDTOs) o;
        return Objects.equals(boats, boatDTOs.boats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boats);
    }

    @Override
    public String toString() {
        return "BoatDTOs{" +
                "boats=" + boats +
                '}';
    }
}
