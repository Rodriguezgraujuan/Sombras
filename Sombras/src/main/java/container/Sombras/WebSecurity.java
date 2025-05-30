package container.Sombras;

import container.Sombras.Entidad.Usuario;
import container.Sombras.Repositorio.UsuarioRepository;
import container.Sombras.Servicio.CustomOAuth2UserService;
import container.Sombras.Servicio.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
        ).formLogin(login->
                login.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(customSuccessHandler())
                        .failureHandler(customFailureHandler())
                        .permitAll()
        ).logout(logout->
                logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
        ).oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .defaultSuccessUrl("/home.html", true)
                .failureUrl("/login?error")
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(customOAuth2UserService)
                )
                .successHandler(oauth2SuccessHandler())
        );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public AuthenticationSuccessHandler oauth2SuccessHandler() {
        return (request, response, authentication) -> {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = oAuth2User.getAttributes();
            String email = (String) attributes.get("email");
            String name = (String) attributes.get("name");

            Usuario usuario = usuarioRepository.findByEmail(email);

            if (usuario == null) {
                usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setUsername(name);
                usuario.setPassword(passwordEncoder().encode("1"));
                usuario.setStartDate(LocalDate.now().getYear()+" "+LocalDate.now().getMonth());
                if (usuario.getEmail().equals("comuntiro75@gmail.com")) {
                    usuario.setRol("ADMIN");
                }else {
                    usuario.setRol("USER");
                }
                usuarioRepository.save(usuario);
            }
            if (usuario.getEmail().equals("comuntiro75@gmail.com")) {
                response.sendRedirect("/administrador.html");
            }else {
                response.sendRedirect("/home.html");
            }
        };

    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);

        return authenticationManagerBuilder.build();
    }


    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return (request, response, authentication) -> {
            CustomUserDetailsService.resetContador();
            String redirectUrl = "/home.html";
            if (AuthorityUtils.authorityListToSet(authentication.getAuthorities()).contains("ADMIN")) {
                redirectUrl = "/administrador.html";
            }
            response.sendRedirect(redirectUrl);
        };
    }

    @Bean
    public AuthenticationFailureHandler customFailureHandler() {
        return (request, response, exception) -> {
            String errorMessage = "Error de autenticación.";
            if (exception instanceof UsernameNotFoundException) {
                errorMessage = "Usuario no encontrado.";
            } else if (exception instanceof BadCredentialsException) {
                errorMessage = "Credenciales incorrectas.";
            }

            request.getSession().setAttribute("loginError", errorMessage);
            response.sendRedirect("/login?error=true");
        };
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE0", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }

}
