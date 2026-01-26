package com.erp.logistico.infrastructure.config;

import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class
FiltroValidation extends OncePerRequestFilter {
    private TokenConfigure tokenConfigure;
    public FiltroValidation( TokenConfigure tokenConfigure){
        this.tokenConfigure = tokenConfigure;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods",
                "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");

        // Se for uma preflight request, responde direto e não continua o filtro
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        String token = ValidaToken(request);
        if (token != null) {
            Map<String, Claim> claims = tokenConfigure.validaTokenAuth(token);
            String email = claims.get("sub").asString();
            List<String> permissoes = claims.get("permissoes").asList(String.class);
            String perfil = claims.get("perfil").asString();

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(
                    new SimpleGrantedAuthority("ROLE_" +perfil)
            );
            permissoes.forEach(p ->
                    authorities.add(new SimpleGrantedAuthority(p))
            );


            var auth = new UsernamePasswordAuthenticationToken(email, null,authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);

        }
        filterChain.doFilter(request,response);
    }
    private String ValidaToken(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth!=null){
            return auth.replace("Bearer","").trim();
        }
        return null;
    }

    private void enviarErroJson(HttpServletResponse response, int status, String msg) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\": \"" + msg + "\"}");
        response.getWriter().flush();
    }

}
