package facades;

import dtos.OwnerDTOs.OwnerDTO;
import entities.Owner;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OwnerFacadeTest {



    private static EntityManagerFactory emf;
    private static OwnerFacade facade;
    private static Owner o1, o2;

    public OwnerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = OwnerFacade.getOwnerFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        o1 = new Owner("damsbo vænge", "Janus", 42424242);
        o2 = new Owner("Lygnby", "Freddy", 99999999);

        try {
            em.getTransaction().begin();
            em.persist(o1);
            em.persist(o2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Owner.deleteAllRows").executeUpdate();
            em.getTransaction().commit();

        }finally {
            em.close();
        }

    }

    // TODO: Delete or change this method
    @Test
    public void createOwnerTest() throws Exception {
        OwnerDTO o3 = facade.create(new OwnerDTO("amager","andreas",11111111));

        long expected = 3;
        long actual = facade.getCount();

        assertEquals(expected, actual);
    }

    @Test
    public void getAllOwnersTest() throws Exception {
        long expected = 2;
        long actual = facade.getAllOwners().getSize();

        assertEquals(expected,actual);
    }

}