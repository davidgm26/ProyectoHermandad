package com.salesianosrtriana.dam.proyectotiendahermandad.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
/*@EnableWebSecurity*/
public class ConfiguracionSeguridad /*extends WebSecurityConfigurerAdapter*/{
}/*
	private List<Usuario>usuarios;
	
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
 
    


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/private/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().permitAll()
            .and().exceptionHandling().accessDeniedPage("/error")
            .and().formLogin().loginPage("/").loginProcessingUrl("/login").failureUrl("/login-error").permitAll()
            .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();

    }
    
    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        usuarios.getUsuarios()
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
}*/