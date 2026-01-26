package com.erp.logistico.infrastructure.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Component("permissaoService")
public class PermissionConfig {
    public boolean hasPermission(Authentication auth,String... permission){
        return Arrays.stream(permission)
                .anyMatch(item->auth.getAuthorities().contains(new SimpleGrantedAuthority(item)));
    }
}
