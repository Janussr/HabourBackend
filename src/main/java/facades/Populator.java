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

import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
      // FacadeExample fe = FacadeExample.getFacadeExample(emf);
      // fe.create(new RenameMeDTO(new RenameMe("First 1", "Last 1")));
      // fe.create(new RenameMeDTO(new RenameMe("First 2", "Last 2")));
      // fe.create(new RenameMeDTO(new RenameMe("First 3", "Last 3")));

        OwnerFacade of = OwnerFacade.getOwnerFacade(emf);
        BoatFacade bf = BoatFacade.getInstance(emf);

        of.create((new OwnerDTO(new Owner("janus","kbh",42424242 ))));

        bf.create(new BoatDTO(new Boat("tesla","Elon","speedy","jpg")));


        Boat boat = new Boat("Gump", "Honda", "Shrimping Boat", "google.com");

        Harbour harbour = new Harbour(2);

        boat.setHarbour(harbour);
    }


    
    public static void main(String[] args) {
        populate();
    }
}
