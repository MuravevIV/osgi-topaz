package com.ilyamur.osgi.topaz.servlet;

import com.ilyamur.osgi.topaz.service.HelloService;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(immediate = true)
public class HelloServlet extends OsgiHttpServlet {

    private static final String PATTERN = "/osgi-topaz/hello";

    private static final String NAME_PARAMETER = "name";
    private static final String NAME_PARAMETER_DEFAULT = "anonymous";

    @Activate
    public void activate() {
        super.activate(PATTERN);
    }

    @Deactivate
    public void deactivate() {
        super.deactivate();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(NAME_PARAMETER);
        if (name == null) {
            name = NAME_PARAMETER_DEFAULT;
        }
        HelloService helloService = getReference(HelloService.class);
        String greeting = helloService.getGreeting(name);
        response.getWriter().write(greeting);
    }
}
