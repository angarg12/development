/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: May 20, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.rest.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.core.util.Base64;
import com.sun.web.security.WebProgrammaticLoginImpl;

/**
 * Servlet filter for programmatic Glassfish authentication via basic auth
 * 
 * @author miethaner
 */
public class BasicAuthFilter implements Filter {

    private WebProgrammaticLoginImpl programmaticLogin;

    public void setProgrammaticLogin(WebProgrammaticLoginImpl programmaticLogin) {
        this.programmaticLogin = programmaticLogin;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        programmaticLogin = new WebProgrammaticLoginImpl();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;

        String header = rq.getHeader(CommonParams.HEADER_AUTH);
        if (header != null && !header.isEmpty()
                && header.startsWith(CommonParams.BASIC_AUTH_PREFIX)) {

            String encodedUsrPwd = header.replace(
                    CommonParams.BASIC_AUTH_PREFIX, "");
            String userPwd = Base64.base64Decode(encodedUsrPwd);
            String[] split = userPwd
                    .split(CommonParams.BASIC_AUTH_SEPARATOR, 2);

            programmaticLogin.login(split[0], split[1].toCharArray(),
                    CommonParams.REALM, rq, rs);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}