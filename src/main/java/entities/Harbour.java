package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Harbour.deleteAllRows", query = "DELETE from Harbour")
public class Harbour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private String address;
    private int capacity;

    @OneToMany (mappedBy = "harbour", cascade = CascadeType.PERSIST)
    private List<Boat> boatList;


    public Harbour() {
    }

    public Harbour( String name, String address, int capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }



    public List<Boat> getBoatList() {
        return boatList;
    }

    public void setBoatList(List<Boat> boatList) {
        this.boatList = boatList;
    }

    public Harbour(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
