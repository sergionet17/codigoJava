package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendo;
import co.gov.movilidadbogota.sipa.data.doc.Plantilla;
import co.gov.movilidadbogota.sipa.data.doc.PlantillaRepo;
import co.gov.movilidadbogota.sipa.data.fin.VolantePagoRepo;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import co.gov.movilidadbogota.sipa.data.persona.Persona;
import co.gov.movilidadbogota.sipa.data.persona.TipoPersona;
import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

/**
 * Prueba unitaria del controlador de VolantePago
 * Autor by paola.charry on 28/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VolantePagoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ComparendoRepository comparendoRepository;
    @Mock
    private VolantePagoRepo volantePagoRepo;

    @InjectMocks
    private VolantePagoController volantePagoController;

    @Mock
    private DocumentoService documentoService;

    @Autowired
    private PlantillaRepo plantillaRepo;

    @Before
    public void setUp() {

        // Process mock annotations
        /*MockitoAnnotations.initMocks(this);*/

        // Setup Spring test in standalone mode
        /*this.mockMvc = MockMvcBuilders.standaloneSetup(volantePagoController).build();
        comparendoRepository = Mockito.mock(ComparendoRepository.class);
        volantePagoRepo = Mockito.mock(VolantePagoRepo.class);
        NumeroComparendo numeroComparendo = new NumeroComparendo();
        numeroComparendo.setConsecutivo(1);
        numeroComparendo.setValor("1");
        Comparendo comparendo = new Comparendo();
        comparendo.setId(UUID.fromString("b5b76777-cf8f-4447-987b-0ce5951b0d3e"));
        comparendo.setNumero(numeroComparendo);
        Mockito.when(comparendoRepository.findOne(UUID.fromString("b5b76777-cf8f-4447-987b-0ce5951b0d3e")))
                .thenReturn(comparendo);
        Mockito.when(comparendoRepository.findByNumeroConsecutivo(1)).thenReturn(comparendo);*/

    }

    //@Test
    public void insertarCursoComparendoExitoso() throws Exception {
        this.mockMvc.perform(get("/volante-create/{numero}", 1))
                .andExpect(redirectedUrl("/comparendo/general/1"));
    }


    //@Test
    public void generarVolante() throws Exception {

    }

    @Test
    public void exportarVolantePago() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(volantePagoController).build();
        comparendoRepository = Mockito.mock(ComparendoRepository.class);
        volantePagoRepo = Mockito.mock(VolantePagoRepo.class);
        NumeroComparendo numeroComparendo = new NumeroComparendo();
        numeroComparendo.setConsecutivo(1);
        numeroComparendo.setValor("1");
        Comparendo comparendo = new Comparendo();
        comparendo.setId(UUID.fromString("b5b76777-cf8f-4447-987b-0ce5951b0d3e"));

        Persona infractor = new Persona(UUID.randomUUID(), TipoDocumento.CC, "1020727514", "John", "Perez", TipoPersona.NATURAL);
        comparendo.setInfractor(infractor);
        comparendo.setNumero(numeroComparendo);
        CommandContext cc = new CommandContext();
        cc.put("infractor",infractor);
        documentoService = Mockito.mock(DocumentoService.class);
        CommandContext cc2 =  documentoService.generarDocumento("volante-pago",cc);
        System.out.println(cc2);

    }

}