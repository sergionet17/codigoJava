package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.UsuarioRepo;
import co.gov.movilidadbogota.sipa.data.gen.*;
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

import javax.persistence.EntityManager;
import java.util.Locale;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


/**
 * Prueba unitaria del controlador de parametro
 *
 * @author lorenzo.pinango
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParametroControllerTest {

    @Mock
    ParametroRepo parametroRepo;
    @Mock
    CategoriaParametroRepo categoriaParametroRepo;
    @Mock
    ValorParametroRepo valorParametroRepo;
    @Mock
    ValorParametroFileRepo valorParametroFileRepo;
    @Mock
    MessageSource messages;
    @Mock
    UsuarioRepo usuarioRepo;
    @Mock
    EntityManager em;

    @InjectMocks
    private ParametroController parametroController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(parametroController).build();

        Parametro parametro = new Parametro();
        parametro.setClave("LOGITUD_MINIMA_PASSWORD");
        parametro.setTipo("Integer");
        parametro.setNombre("Longitud de password");
        parametro.setDescripcion("Longitud de password");
        Mockito.when(parametroRepo.findOne("LOGITUD_MINIMA_PASSWORD")).thenReturn(parametro);

        Mockito.when(messages.getMessage(anyString(), any(Object[].class), any(Locale.class)))
                .thenReturn("Operacion exitosa");

    }

    @Test
    public void editarParametroExitoso() throws Exception {

        this.mockMvc.perform(post("/parametro/")
                .param("parametro.clave", "LOGITUD_MINIMA_PASSWORD")
                .param("inicioVigencia", "1870-01-01 00:00")
                .param("fechaCreacion", "1870-01-01 00:00")
                .param("valor", "20"))
                .andExpect(model().errorCount(0));
    }

    @Test
    public void editarParametroValorVacio() throws Exception {

        this.mockMvc.perform(post("/parametro/")
                .param("parametro.clave", "LOGITUD_MINIMA_PASSWORD")
                .param("inicioVigencia", "1870-01-01 00:00")
                .param("fechaCreacion", "1870-01-01 00:00")
                .param("valor", ""))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("parametro-edit"));
    }

}