package pt.isel.vsdserver.security.models;


public class Session {

    private String apiKey;
    private long expirationDate;

    public Session(String apiKey, long expirationDate){
        this.apiKey = apiKey;
        this.expirationDate = expirationDate;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }
}
