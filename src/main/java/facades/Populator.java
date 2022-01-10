/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.BoatDTOs.BoatDTO;
import dtos.OwnerDTOs.OwnerDTO;
import entities.Boat;
import entities.Harbour;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();


        OwnerFacade of = OwnerFacade.getOwnerFacade(emf);
        BoatFacade bf = BoatFacade.getInstance(emf);

        of.create((new OwnerDTO(new Owner("janus","kbh",42424242 ))));

       // bf.create(new BoatDTO(new Boat("tesla","Elon","speedy","jpg")));





        //Persist object, without the create method in the facade
        Owner owner1 = new Owner("janus", "kbh", 424242424);
        Boat boat1 = new Boat("Gump", "Honda", "Shrimping Boat", "google.com");
        Boat boat2 = new Boat("tesla","Elon","speedy","jpg");
        Harbour harbour1 = new Harbour("Nordhavn","nordhavngade 5",10);
        Harbour harbour2 = new Harbour("ratchet","The barrens",10);
        Harbour harbour3 = new Harbour("Booty Bay","Strangelthorn",10);

        //TODO: Hvordan forbinder jeg flere både til samme harbour?
        //setter harbour id på boat. (forbinder en boat på en harbour)
        boat1.setHarbour(harbour1);
        boat2.setHarbour(harbour1);

        //Da det er en liste, skal jeg bruge min addBoat metode, i forhold til hvis det var et object kan man bruge getters/setters
        owner1.addBoat(boat1);

        em.getTransaction().begin();
        em.persist(owner1);
        em.persist(harbour1);
        em.persist(harbour2);
        em.persist(harbour3);
        em.persist(boat1);
        em.persist(boat2);
        em.getTransaction().commit();


    }


    
    public static void main(String[] args) {
        populate();
    }
}
