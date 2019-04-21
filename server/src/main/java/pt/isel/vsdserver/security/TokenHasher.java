package pt.isel.vsdserver.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Helper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class TokenHasher {

    @Value("${argon2.maxmilliseconds}")
    private long hashMilliseconds = 1000;

    @Value("${argon2.maxmemory}")
    private int hashMemory = 65536;

    @Value("${argon2.parallelism}")
    private int parallelism = 1;

    private final Argon2 argon2 = Argon2Factory.create();
    private final int iterations = Argon2Helper.findIterations(argon2, hashMilliseconds, hashMemory, parallelism);

    /**
     * This hashes the token passed to it
     * @param token the token to be hashed
     * @return the hashed string
     */
    public String hash(String token){
        return argon2.hash(iterations, hashMemory, parallelism, token);
    }
}
