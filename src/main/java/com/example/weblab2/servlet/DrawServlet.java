package com.example.weblab2.servlet;

import com.example.weblab2.resContent.ResultTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author tsypk
 * @project web-lab-2
 */
@WebServlet(name = "drawServlet", value = "/draw-servlet")
public class DrawServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ResultTable resultTable = (ResultTable) session.getAttribute("resultTable");
        double r = getR(req);
        if (resultTable != null) {
            if (r > 2 && r < 5) {
                resultTable.setDisplayR(r);
                session.setAttribute("curR", String.valueOf(r));
            }
        } else {
            resultTable = new ResultTable();
        }
        session.setAttribute("resultTable", resultTable);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private double getR(HttpServletRequest request) {
        double param = Double.parseDouble(request.getParameter("r").replace(',', '.'));
        if (Math.abs(param - Math.round(param)) <= 1e-7)
            param = Math.round(param);
        return param;
    }
}
