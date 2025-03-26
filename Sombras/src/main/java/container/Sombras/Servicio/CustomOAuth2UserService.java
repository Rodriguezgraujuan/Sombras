package container.Sombras.Servicio;

import container.Sombras.Entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioService usuarioService;

    public CustomOAuth2UserService(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest){
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");

        Usuario usuario = usuarioService.findByEmail(email);

        Usuario user;
        if (usuario==null) {
            user = new Usuario();
            user.setEmail(email);
            user.setUsername(oAuth2User.getAttribute("name"));
            if (user.getUsername()==null){
                user.setUsername(oAuth2User.getAttribute("login"));
            }
            user.setPassword(passwordEncoder.encode("1"));
            user.setRol("USER");
            usuarioService.save(user);
        }

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                oAuth2User.getAttributes(), "email"
        );
    }
}
