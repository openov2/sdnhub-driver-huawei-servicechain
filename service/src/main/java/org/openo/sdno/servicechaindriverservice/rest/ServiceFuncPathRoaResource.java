/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.servicechaindriverservice.rest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.util.RestUtils;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.consts.HttpCode;
import org.openo.sdno.overlayvpn.util.check.UuidUtil;
import org.openo.sdno.servicechaindriverservice.inf.IServiceFuncPathService;
import org.openo.sdno.servicechaindriverservice.nbimodel.ServiceChainPath;
import org.openo.sdno.servicechaindriverservice.nbimodel.ServiceFunctionPath;
import org.openo.sdno.servicechaindriverservice.util.CheckServiceFuncPathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Restful interface class for service function paths.<br>
 * 
 * @author
 * @version SDNO 0.5 2016-08-19
 */
@Service
@Path("/sbi-servicechain/v1/paths")
public class ServiceFuncPathRoaResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFuncPathRoaResource.class);

    private static final String SERVICE_FUNCTION_PATH_KEY = "serviceFunctionPath";

    private static final String SERVICE_CHAIN_PATH_KEY = "serviceChainPath";

    @Resource
    private IServiceFuncPathService service;

    public void setService(IServiceFuncPathService service) {
        this.service = service;
    }

    /**
     * Create Service Function Path.<br>
     * 
     * @param request HttpServletRequest Object
     * @param response HttpServletResponse Object
     * @return ServiceChainPath created
     * @throws ServiceException when create ServiceFunctionPath failed
     * @since SDNO 0.5
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, ServiceChainPath> create(@Context HttpServletRequest request,
            @Context HttpServletResponse response) throws ServiceException {
        String requestBody = RestUtils.getRequestBody(request);
        if(StringUtils.isEmpty(requestBody)) {
            LOGGER.error("Request body is null!!");
            throw new ServiceException("Request body is null");
        }

        Map<String, ServiceFunctionPath> pathDataMap =
                JsonUtil.fromJson(requestBody, new TypeReference<Map<String, ServiceFunctionPath>>() {});
        if(null == pathDataMap || null == pathDataMap.get(SERVICE_FUNCTION_PATH_KEY)) {
            LOGGER.error("no service function path data in request!!");
            throw new ServiceException("no service function path data in request!!");
        }

        ServiceFunctionPath pathData = pathDataMap.get(SERVICE_FUNCTION_PATH_KEY);

        CheckServiceFuncPathUtil.check(pathData);

        ServiceFunctionPath newPathData = service.create(pathData);

        response.setStatus(HttpCode.CREATE_OK);

        Map<String, ServiceChainPath> resultRsp = new HashMap<String, ServiceChainPath>();
        ServiceChainPath serviceChainPath = new ServiceChainPath();
        serviceChainPath.setId(newPathData.getId());
        serviceChainPath.setCreateTime(System.currentTimeMillis());
        resultRsp.put(SERVICE_CHAIN_PATH_KEY, serviceChainPath);

        return resultRsp;
    }

    /**
     * Delete Service Function Path.<br>
     * 
     * @param request HttpServletRequest Object
     * @param response HttpServletResponse Object
     * @param uuid ServiceFunctionPath uuid
     * @return ServiceFunctionPath Object deleted
     * @throws ServiceException when delete ServiceFunctionPath failed
     * @since SDNO 0.5
     */
    @DELETE
    @Path("/{pathId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@Context HttpServletRequest request, @Context HttpServletResponse response,
            @PathParam("pathId") String uuid) throws ServiceException {
        UuidUtil.validate(uuid);
        service.delete(uuid);
    }
}
