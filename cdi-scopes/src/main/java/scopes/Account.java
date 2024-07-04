package scopes;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;

@ApplicationScoped
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * @ApplicationScoped: Wird verwendet, um die Lebensdauer einer CDI-Bean auf die gesamte Anwendung zu beschränken.
	 * 
	 * 
	 */
    private String appName = "My Application 1";

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
