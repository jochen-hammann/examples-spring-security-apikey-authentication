package com.example.spring.security.apikeyauthentication.authn;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class ApikeyAuthenticationToken extends AbstractAuthenticationToken
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private String apikey;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    public ApikeyAuthenticationToken(String apikey)
    {
        super(null);

        this.apikey = apikey;
        super.setAuthenticated(false);
    }

    public ApikeyAuthenticationToken(String apikey, Collection<? extends GrantedAuthority> authorities)
    {
        super(authorities);

        this.apikey = apikey;
        super.setAuthenticated(true);
    }

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    @Override
    public Object getCredentials()
    {
        return this.apikey;
    }

    @Override
    public Object getPrincipal()
    {
        return this.apikey;
    }

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

}
