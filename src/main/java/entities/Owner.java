package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NamedQuery(name = "Owner.deleteAllRows", query = "DELETE from Owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private String address;
    private int phone;

    @ManyToMany(mappedBy = "ownerList", cascade = CascadeType.PERSIST)
    List<Boat> boatList;

    public Owner() {
    }

    public Owner(String name, String address, int phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Owner(Integer id, String name, String address, int phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Owner(String name, String address, int phone, List<Boat> boatList) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.boatList = boatList;
    }

    public List<Boat> getBoatList() {
        return boatList;
    }

    public void setBoatList(List<Boat> boatList) {
        this.boatList = boatList;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
