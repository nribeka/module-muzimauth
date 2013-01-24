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
package org.openmrs.module.muzimauth.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.api.context.Context;
import org.openmrs.module.muzimauth.web.resource.AuthenticationResource;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestUtil;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseCrudController;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/muzimauth/authentication")
public class AuthenticationController extends BaseRestController {

    protected AuthenticationResource getResource() {
        return Context.getService(RestService.class).getResource(AuthenticationResource.class);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"username", "password"})
    @ResponseBody
    public SimpleObject retrieve(final @RequestParam("username") String username,
                                 final @RequestParam("password") String password,
                                 final HttpServletRequest request)
            throws ResponseException {
        RequestContext context = RestUtil.getRequestContext(request);
        AuthenticationResource resource = getResource();
        return resource.generate(username, password, context);
    }

}
