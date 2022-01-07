package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoatDTOs.BoatDTO;
import dtos.OwnerDTOs.OwnerDTO;
import facades.BoatFacade;
import facades.OwnerFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/boat")
public class BoatResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final BoatFacade facade =  BoatFacade.getInstance(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, owner!";
    }

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createBoat(String boat) {
        BoatDTO boatDTO = gson.fromJson(boat, BoatDTO.class);
        BoatDTO boatDTONew = facade.create(boatDTO);

        return gson.toJson((boatDTONew));
    }


    @Path("/all")
    @GET
    @Produces("application/json")
    public String getAllBoats () {
        return gson.toJson(facade.getAllBoats());
    }


}