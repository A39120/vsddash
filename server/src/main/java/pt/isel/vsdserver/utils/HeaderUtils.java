package pt.isel.vsdserver.utils;

import org.springframework.data.util.Pair;

import java.util.Base64;

public class HeaderUtils {

    public static Pair<String, String> getAuthorizationHeader(String authorization){
        byte[] decodedBytes = Base64.getDecoder().decode(authorization);
        String decodedString = new String(decodedBytes);

        String[] str = decodedString.split(":");

        if(str.length != 2 )
            throw new IllegalArgumentException();

        return new Pair<>(str[0], str[1]);
    }
}
