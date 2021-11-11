package org.elsys.ip.chronometer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MyServlet extends HttpServlet {
    Chronometer chronometer = new Chronometer();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo() == null ? "" : req.getPathInfo();
        List<String> collect = Arrays.stream(path.split("/")).filter(s -> !s.equals("")).collect(Collectors.toList());

        if (collect.size() != 2) {
            resp.setStatus(400);
            return;
        }

        String id = collect.get(1);
        String result = chronometer.stopWatch(id);
        if (result == null){
            resp.setStatus(404);
            return;
        }
        resp.getWriter().print(result);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo() == null ? "" : req.getPathInfo();
        List<String> collect = Arrays.stream(path.split("/")).filter(s -> !s.equals("")).collect(Collectors.toList());
        if (collect.size() != 2 || !collect.get(1).equalsIgnoreCase("start")) {
            resp.setStatus(404);
            return;
        }

        String id = chronometer.start();
        resp.getWriter().println(id);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo() == null ? "" : req.getPathInfo();
        List<String> collect = Arrays.stream(path.split("/")).filter(s -> !s.equals("")).collect(Collectors.toList());

        if (collect.size() != 3 && !collect.get(2).equals("lap")) {
            resp.setStatus(400);
            return;
        }
        String id = collect.get(1);
        String result = chronometer.startLap(id);
        if (result == null){
            resp.setStatus(404);
            return;
        }
        resp.getWriter().print(result);
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo() == null ? "" : req.getPathInfo();
        List<String> collect = Arrays.stream(path.split("/")).filter(s -> !s.equals("")).collect(Collectors.toList());

        if (collect.size() != 2) {
            resp.setStatus(400);
            return;
        }
        String id = collect.get(1);
        String result = chronometer.stop(id);
        if (result == null){
            resp.setStatus(404);
            return;
        }
        resp.getWriter().print(result);
        resp.setStatus(200);
    }
}
