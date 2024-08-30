package cr.ac.una.chatandmailapi;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import cr.ac.una.chatandmailapi.controller.JsonbContextResolver;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("resources")
public class JakartaRestConfiguration extends Application {
    
}
