package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.OwnerDTOs.OwnerDTO;
import facades.OwnerFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/owner")
public class OwnerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final OwnerFacade facade =  OwnerFacade.getOwnerFacade(EMF);
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
    public String createOwner(String owner) {
        OwnerDTO ownerDTO = gson.fromJson(owner, OwnerDTO.class);
        OwnerDTO ownerDTONew = facade.create(ownerDTO);

        return gson.toJson((ownerDTONew));
    }

    @Path("/all")
    @GET
    @Produces("application/json")
    public String getAllOwners () {
        return gson.toJson(facade.getAllOwners());
    }


    @Path("/getownerofboat/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getOwnerOfBoat(@PathParam("id")int id){
    return gson.toJson(facade.getOwnersOfSpecificBoat(id));
    }
}