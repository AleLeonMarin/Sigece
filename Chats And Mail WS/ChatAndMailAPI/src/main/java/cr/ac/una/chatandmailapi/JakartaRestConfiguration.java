package cr.ac.una.chatandmailapi;


import cr.ac.una.chatandmailapi.controller.JsonbContextResolver;


import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.ws.rs.ApplicationPath;
/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("ws")// TODO
public class JakartaRestConfiguration extends ResourceConfig {
public JakartaRestConfiguration() {
        super();
        packages("cr.ac.una.chatandmailapi.controller");
        packages("cr.ac.una.chatandmailapi.controller", "io.swagger.v3.jaxrs2.integration.resources");
        registerCustomProviders();
    }
private void registerCustomProviders() {
        Set<String> providerClassNames = new HashSet() {
            {
                add(JsonbContextResolver.class.getName());
                //add(ConstraintViolationExceptionMapper.class.getName());
                //add(SecurityFilter.class.getName());
            }
        };
     
        Map<String, Object> properties = new HashMap() {
            {
                put("jersey.config.server.provider.classnames", providerClassNames.stream().collect(Collectors.joining(",")));
            }
        };
     
        addProperties(Collections.unmodifiableMap(properties));
    }
}


