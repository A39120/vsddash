package pt.isel.vsdserver.session;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class TokenGenerator {

    private Argon2 argon2 = Argon2Factory.create();


    public static String generate(String base){
        //argon2.hash(10, 6)
        return "";
    }
}
