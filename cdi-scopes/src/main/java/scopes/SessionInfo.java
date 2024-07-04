package scopes;

import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

@SessionScoped
public class SessionInfo implements Serializable {
/**
 * @SessionScoped: Wird verwendet, um die Lebensdauer einer CDI-Bean auf eine HTTP-Sitzung zu beschränken.
 * @return
 */
    public String getSessionDetails() {
        return sessionDetails;
    }

    public void setSessionDetails(String sessionDetails) {
        this.sessionDetails = sessionDetails;
    }

    private String sessionDetails = "Created At " + System.currentTimeMillis();
}
