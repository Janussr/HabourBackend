package facades;

import static org.junit.jupiter.api.Assertions.*;

import dtos.BoatDTOs.BoatDTO;
import dtos.OwnerDTOs.OwnerDTO;
import entities.Boat;
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

class BoatFacadeTest {


    private static EntityManagerFactory emf;
    private static BoatFacade facade;
    private static Boat b1, b2;

    public BoatFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = BoatFacade.getInstance(emf);
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

        b1 = new Boat("FirstBoat","FirstBoat","FirstBoat","FirstBoat");
        b2 = new Boat("secondBoat","secondBoat","secondBoat","secondBoat");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Boat.deleteAllRows").executeUpdate();
            em.persist(b1);
            em.persist(b2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method
    @Test
    public void createOwnerTest() throws Exception {
        BoatDTO b3 = facade.create(new BoatDTO("ThirdBoat","ThirdBoat","ThirdBoat","ThirdBoat"));

        long expected = 3;
        long actual = facade.getCount();

        assertEquals(expected, actual);
    }


}