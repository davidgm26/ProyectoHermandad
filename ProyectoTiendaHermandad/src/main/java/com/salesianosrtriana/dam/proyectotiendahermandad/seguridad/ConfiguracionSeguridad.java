package com.salesianosrtriana.dam.proyectotiendahermandad.seguridad;

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
import com.salesianostriana.dam.proyectotiendahermandad.modelo.Usuario;

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
    	 	http.csrf().disable().authorizeRequests()
			.antMatchers("/admin")
			.hasRole("ADMIN")
			  .antMatchers("/","index","/cristo","/virgen","/juntaDeGobierno","/reglas",
		                "musica","/recorrido")
			.hasAnyRole("ADMIN").anyRequest().permitAll().and().exceptionHandling()
			.accessDeniedPage("/login").and().formLogin().loginPage("/login").loginProcessingUrl("/login")
			.failureUrl("/login-error").permitAll().and().formLogin().defaultSuccessUrl("/menu-principal").and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/inicio").permitAll().and().headers().frameOptions()
			.disable();
}
 /*      
                .authorizeRequests()
                .antMatchers("/","index","/cristo","/virgen","/juntaDeGobierno","/reglas",
                        "musica","/recorrido").permitAll()
                .antMatchers("/","index","/cristo","/virgen","/juntaDeGobierno","/reglas",
                "musica","/recorrido").hasAnyRole("USER", "ADMIN")
                .and().exceptionHandling().accessDeniedPage("/error")
                .and().formLogin()
                .and().logout().logoutSuccessUrl("/");

    }
*/
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/private/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().permitAll()
            .and().exceptionHandling().accessDeniedPage("/")
            .and().formLogin().defaultSuccessUrl("/")
            .and().formLogin().loginPage("/index").loginProcessingUrl("/").failureUrl("/login-error").permitAll()
            .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();

    }
*/
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