package servlets;


import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergei on 25.04.2016.
 */
public class AllRequestsServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request);
        //pageVariables.put("message", "");

        Map<String, Object> pageInput = new HashMap<>();
        pageInput.put("inputParam", "");

        //response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageInput));

        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        Map<String, Object> pageInput = new HashMap<>();

        String temp = request.getParameter("inputParam");

        pageInput.put("inputParam", temp == null ? "" : temp);

        response.setContentType("text/html; charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageInput));
        response.setStatus(HttpServletResponse.SC_OK);

    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathinfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }

}
