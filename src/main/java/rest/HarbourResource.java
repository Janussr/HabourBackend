package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.HarbourFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/harbour")
public class HarbourResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final HarbourFacade facade =  HarbourFacade.getInstance(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAllBoatFromHarbour(@PathParam("id")int id){
        return gson.toJson(facade.getAllBoatsInSpecificHarbour(id));
    }


    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllHarbours(){
        return gson.toJson(facade.getAll());
    }
}