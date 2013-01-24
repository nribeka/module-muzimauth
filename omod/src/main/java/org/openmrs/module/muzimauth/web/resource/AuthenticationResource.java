/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.muzimauth.web.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.authentication.api.AuthenticationService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.resource.api.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.response.GenericRestException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

public class AuthenticationResource implements Resource {
    private final Log log = LogFactory.getLog(AuthenticationResource.class);

    /**
     * Gets the URI of the given instance of this resource. (If instance is null, this should return the
     * base URI for creating and searching the resource.)
     *
     * @param instance
     * @return
     */
    @Override
    public String getUri(final Object instance) {
        return null;
    }

    public SimpleObject generate(final String username, final String password, final RequestContext context) throws ResponseException {
        SimpleObject returnMap = new SimpleObject();
        try {
            Long duration = 60000L;
            AuthenticationService service = Context.getService(AuthenticationService.class);
            String token = service.authenticate(username, password, duration);
            returnMap.put("username", username);
            returnMap.put("token", token);
            returnMap.put("status", "OK");
        } catch (Exception e) {
            log.error("Authenticating user failed!", e);
        }
        return returnMap;
    }


}
