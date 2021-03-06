package facades;

import dtos.OwnerDTOs.OwnerDTO;
import dtos.OwnerDTOs.OwnerDTOs;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class OwnerFacade {

    private static OwnerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private OwnerFacade() {}

    public static OwnerFacade getOwnerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OwnerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }



    //US-4
    public OwnerDTO create(OwnerDTO ownerDTO){
        EntityManager em = emf.createEntityManager();
        Owner owner = new Owner(ownerDTO.getName() , ownerDTO.getAddress(), ownerDTO.getPhone());
        try {
            em.getTransaction().begin();
            em.persist(owner);
            em.getTransaction().commit();
            return new OwnerDTO(owner);

        } finally {
            em.close();
        }
    }

    //US-1
    public OwnerDTOs getAllOwners (){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o", Owner.class);
            List<Owner> ownerList = query.getResultList();
            return new OwnerDTOs(ownerList);
        } finally {
            em.close();
        }
    }

    //US-3
    public OwnerDTOs getOwnersOfSpecificBoat(int boatId){
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Owner> query = em.createQuery("SELECT o from Owner o JOIN o.boatList b WHERE b.id =:boatId", Owner.class);
            query.setParameter("boatId", boatId);
            List<Owner> ownerList = query.getResultList();
            return new OwnerDTOs(ownerList);

        }finally {
            em.close();
        }
    }






    //Used for test (get number of ppl left in DB after delete test)
    public Long getCount() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(o.id) FROM Owner o", Long.class);
            long rows = query.getSingleResult();
            return rows;
        } finally {
            em.close();
        }
    }

}
