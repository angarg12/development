/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: Jun 10, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.rest.trigger.unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.ws.rs.WebApplicationException;

import org.junit.Test;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.trigger.data.DefinitionRepresentation;

/**
 * Unit test for TriggerRepresentation
 * 
 * @author miethaner
 */
public class TriggerRepresentationTest {

    @Test
    public void testValidationPositive() throws Exception {

        DefinitionRepresentation trigger = new DefinitionRepresentation();
        trigger.setDescription("abc");
        trigger.setTargetURL("http://abc.de");

        trigger.validateContent();
    }

    @Test
    public void testValidationNegative() throws Exception {

        DefinitionRepresentation trigger = new DefinitionRepresentation();
        trigger.setDescription("<abc>");

        try {
            trigger.validateContent();
            fail();
        } catch (WebApplicationException e) {
            assertEquals(CommonParams.STATUS_BAD_REQUEST, e.getResponse()
                    .getStatus());
        }

        trigger = new DefinitionRepresentation();
        trigger.setTargetURL("<http://");

        try {
            trigger.validateContent();
            fail();
        } catch (WebApplicationException e) {
            assertEquals(CommonParams.STATUS_BAD_REQUEST, e.getResponse()
                    .getStatus());
        }
    }

}