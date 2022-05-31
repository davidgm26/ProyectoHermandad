package com.salesianostriana.dam.proyectotiendahermandad.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.salesianostriana.dam.proyectotiendahermandad.repositorio.UsuarioRepo;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter{

    @Autowired
    private UsuarioRepo usuarioRepo;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
  
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	  http
          .csrf().disable()
          .authorizeRequests()
          .antMatchers("/").hasAnyRole("ADMIN")
          .antMatchers("/").hasRole("ADMIN")
          .anyRequest().permitAll()
          .and().exceptionHandling().accessDeniedPage("/")
          .and().formLogin().loginPage("/").loginProcessingUrl("/login")
          .defaultSuccessUrl("/")
          .failureUrl("/").permitAll()
          .and().logout().logoutUrl("/").logoutSuccessUrl("/").permitAll();

    }
      
    
    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();   			
        
        usuarioRepo.getUsuarios()
                .stream()
                .map(u -> {
                    return User
                            .withUsername(u.getUsername())
                            .password("{noop}"+ u.getPassword())
                            .roles(u.getRole())
                            .build();

                })
                .forEach(userDetailsManager::createUser);


        return userDetailsManager;


    }
}