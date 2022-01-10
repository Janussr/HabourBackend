package rest;

import dtos.BoatDTOs.BoatDTO;
import entities.Boat;
import entities.Harbour;
import entities.RenameMe;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class BoatResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Boat b1, b2, b3;
    private static Harbour h1, h2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        b1 = new Boat("TestBrandOne", "TestMakeOne", "TestNameOne", "TestImageOne");
        b2 = new Boat("TestBrandTwo", "TestMakeTwo", "TestNameTwo", "TestImageTwo");
        b3 = new Boat("TestBrandThree", "TestMakeThree", "TestNameThree", "TestImageThree");

        h1 = new Harbour("TestNameOne", "TestAddressOne", 50);
        h2 = new Harbour("TestNameTwo", "TestAddressTwo", 30);

        b1.setHarbour(h1);
        b2.setHarbour(h1);
        b3.setHarbour(h1);

        try {
            em.getTransaction().begin();
            em.createQuery("delete from Boat").executeUpdate();
            em.createQuery("delete from Harbour").executeUpdate();
            em.persist(b1);
            em.persist(b2);
            em.persist(b3);
            em.persist(h1);
            em.persist(h2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/boat").then().statusCode(200);
    }




    @Test
    public void testGetAllBoats() throws Exception {
        given()
                .contentType("application/json")
                .get("/boat/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    @Test
    public void testDelete() {
        given()
                .contentType("application/json")
                .pathParam("id", b2.getId())
                .delete("boat/{id}")
                .then()
                .assertThat()
                //statusCode(HttpStatus.OK_200.getStatusCode()); can also be used below
                .statusCode(200);

        List<BoatDTO> allBoats;

        allBoats = given()
                .contentType("application/json")
                .when()
                .get("/boat/all")
                .then()
                .extract().body().jsonPath().getList("boats", BoatDTO.class);

        BoatDTO b2DTO = new BoatDTO(b2);

        assertThat(allBoats, not(hasItem(b2DTO)));
    }


}



