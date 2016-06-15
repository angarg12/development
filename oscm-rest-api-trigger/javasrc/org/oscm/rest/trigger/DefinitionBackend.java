/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: May 4, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.rest.trigger;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.WebApplicationException;

import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.RestBackend.Get;
import org.oscm.rest.common.WebException;
import org.oscm.rest.external.exceptions.AuthorizationException;
import org.oscm.rest.external.exceptions.BadDataException;
import org.oscm.rest.external.exceptions.ConflictException;
import org.oscm.rest.external.exceptions.DataException;
import org.oscm.rest.external.exceptions.NotFoundException;
import org.oscm.rest.trigger.data.DefinitionRepresentation;
import org.oscm.rest.trigger.interfaces.TriggerDefinitionRest;
import org.oscm.rest.trigger.interfaces.TriggerDefinitionRestService;

/**
 * Backend class for the trigger definition resource.
 * 
 * @author miethaner
 */
@Stateless
public class DefinitionBackend {

    @EJB
    private TriggerDefinitionRestService service;

    public void setService(TriggerDefinitionRestService service) {
        this.service = service;
    }

    public RestBackend.Get<DefinitionRepresentation, TriggerParameters> getItem()
            throws WebApplicationException {

        return new RestBackend.Get<DefinitionRepresentation, TriggerParameters>() {

            @Override
            public DefinitionRepresentation get(TriggerParameters params)
                    throws WebApplicationException {

                TriggerDefinitionRest definition;
                try {
                    definition = service.getDefinition(params.getId());
                } catch (NotFoundException e) {
                    throw WebException.notFound().build();
                    // TODO add more info
                } catch (AuthorizationException e) {
                    throw WebException.forbidden().build();
                    // TODO add more info
                }

                return new DefinitionRepresentation(definition);
            }
        };
    }

    public Get<RepresentationCollection<DefinitionRepresentation>, TriggerParameters> getCollection()
            throws WebApplicationException {

        return new RestBackend.Get<RepresentationCollection<DefinitionRepresentation>, TriggerParameters>() {
            @Override
            public RepresentationCollection<DefinitionRepresentation> get(
                    TriggerParameters params) throws WebApplicationException {

                Collection<TriggerDefinitionRest> definitions = service
                        .getDefinitions();

                Collection<DefinitionRepresentation> representationList = new ArrayList<DefinitionRepresentation>();

                for (TriggerDefinitionRest d : definitions) {
                    representationList.add(new DefinitionRepresentation(d));
                }

                return new RepresentationCollection<DefinitionRepresentation>(
                        representationList);
            }
        };
    }

    public RestBackend.Post<DefinitionRepresentation, TriggerParameters> postCollection()
            throws WebApplicationException {

        return new RestBackend.Post<DefinitionRepresentation, TriggerParameters>() {

            @Override
            public Object post(DefinitionRepresentation content,
                    TriggerParameters params) throws WebApplicationException {

                try {
                    return service.createDefinition(content);
                } catch (BadDataException e) {
                    throw WebException.badRequest().build();
                    // TODO add more info
                } catch (ConflictException e) {
                    throw WebException.conflict().build();
                    // TODO add more info
                }
            }
        };
    }

    public RestBackend.Put<DefinitionRepresentation, TriggerParameters> putItem()
            throws WebApplicationException {

        return new RestBackend.Put<DefinitionRepresentation, TriggerParameters>() {

            @Override
            public void put(DefinitionRepresentation content,
                    TriggerParameters params) throws WebApplicationException {

                try {
                    service.updateDefinition(content);
                } catch (BadDataException e) {
                    throw WebException.badRequest().build();
                    // TODO add more info
                } catch (DataException e) {
                    throw WebException.internalServerError().build();
                    // TODO add more info
                } catch (ConflictException e) {
                    throw WebException.conflict().build();
                    // TODO add more info
                } catch (AuthorizationException e) {
                    throw WebException.forbidden().build();
                    // TODO add more info
                } catch (NotFoundException e) {
                    throw WebException.notFound().build();
                    // TODO add more info
                }
            }
        };
    }

    public RestBackend.Delete<TriggerParameters> deleteItem()
            throws WebApplicationException {

        return new RestBackend.Delete<TriggerParameters>() {

            @Override
            public void delete(TriggerParameters params)
                    throws WebApplicationException {

                try {
                    service.deleteDefinition(params.getId());
                } catch (ConflictException e) {
                    throw WebException.conflict().build();
                    // TODO add more info
                } catch (AuthorizationException e) {
                    throw WebException.forbidden().build();
                    // TODO add more info
                } catch (NotFoundException e) {
                    throw WebException.notFound().build();
                    // TODO add more info
                }
            }
        };
    }
}