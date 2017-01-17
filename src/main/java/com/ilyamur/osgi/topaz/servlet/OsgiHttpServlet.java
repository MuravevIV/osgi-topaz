package com.ilyamur.osgi.topaz.servlet;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

public abstract class OsgiHttpServlet extends HttpServlet {

    private ServiceRegistration serviceRegistration;

    /**
     * @see <a href="http://felix.apache.org/documentation/subprojects/apache-felix-http-service.html#using-the-osgi-http-whiteboard">
     * Using the OSGi Http Whiteboard
     * </a>
     */
    protected void activate(String pattern) {
        BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
        Hashtable<String, Object> properties = new Hashtable<>();
        properties.put("osgi.http.whiteboard.servlet.pattern", pattern);
        serviceRegistration = context.registerService(Servlet.class.getName(), this, properties);
    }

    protected void deactivate() {
        serviceRegistration.unregister();
    }
}
