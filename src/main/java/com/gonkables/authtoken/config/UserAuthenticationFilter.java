package com.gonkables.authtoken.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonkables.authtoken.dto.CredentialDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserAuthenticationFilter implements Filter {

    private static final String LOGIN_PATH = "/auth/login";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // no implementar
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if(LOGIN_PATH.equals(httpRequest.getServletPath()) && HttpMethod.POST.matches(httpRequest.getMethod())){
            CredentialDto credentialDto = MAPPER.readValue(httpRequest.getInputStream(), CredentialDto.class);
            securityContext.setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            credentialDto.getLogin(), credentialDto.getPassword()));
        }else{
            String header = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);

            if (header != null) {
                String[] authElements = header.split(" ");

                if (authElements.length == 2
                        && "Bearer".equals(authElements[0])) {
                    securityContext.setAuthentication(new PreAuthenticatedAuthenticationToken(authElements[1], null));
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // no implementar
    }
}
