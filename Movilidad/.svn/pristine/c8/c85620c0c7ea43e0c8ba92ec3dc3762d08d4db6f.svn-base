package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.AgendamientoAudienciaRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoAudienciaComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoAudienciaComparendoRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Prueba unitaria del controlador de AgendamientoAudienciaController
 * Created by paola.charry on 30/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AgendamientoAudienciaControllerTest {


    @Mock
    AgendamientoAudienciaRepo agendamientoAudienciaRepo;
    @Mock
    TipoAudienciaComparendoRepo tipoAudienciaRepo;
    private MockMvc mockMvc;
    @InjectMocks
    private AgendamientoAudienciaController agendamientoAudienciaController;

    @Before
    public void setUp() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(agendamientoAudienciaController).build();

        agendamientoAudienciaRepo = Mockito.mock(AgendamientoAudienciaRepo.class);
        tipoAudienciaRepo = Mockito.mock(TipoAudienciaComparendoRepo.class);

        List<TipoAudienciaComparendo> tipo_audiencia = new ArrayList<>();
        TipoAudienciaComparendo tipo = new TipoAudienciaComparendo();
        tipo.setId(1);
        tipo.setNombre("AUDIENCIA_INMOVILIZACION");
        Mockito.when(tipoAudienciaRepo.findOne(1))
                .thenReturn(tipo);

        tipo_audiencia.add(tipo);
        tipo = new TipoAudienciaComparendo();
        tipo.setId(2);
        tipo.setNombre("AUDIENCIA_FALLO");
        Mockito.when(tipoAudienciaRepo.findOne(2))
                .thenReturn(tipo);

        tipo_audiencia.add(tipo);

        Mockito.when(tipoAudienciaRepo.findAll()).thenReturn(tipo_audiencia);

    }

    @Test
    public void insertaAgendaExitoso() throws Exception {
        this.mockMvc.perform(post("/agendamiento-audiencia/create")
                .param("horaInicial", "2")
                .param("horaFinal", "4")
                .param("tipo.id", "1")
                .param("fechaRegistro", "2017-06-30T00:00:00.000Z"))
                .andExpect(model().errorCount(0));
    }

    @Test
    public void insertaAgendaHorasFallido() throws Exception {
        this.mockMvc.perform(post("/agendamiento-audiencia/create")
                .param("horaInicial", "2")
                .param("horaFinal", "2")
                .param("tipo.id", "1")
                .param("fechaRegistro", "2017-06-30T00:00:00.000Z"))
                .andExpect(model().errorCount(0));
    }

}