package com.example.arclight.configurations;

import com.example.arclight.models.UserContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtConfigurationFilter extends OncePerRequestFilter  //Pipleline
{
    private final  JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain)
            throws ServletException, IOException {
    final String authHeader= request.getHeader("Authorization");
    final String jwtToken;

    if(authHeader==null || !authHeader.startsWith("Bearer ")){
        filterChain.doFilter(request,response); // next in the pipeline
        return;
    }
    jwtToken=authHeader.substring(7); // removes the "Bearer ";
        var userId=jwtService.ExtractId(jwtToken)  ; //extract userId
        var email=jwtService.ExtractEmail(jwtToken) ; //extract email

        var userAuth= SecurityContextHolder.getContext().getAuthentication();
        var userContext = new UserContext();
        if(email!=null && userAuth==null){
            var userDetails= this.userDetailsService.loadUserByUsername(email);
            if(jwtService.IsTokenValid(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                  userDetails,
                   null,
                   userDetails.getAuthorities()
                );

                authToken.setDetails(
                        //new WebAuthenticationDetails()
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
            }
        }

        filterChain.doFilter(request,response);
    }
}
