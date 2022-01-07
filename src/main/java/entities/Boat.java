package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NamedQuery(name = "Boat.deleteAllRows", query = "DELETE from Boat")
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String brand;
    private String make;
    private String name;
    private String image;

    @ManyToMany
    List<Owner> ownerList;

    @ManyToOne
    Harbour harbour;


    public Boat() {
    }

    public Boat(Integer id, String brand, String make, String name, String image) {
        this.id = id;
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
    }

    public Boat(String brand, String make, String name, String image, List<Owner> ownerList) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
        this.ownerList = ownerList;
    }

    public Boat(String brand, String make, String name, String image) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
    }

    public Harbour getHarbour() {
        return harbour;
    }

    public void setHarbour(Harbour harbour) {
        this.harbour = harbour;
    }

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<Owner> ownerList) {
        this.ownerList = ownerList;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
