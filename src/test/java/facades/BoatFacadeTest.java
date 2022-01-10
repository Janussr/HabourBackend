package facades;

import static org.junit.jupiter.api.Assertions.*;

import dtos.BoatDTOs.BoatDTO;
import dtos.OwnerDTOs.OwnerDTO;
import entities.Boat;
import entities.Harbour;
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

    private static Boat b1, b2, b3;
    private static Harbour h1, h2;
    private static Owner o1;

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

        h1 = new Harbour("TestNameOne", "TestAddressOne", 50);
        h2 = new Harbour("TestNameTwo", "TestAddressTwo", 30);

        b1 = new Boat("TestBrandOne", "TestMakeOne", "TestNameOne", "TestImageOne", h1);
        b2 = new Boat("TestBrandTwo", "TestMakeTwo", "TestNameTwo", "TestImageTwo", h1);
        b3 = new Boat("TestBrandThree", "TestMakeThree", "TestNameThree", "TestImageThree", h2);

        o1 = new Owner("TestOwner","TestOwnersAddress",42424242);

        try {
            em.getTransaction().begin();
            em.createQuery("delete from Boat").executeUpdate();
            em.createQuery("delete from Owner").executeUpdate();
            em.createQuery("delete from Harbour").executeUpdate();
            em.persist(b1);
            em.persist(b2);
            em.persist(b3);
            em.persist(h1);
            em.persist(h2);
            em.persist(o1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void createBoatTest() {
//        BoatDTO b3 = facade.create(new BoatDTO("ThirdBoat","ThirdBoat","ThirdBoat","ThirdBoat"));

        long expected = 3;
        long actual = facade.getCount();

        assertEquals(expected, actual);
    }


    @Test
    public void deleteBoat(){
    facade.deleteBoat(b1.getId());

    assertEquals(2,facade.getAllBoats().getSize());
    }

    @Test
    public void getBoatById() {

    }

}