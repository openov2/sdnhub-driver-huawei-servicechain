/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
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

package org.openo.sdnhub.servicechaindriverservice.sbi;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.servicechaindriverservice.deviceconfig.DCDeviceConfig;
import org.openo.sdnhub.servicechaindriverservice.sbi.GatewayConfigurationAPI;

public class GatewayConfigurationAPITest {

    @Test(expected = ServiceException.class)
    public void testLoadConfiguration() throws ServiceException {
        DCDeviceConfig deviceCfg = new DCDeviceConfig();
        GatewayConfigurationAPI gwAPI = new GatewayConfigurationAPI(deviceCfg);
        gwAPI.loadConfiguration(null);
    }

    @Test(expected = ServiceException.class)
    public void testDeLoadConfiguration() throws ServiceException {
        DCDeviceConfig deviceCfg = new DCDeviceConfig();
        GatewayConfigurationAPI gwAPI = new GatewayConfigurationAPI(deviceCfg);
        gwAPI.deLoadConfiguration(null);
    }

    @Test(expected = ServiceException.class)
    public void testQueryVpnInterface() throws ServiceException {
        DCDeviceConfig deviceCfg = new DCDeviceConfig();
        GatewayConfigurationAPI gwAPI = new GatewayConfigurationAPI(deviceCfg);
        gwAPI.queryVpnInterface(null);
    }

}
