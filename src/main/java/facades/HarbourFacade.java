package facades;

import dtos.BoatDTOs.BoatDTOs;
import dtos.Harbour.HarbourDTO;
import dtos.Harbour.HarbourDTOs;
import entities.Boat;
import entities.Harbour;
import entities.Role;

import javax.enterprise.inject.Typed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class HarbourFacade {
    private static HarbourFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HarbourFacade() {
    }

    public static HarbourFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HarbourFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BoatDTOs getAllBoatsInSpecificHarbour( int id) {
        EntityManager em = emf.createEntityManager();
        try {

            TypedQuery<Boat> query = em.createQuery("SELECT b from Boat b JOIN b.harbour h WHERE h.id =:id ", Boat.class);
            query.setParameter("id", id);

            List<Boat> boats = query.getResultList();
            return new BoatDTOs(boats);
        } finally {
            em.close();
        }
    }

    public HarbourDTOs getAll () {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Harbour> query = em.createQuery("SELECT h FROM Harbour h",Harbour.class);
            List<Harbour> harbourList = query.getResultList();
            return new HarbourDTOs(harbourList);
        }finally {
            em.close();
        }
    }

}
