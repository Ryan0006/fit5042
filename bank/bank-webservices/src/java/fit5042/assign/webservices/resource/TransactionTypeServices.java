/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.webservices.resource;

import fit5042.assign.jpa.JPATransactionType;
import fit5042.assign.webservices.entity.TransactionType;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jerrychen
 */
@Path("transactionTypeServices")
public class TransactionTypeServices {

    public TransactionTypeServices() {
    }

    @EJB
    JPATransactionType jPATransactionType;

    @GET
    public Response getAllTypes() {

        List<TransactionType> result = jPATransactionType.getAllTypes();
        return Response.ok(new GenericEntity<List<TransactionType>>(result) {
        }).build();
    }

//    @GET
//    public Response getType() {
//        
//        GenericEntity<TransactionType> type = new GenericEntity<TransactionType>(new TransactionType("bababa"), TransactionType.class);
//        return Response.ok().entity(type).build();
//    }
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response getType( @QueryParam("type") String type) {
//        return Response.ok(jPATransactionType.searchTypeByName(type).getType()).build();
//    }
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response createType(@FormParam("type") String type) {
        
        if (jPATransactionType.addType(type)) {
            return Response.status(201).build();
        }
        return Response.status(409).build();
    }

    @DELETE
    public Response deleteType(@QueryParam("type") String type) {
    
        if (jPATransactionType.removeType(type)) {
            return Response.status(200).build();
        }
        return Response.status(204).build();
    }
}
