package com.example.weblab2.servlet;

import com.example.weblab2.resContent.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author tsypk
 * @project lab2
 */
@WebServlet(name = "areaCheckServlet", value = "/area-check-servlet")
public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TimeHandler timeHandler = new TimeHandler();
        timeHandler.START();
        HttpSession session = request.getSession(true);
        String xParam, yParam, rParam;
        xParam = request.getParameter("x");
        yParam = request.getParameter("y");
        rParam = request.getParameter("r");
        if (isDoubles(xParam, yParam, rParam)) {
            double x = Double.parseDouble(xParam.replace(',', '.'));
            double y = Double.parseDouble(yParam.replace(',', '.'));
            double r = Double.parseDouble(rParam.replace(',', '.'));
            ResultTable resultTable = getHandled(new Point(x, y, r), session, timeHandler, request);
            session.setAttribute("resultTable", resultTable);
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private ResultTable getHandled(Point point, HttpSession session, TimeHandler timeHandler, HttpServletRequest request) {
        PointService pointService = new PointService(point);
        ResultTable resultTable = (ResultTable) session.getAttribute("resultTable");
        if (resultTable == null)
            resultTable = new ResultTable();
        pointService.handle();
        Result result = new Result(point.getX(), point.getY(), point.getR(), pointService.isHit());
        double r = point.getR();
        resultTable.setDisplayR(r);
        session.setAttribute("curR", String.valueOf(r));
        if (request.getParameter("check").equals("dotCheck"))
            result.setDot(true);
        timeHandler.FINISH();
        result.setExcTime(timeHandler.getExcTime() + " ms");
        result.setCurTime(timeHandler.getCurTime());
        resultTable.addResult(result);
        return resultTable;
    }

    private boolean isDoubles(String... args) throws NumberFormatException {
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
