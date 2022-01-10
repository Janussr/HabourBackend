package entities;

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<Boat> boatList;

    public Owner() {
    }

    public Owner(String name, String address, int phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        //need this for US-3
        this.boatList = new ArrayList<>();
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

    //takes list of boats, adds a boat. takes list of owner, and adds "this?"(the class itself) this method is used in populate.
    public void addBoat(Boat boat) {
        if (boat != null) {
            this.boatList.add(boat);
            boat.getOwnerList().add(this);
        }
    }
}
