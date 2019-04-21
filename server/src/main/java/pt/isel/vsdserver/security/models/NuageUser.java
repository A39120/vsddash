package pt.isel.vsdserver.security.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.nuagenetworks.bambou.RestException;
import net.nuagenetworks.bambou.annotation.RestEntity;
import net.nuagenetworks.vspk.v5_0.VSDSession;
import org.springframework.util.MultiValueMap;
import pt.isel.vsdserver.security.exception.InvalidUserException;
import pt.isel.vsdserver.security.TokenHasher;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

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
    public VSDSession createSession() throws InvalidUserException, RestException {

        if(password == null && (certificateContent == null || privateKeyContent == null))
            throw new InvalidUserException("Invalid credentials specified.");

        VSDSession session;
        if(password == null)
            session =  new VSDSession(username, organization, apiUrl, certificateContent, privateKeyContent);
        else
            session = new VSDSession(username, password, organization, apiUrl);

        // Ping and check if the session actually exists
        session.start();
        session.reset();

        return session;
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

    @JsonIgnore
    public static NuageUser parseFromEncoded(MultiValueMap<String, String> valueMap) throws InvalidUserException{
        NuageUser nu = new NuageUser();

        try {
            for(String key: valueMap.keySet()){
                String capitalizedKey = key.substring(0, 1).toUpperCase() + key.substring(1);
                Method setter = nu.getClass().getMethod("set" + capitalizedKey, String.class);
                setter.invoke(nu, (String) valueMap.get(key).get(0));
            }

        } catch (Exception ex) {
            throw new InvalidUserException();
        }

        return nu;
    }
}
