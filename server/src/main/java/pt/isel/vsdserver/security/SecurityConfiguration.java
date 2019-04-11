package pt.isel.vsdserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SecurityConfiguration {


    private final TokenHasher tokenHasher = new TokenHasher();

    @Bean
    public TokenHasher getTokenHasher(){
        return tokenHasher;
    }
}
