package dtos.BoatDTOs;

import dtos.Harbour.HarbourDTO;
import entities.Boat;
import entities.Harbour;

import java.util.List;
import java.util.stream.Collectors;

public class BoatDTO {
    private Integer id;
    private String brand;
    private String make;
    private String name;
    private String image;
    private HarbourDTO harbour;



    public BoatDTO(Boat boat) {
        this.id = boat.getId();
        this.brand = boat.getBrand();
        this.make = boat.getMake();
        this.name = boat.getName();
        this.image = boat.getImage();
        //Use this to get Harbour_Id, to display on the boat on the frontend
        this.harbour = new HarbourDTO(boat.getHarbour());
    }

    //USED FOR UNIT TESTS
    public BoatDTO(String brand, String make, String name, String image) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
    }

    //Used to make a list of dto, so we can use it in DTO's class.
    public static List<BoatDTO> getFromList(List<Boat> boats) {
        return boats.stream()
                .map(boat -> new BoatDTO(boat))
                .collect(Collectors.toList());
    }

    public HarbourDTO getHarbour() {
        return harbour;
    }

    public void setHarbour(HarbourDTO harbour) {
        this.harbour = harbour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BoatDTO{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", make='" + make + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
