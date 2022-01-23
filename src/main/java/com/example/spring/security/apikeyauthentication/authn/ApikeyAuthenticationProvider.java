package com.example.spring.security.apikeyauthentication.authn;

import com.example.spring.security.apikeyauthentication.exceptions.InvalidApikeyException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ApikeyAuthenticationProvider implements AuthenticationProvider
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private Map<String, List<GrantedAuthority>> apikeyAuthorityMapping;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    public ApikeyAuthenticationProvider()
    {
        this.apikeyAuthorityMapping = new HashMap<>();
        this.apikeyAuthorityMapping.put("4711", List.of(new SimpleGrantedAuthority("ADMIN")));
        this.apikeyAuthorityMapping.put("4712", List.of(new SimpleGrantedAuthority("PRIVILEGE1"), new SimpleGrantedAuthority("PRIVILEGE2")));
        this.apikeyAuthorityMapping.put("4713", List.of(new SimpleGrantedAuthority("PRIVILEGE1")));
    }

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    private boolean validateApikey(String apikey)
    {
        return this.apikeyAuthorityMapping.keySet().stream().anyMatch(a -> a.equals(apikey));
    }

    // -------------------- [Public Methods] --------------------

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        // Get the API key authentication token.
        ApikeyAuthenticationToken apikeyAuthenticationToken = (ApikeyAuthenticationToken) authentication;
        String apikey = apikeyAuthenticationToken.getApikey();

        // Validate the API key.
        if (!this.validateApikey(apikey))
            throw new InvalidApikeyException("The API key is invalid.");

        // Get the API key's authorities (privileges).
        List<GrantedAuthority> grantedAuthorities = this.apikeyAuthorityMapping.get(apikey);

        // Internally sets the authenticated flag to true;
        ApikeyAuthenticationToken authenticatedToken = new ApikeyAuthenticationToken(apikey, grantedAuthorities);

        return authenticatedToken;
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return ApikeyAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
