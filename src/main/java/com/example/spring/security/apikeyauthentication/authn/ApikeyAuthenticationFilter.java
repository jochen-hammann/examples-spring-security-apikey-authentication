package com.example.spring.security.apikeyauthentication.authn;

import com.example.spring.security.apikeyauthentication.constants.ConstHttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApikeyAuthenticationFilter extends OncePerRequestFilter
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Protected Methods] --------------------

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException
    {
        // Get the API key header from the request.
        String apikey = httpServletRequest.getHeader(ConstHttpHeaders.APIKEY_HEADER);

        // Verify, if an API key header is specified.
        if (apikey == null)
            throw new SecurityException("An API key header is missing.");

        // Create the authentication token.
        ApikeyAuthenticationToken apikeyAuthenticationToken = new ApikeyAuthenticationToken(apikey);

        // Let Spring know about the authentication token.
        SecurityContextHolder.getContext().setAuthentication(apikeyAuthenticationToken);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // -------------------- [Public Methods] --------------------

}
