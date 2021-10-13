package com.example.weblab2.servlet;

import com.example.weblab2.resContent.ResultTable;

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
@WebServlet(name = "clearTableServlet", value = "clear-table-servlet")
public class ClearTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultTable resultTable = (ResultTable) request.getSession().getAttribute("resultTable");
        if (resultTable == null)
            resultTable = new ResultTable();
        resultTable.clearTable();
        request.getSession().setAttribute("curR", null);
        request.getSession().setAttribute("resultTable", resultTable);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
