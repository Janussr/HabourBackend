package facades;

import dtos.OwnerDTO;
import dtos.RenameMeDTO;
import entities.Owner;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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
