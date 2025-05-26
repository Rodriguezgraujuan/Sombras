package container.Sombras;

import container.Sombras.TestUnitariosGenericos.testUnitariosGenericos;
import container.Sombras.UsuarioControllerTest.UsuarioControllerIntegrationTest;
import container.Sombras.personajeTests.PersonajeControllerIntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.test.context.ActiveProfiles;

import container.Sombras.E2E.RegistroLoginE2ETest;
import container.Sombras.personajeTests.PersonajeControllerTest;
import container.Sombras.UsuarioControllerTest.UsuarioControllerTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        PersonajeControllerTest.class,
        UsuarioControllerTest.class,
        testUnitariosGenericos.class,
        PersonajeControllerIntegrationTest.class,
        UsuarioControllerIntegrationTest.class,
        RegistroLoginE2ETest.class,
})
class SombrasApplicationTests {

}
