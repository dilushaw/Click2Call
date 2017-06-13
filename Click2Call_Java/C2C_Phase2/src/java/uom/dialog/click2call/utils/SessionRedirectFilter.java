/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All
 * Rights Reserved. This software is the proprietary information of Dialog-University of Moratuwa
 * Mobile Communications Research Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the
 * software as it sees fit.
 *
 */
package uom.dialog.click2call.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * SessionRedirectFilter.java (UTF-8) Jun 26, 2013, 3:00:44 PM
 *
 * @author Dewmini
 */
public class SessionRedirectFilter implements Filter {

    private ArrayList<String> avoidList;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        avoidList = new ArrayList<String>();
        avoidList.add("/login.htm");
        avoidList.add("/home.htm");
        avoidList.add("/logout.htm");
        avoidList.add("/sessionExipired.htm");
        avoidList.add("/Click2Call.htm");
        avoidList.add("/redirect.jsp");
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String url = httpRequest.getServletPath();
        //System.out.println("-------------check session---------- ::::::::::: " + session != null ? session.getId():"**session is null**");

        String loggedUserName = (session != null) ? (String) session.getAttribute(Constants.USER_NAME) : null;
        String loginURL = httpRequest.getContextPath() + "/login.htm";
        System.out.println("loginURL : " + loginURL);
        System.out.println("httpRequest.getRequestURI() : " + httpRequest.getRequestURI());

        if (!avoidList.contains(url)) {
            if (session == null) {
                System.out.println("inside SessionRedirectFilter ----- session=null");
                httpResponse.sendRedirect("login.htm");
                return;
            } else if (session.getAttribute("userName") == null) {
                System.out.println("inside SessionRedirectFilter ++++ session not null, but userName=null");
                httpResponse.sendRedirect("login.htm");
                return;
            }

        }
        chain.doFilter(httpRequest, httpResponse);


    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
