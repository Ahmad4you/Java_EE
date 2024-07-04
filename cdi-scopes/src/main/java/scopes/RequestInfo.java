package scopes;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RequestInfo {
/**
 * Wird verwendet, um die Lebensdauer einer CDI-Bean auf eine einzelne HTTP-Anfrage zu beschränken.
 * @return
 */
    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    private String clientInfo = System.currentTimeMillis() + "";

}
