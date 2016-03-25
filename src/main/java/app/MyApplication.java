package app;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import resources.StatusResource;
import resources.ImageResource;

public class MyApplication extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public MyApplication() {
        register(RequestContextFilter.class);
        register(StatusResource.class);
        register(ImageResource.class);
    }
}
