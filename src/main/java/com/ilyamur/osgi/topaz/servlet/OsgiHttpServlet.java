package com.ilyamur.osgi.topaz.servlet;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

public abstract class OsgiHttpServlet extends HttpServlet {

    private ServiceRegistration registration;

    /**
     * @see <a href="http://felix.apache.org/documentation/subprojects/apache-felix-http-service.html#using-the-osgi-http-whiteboard">
     * Using the OSGi Http Whiteboard
     * </a>
     */
    public void activate(String pattern) {
        BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
        Hashtable properties = new Hashtable();
        properties.put("osgi.http.whiteboard.servlet.pattern", pattern);
        this.registration = context.registerService(Servlet.class.getName(), this, properties);
    }

    public void deactivate() {
        this.registration.unregister();
    }

    protected <T> T getReference(Class<T> c) {
        BundleContext context = FrameworkUtil.getBundle(c).getBundleContext();
        ServiceReference<T> serviceReference = context.getServiceReference(c);
        if (serviceReference != null) {
            return context.getService(serviceReference);
        } else {
            throw new IllegalStateException("Service inaccessible: " + c.getCanonicalName());
        }
    }
}
