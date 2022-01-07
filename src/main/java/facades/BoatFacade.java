package facades;

import dtos.BoatDTOs.BoatDTO;
import dtos.BoatDTOs.BoatDTOs;
import dtos.OwnerDTOs.OwnerDTO;
import dtos.OwnerDTOs.OwnerDTOs;
import entities.Boat;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
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

    public BoatDTO create(BoatDTO boatDTO){
        EntityManager em = emf.createEntityManager();
        Boat boat = new Boat(boatDTO.getBrand(), boatDTO.getImage(),  boatDTO.getMake(), boatDTO.getName());
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
