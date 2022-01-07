package dtos.OwnerDTOs;

import entities.Owner;

import java.util.List;
import java.util.Objects;

public class OwnerDTOs {
    private List<OwnerDTO> owners;


    public int getSize() {
        int counter = 0;
        for (OwnerDTO o : owners) {
            counter++;
        }
        return counter;
    }


    public OwnerDTOs(List<Owner> owners) {
        this.owners = OwnerDTO.getFromList(owners);
    }

    public List<OwnerDTO> getOwners() {
        return owners;
    }

    public void setOwners(List<OwnerDTO> owners) {
        this.owners = owners;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerDTOs ownerDTOs = (OwnerDTOs) o;
        return Objects.equals(owners, ownerDTOs.owners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owners);
    }

    @Override
    public String toString() {
        return "OwnerDTOs{" +
                "owners=" + owners +
                '}';
    }
}
