package facades;

import dtos.BoatDTOs.BoatDTO;
import dtos.BoatDTOs.BoatDTOs;
import dtos.Harbour.HarbourDTO;
import dtos.OwnerDTOs.OwnerDTO;
import dtos.OwnerDTOs.OwnerDTOs;
import entities.Boat;
import entities.Harbour;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

public class BoatFacade {
    private static BoatFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private BoatFacade() {}

    public static BoatFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BoatFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //US-4
    public BoatDTO create(BoatDTO boatDTO){
        EntityManager em = emf.createEntityManager();
        Boat boat = new Boat(boatDTO.getBrand(), boatDTO.getImage(),  boatDTO.getMake(), boatDTO.getName());


        //Adds the harbour to the boat, when the boat is created.
        TypedQuery<Harbour> query = em.createQuery("SELECT h FROM Harbour h WHERE h.id =:harbourId", Harbour.class);
        query.setParameter("harbourId", boatDTO.getHarbour().getId());
        Harbour harbour = query.getSingleResult();
        boat.setHarbour(harbour);

        try {
            em.getTransaction().begin();
            em.persist(boat);
            em.getTransaction().commit();
            return new BoatDTO(boat);

        } finally {
            em.close();
        }
    }


    public BoatDTOs getAllBoats (){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Boat> query = em.createQuery("SELECT b FROM Boat b", Boat.class);
            List<Boat> boatList = query.getResultList();
            return new BoatDTOs(boatList);
        } finally {
            em.close();
        }
    }

 

    //delete boat Solve us 7
    public BoatDTO deleteBoat(int id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Boat boat = em.find(Boat.class, id);
        if (boat == null) {
            throw new WebApplicationException("no boat matches the id");
        } else {
            try {
                em.getTransaction().begin();
                em.createNativeQuery("DELETE FROM BOAT_OWNER WHERE boatList_id = ?").setParameter(1, boat.getId()).executeUpdate();
                em.remove(boat);
                em.getTransaction().commit();

                return new BoatDTO(boat);
            } finally {
                em.close();
            }
        }
    }

    // US - 5 connect boat to harbour
    public BoatDTO connectBoatToHarbour (int boatID, HarbourDTO harbourDTO) {
        EntityManager em = emf.createEntityManager();

        Boat boat = em.find(Boat.class, boatID);
        Harbour harbour = em.find(Harbour.class, harbourDTO.getId());
        boat.setHarbour(harbour);

        try {

           em.getTransaction().begin();
           em.merge(boat);
           em.getTransaction().commit();

            return new BoatDTO(boat);
        } finally {
            em.close();
        }
    }


    //Used for test (get number of ppl left in DB after delete test)
    public Long getCount() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(b.id) FROM Boat b", Long.class);
            long rows = query.getSingleResult();
            return rows;
        } finally {
            em.close();
        }
    }


}
