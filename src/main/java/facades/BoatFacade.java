package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class BoatFacade {
    private static BoatFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private BoatFacade() {}

    public static BoatFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BoatFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


}
