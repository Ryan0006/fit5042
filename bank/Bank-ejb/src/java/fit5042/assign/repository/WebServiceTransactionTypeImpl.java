/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.repository;

import fit5042.assign.interfaces.TransactionTypeManager;
import fit5042.assign.utility.TransactionType;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jerrychen
 */
@Stateless
public class WebServiceTransactionTypeImpl implements TransactionTypeManager {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:48272/bank-webservices/webresources";

    public WebServiceTransactionTypeImpl() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("transactionTypeServices");
    }

    @Override
    public boolean createTransactionType(String type) {
        Form form = new Form();
        form.param("type", type);
        Response response = webTarget.request(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        if (response.getStatus() == 409) {
            return false;
        }
        if (response.getStatus() == 201) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTransactionType(String type) {
        Response response = webTarget.queryParam("type", type).request().delete(Response.class);
        if (response.getStatus() == 204) {
            return false;
        }
        if (response.getStatus() == 200) {
            return true;
        }
        return false;
    }

    @Override
    public List<TransactionType> findAllTypes() {
        Response response = webTarget.request().get(Response.class);
        return response.readEntity(new GenericType<List<TransactionType>>() {
        });
    }

}
