package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.UsuarioRepo;
import co.gov.movilidadbogota.sipa.data.loc.DependenciaRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Locale;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


/**
 * Prueba unitaria del controlador de dependencia
 *
 * @author lorenzo.pinango
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DependenciaControllerTest {

    @Mock
    DependenciaRepo dependenciaRepo;

    @Mock
    UsuarioRepo usuarioRepo;

    @Mock
    MessageSource messages;

    @InjectMocks
    private DependenciaController dependenciaController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(dependenciaController).build();


        Mockito.when(messages.getMessage(anyString(), any(Object[].class), any(Locale.class)))
                .thenReturn("Operacion exitosa");

    }

    @Test
    public void crearDependenciaExitoso() throws Exception {

        this.mockMvc.perform(post("/dependencia/")
                .param("nombre", "Test")
                .param("dependencia.id", "")
                .param("responsable.id", "")
                .param("editar", "0"))
                .andExpect(model().errorCount(0));
    }

    @Test
    public void crearDependenciaNombreVacio() throws Exception {
        this.mockMvc.perform(post("/dependencia/")
                .param("nombre", "")
                .param("dependencia.id", "")
                .param("responsable.id", "")
                .param("editar", "0"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("dependencia-create"));
    }

    @Test
    public void editarDependenciaExitoso() throws Exception {

        this.mockMvc.perform(post("/dependencia/")
                .param("id", "1d7393f1-cce5-4954-b2dc-f0cd625c463c")
                .param("nombre", "Test 2")
                .param("dependencia.id", "")
                .param("responsable.id", "")
                .param("editar", "1"))
                .andExpect(model().errorCount(0));
    }

    @Test
    public void editarEditarNombreVacio() throws Exception {

        this.mockMvc.perform(post("/dependencia/")
                .param("id", "1d7393f1-cce5-4954-b2dc-f0cd625c463c")
                .param("nombre", "")
                .param("dependencia.id", "")
                .param("responsable.id", "")
                .param("editar", "1"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("dependencia-create"));
    }

}