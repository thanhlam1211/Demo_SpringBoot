package com.example.crud_with_datajpa_thymeleaf.Config;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Admin
 */
public class Utility {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
