/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package servlets;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.annotation.security.DeclareRoles;


/**
 * This is a simple example of an HTTP Servlet that can only be accessed
 * by an authenticated user.  It responds to the GET
 * method of the HTTP protocol.
 */
@DeclareRoles("MANAGER")
public class GreetingServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setBufferSize(8192);

        PrintWriter out = response.getWriter();

        // then write the data of the response
        out.println("<html>" + "<head><title>Hello</title></head>");

        // then write the data of the response
        out.println(
                "<body  bgcolor=\"#ffffff\">"
                + "<img src=\"duke.waving.gif\" alt=\"Duke waving\">"
                + "<h2>Hello, my name is Duke. What's yours?</h2>"
                + "<form method=\"get\">"
                + "<input type=\"text\" name=\"username\" size=\"25\">"
                + "<p></p>" + "<input type=\"submit\" value=\"Submit\">"
                + "<input type=\"reset\" value=\"Reset\">" + "</form>");

        String username = request.getParameter("username");

        if ((username != null) && (username.length() > 0)) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/response");
            if (dispatcher != null) {
                dispatcher.include(request, response);
            }
        }

        out.println("</body></html>");
        out.close();
    }

    public String getServletInfo() {
        return "The Hello servlet says hello.";
    }
}
