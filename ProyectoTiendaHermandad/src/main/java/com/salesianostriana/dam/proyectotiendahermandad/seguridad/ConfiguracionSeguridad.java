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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.salesianostriana.dam.proyectotiendahermandad.repositorio.UsuarioRepo;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter{

    @Autowired
    private UsuarioRepo usuarioRepo;
    
    @Override //Este sirve para ver como se encuentra el usuario a la hora de autenticar el logueo.
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
  
    /**
     * Método que controla a que páginas se va a dar acceso según el rol del usuario
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	  http
          .csrf().disable()//Esta se deja para poder hacer el logout con un enlace y ya
          .authorizeRequests() //Las peticiones necesarias
          .antMatchers("/user/**").hasRole("USER") //
          .antMatchers("/admin/**").hasRole("ADMIN")//Aquí se define el controlador al que pueden acceder según el rol
         //El (/xxx/**) sirve para poder tener acceso a todos los controladores de ese rol
          .anyRequest().permitAll()//Esto es para poder usar los archivos de css,js, img...
          //En los controladores del public,poner /public/xxxx/ y agregar nuevas lineas aquí          
          .and().exceptionHandling().accessDeniedPage("/inicioSesionError")
          .and().formLogin().loginPage("/public/inicioSesion").loginProcessingUrl("/login")
          .successHandler(myAuthenticationSuccessHandler())
          
          		//default Succes handler
          .failureUrl("/login-error").permitAll()
          .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll()
          .and().headers().frameOptions().disable();

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
                            .password("{noop}"+ u.getPassword())//noop es para decir si se guarda encriptada o no, en este caso sin encriptar
                            .roles(u.getRole())
                            .build();

                })
                .forEach(userDetailsManager::createUser);


        return userDetailsManager;


    }
    
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleURLAuthenticationSuccesHandler();
    }
}