package br.dev.pedrosaraiva.security.security;

import java.io.IOException;
import java.util.Base64;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.dev.pedrosaraiva.security.user.model.User;
import br.dev.pedrosaraiva.security.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomBasicAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BASIC = "Basic";
    private UserRepository _Repository ;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (isBasicAuthentication(request)) {
            String [] credentials = decoderBase64(getHader(request).replace(BASIC,""))
            .split(":");

            String username = credentials[0];
            String password = credentials[1];

            User user = _Repository.findByUsername(username);
            
            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Usuario não existe");
                return;
            }

            boolean valid = checkPassword(user.getPassword(), password);

            if (!valid) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Senha não está correta");
            }

            setAuthentication(user);
        }

        

    }

    private void setAuthentication(User user) {
       Authentication authentication = createAuthnticationToken(user);
       SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Authentication createAuthnticationToken(User user) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
    }

    private boolean checkPassword(String userpassword, String loginpassword) {
        return passwordEncoder().matches(loginpassword, userpassword);
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private boolean isBasicAuthentication(HttpServletRequest request) {
        String header = getHader(request);
        return header !=null &&header.startsWith(BASIC);
    }

    private String getHader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }

    private String decoderBase64(String base64){
        byte[] decodeBytes = Base64.getDecoder().decode(base64);
        return new String(decodeBytes);
    }

}
