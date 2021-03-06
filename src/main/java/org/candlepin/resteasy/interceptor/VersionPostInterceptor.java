/**
 * Copyright (c) 2009 - 2012 Red Hat, Inc.
 *
 * This software is licensed to you under the GNU General Public License,
 * version 2 (GPLv2). There is NO WARRANTY for this software, express or
 * implied, including the implied warranties of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
 * along with this software; if not, see
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
 *
 * Red Hat trademarks are not licensed under GPLv2. No permission is
 * granted to use or replicate Red Hat trademarks that are incorporated
 * in this software or its documentation.
 */
package org.candlepin.resteasy.interceptor;

import org.candlepin.util.VersionUtil;
import org.jboss.resteasy.annotations.interception.Precedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;

import java.util.Map;

import javax.ws.rs.ext.Provider;

/**
 * VersionPostInterceptor
 */
@Provider
@ServerInterceptor
@Precedence("HEADER_DECORATOR")
public class VersionPostInterceptor implements PostProcessInterceptor {
    @Override
    public void postProcess(ServerResponse response) {
        Map<String, String> map = VersionUtil.getVersionMap();
        response.getMetadata().add(VersionUtil.VERSION_HEADER,
            map.get("version") + "-" + map.get("release"));
    }
}
