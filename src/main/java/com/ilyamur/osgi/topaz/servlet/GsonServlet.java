package com.ilyamur.osgi.topaz.servlet;

import com.google.gson.Gson;
import com.ilyamur.osgi.topaz.service.HelloService;
import org.apache.felix.scr.annotations.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to demonstrate successful third-party library embedding.
 */
@Component(immediate = true)
@Service(Servlet.class)
public class GsonServlet extends OsgiHttpServlet {

    @Reference
    private HelloService helloService;

    @Activate
    public void activate() {
        super.activate("/osgi-topaz/gson");
    }

    @Deactivate
    public void deactivate() {
        super.deactivate();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write((new Gson()).toString());
    }
}
