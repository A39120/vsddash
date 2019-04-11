package pt.isel.vsdserver.security.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import net.nuagenetworks.vspk.v5_0.VSDSession;
import org.springframework.util.MultiValueMap;
import pt.isel.vsdserver.security.exception.InvalidUserException;
import pt.isel.vsdserver.security.TokenHasher;

/**
 * Model class representing a Nuage user;
 * Serves only to build a session;
 */
public class NuageUser {


    private String password;
    private String username;
    private String organization;
    private String apiUrl;
    private String certificateContent;
    private String privateKeyContent;


    @JsonIgnore
    public VSDSession createSession() throws InvalidUserException {

        if(password == null && (certificateContent == null || privateKeyContent == null))
            throw new InvalidUserException("Invalid credentials specified.");

        if(password == null)
            return new VSDSession(username, organization, apiUrl, certificateContent, privateKeyContent);

        return new VSDSession(username, password, organization, apiUrl);
    }

    @JsonIgnore
    public String getToken(TokenHasher tokenHasher){
        String key = System.currentTimeMillis() + "" + this.username + this.organization + this.apiUrl;

        return tokenHasher.hash(key);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getCertificateContent() {
        return certificateContent;
    }

    public void setCertificateContent(String certificateContent) {
        this.certificateContent = certificateContent;
    }

    public String getPrivateKeyContent() {
        return privateKeyContent;
    }

    public void setPrivateKeyContent(String privateKeyContent) {
        this.privateKeyContent = privateKeyContent;
    }

    public static NuageUser parseFromEncoded(MultiValueMap valueMap){
        NuageUser nu = new NuageUser();
        nu.setUsername((String)valueMap.get("username"));
        nu.setApiUrl((String)valueMap.get("apiUrl"));
        nu.setOrganization((String)valueMap.get("organization"));
        nu.setCertificateContent((String)valueMap.get("certificateContent"));
        nu.setPrivateKeyContent((String)valueMap.get("privateKey"));
        nu.setPassword((String)valueMap.get("password"));

        return nu;
    }
}
