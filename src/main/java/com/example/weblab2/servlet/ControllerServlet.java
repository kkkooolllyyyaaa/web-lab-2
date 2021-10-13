package com.example.weblab2.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author tsypk
 * @project lab2
 */
@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("check") != null) {

            String x = request.getParameter("x");
            String y = request.getParameter("y");
            String r = request.getParameter("r");

            if (isDoubles(x, y, r))
                request.getRequestDispatcher("/area-check-servlet").forward(request, response);
            else
                request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (request.getParameter("clear") != null && request.getParameter("clear").equals("clear")) {
            request.getRequestDispatcher("/clear-table-servlet").forward(request, response);
        } else if (request.getParameter("draw") != null && request.getParameter("draw").equals("draw")) {
            if (isDoubles(request.getParameter("r")))
                request.getRequestDispatcher("/draw-servlet").forward(request, response);
            else
                request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private boolean isDoubles(String... args) {
        try {
            for (String s : args) {
                if (s == null)
                    return false;
                Double.parseDouble(s.replace(',', '.'));
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
