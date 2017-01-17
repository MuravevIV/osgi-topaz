package com.ilyamur.osgi.topaz.servlet;

import com.ilyamur.osgi.topaz.service.HelloService;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(immediate = true)
public class HelloServlet extends OsgiHttpServlet {

    @Reference
    private HelloService helloService;

    @Activate
    public void activate() {
        super.activate("/osgi-topaz/hello");
    }

    @Deactivate
    public void deactivate() {
        super.deactivate();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) {
            name = "anonymous";
        }
        String greeting = helloService.getGreeting(name);
        response.getWriter().write(greeting);
    }
}
