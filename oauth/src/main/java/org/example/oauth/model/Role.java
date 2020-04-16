package org.example.oauth.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * enum Role
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
public enum Role implements GrantedAuthority {
    USER,
    ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
