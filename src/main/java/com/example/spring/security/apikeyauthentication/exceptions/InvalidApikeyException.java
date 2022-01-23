package com.example.spring.security.apikeyauthentication.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidApikeyException extends AuthenticationException
{
    public InvalidApikeyException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    public InvalidApikeyException(String msg)
    {
        super(msg);
    }
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    // -------------------- [Public Fields] --------------------

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Private Construction / Destruction] --------------------

    // -------------------- [Public Construction / Destruction] --------------------

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

}
