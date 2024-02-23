package it.epicode.w7d5.esercizio.settimanale.security;

import it.epicode.w7d5.esercizio.settimanale.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityChain {
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(AbstractHttpConfigurer::disable);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/auth/**").permitAll());

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/events/create").hasAnyAuthority(Role.ADMIN.name(),Role.EVENT_CREATOR.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/events/update/**").hasAnyAuthority(Role.ADMIN.name(),Role.EVENT_CREATOR.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/events/delete/**").hasAnyAuthority(Role.ADMIN.name(),Role.EVENT_CREATOR.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/events/search/**").permitAll());

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/users/create").hasAnyAuthority(Role.ADMIN.name(),Role.EVENT_CREATOR.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/users/update/**").hasAuthority(Role.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/users/delete/**").hasAnyAuthority(Role.ADMIN.name(),Role.EVENT_CREATOR.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/users/role/**").hasAuthority(Role.ADMIN.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/users/search/**").hasAuthority(Role.ADMIN.name()));

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").denyAll());

        return httpSecurity.build();
    }

}
