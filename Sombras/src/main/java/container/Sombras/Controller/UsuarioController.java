package container.Sombras.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
