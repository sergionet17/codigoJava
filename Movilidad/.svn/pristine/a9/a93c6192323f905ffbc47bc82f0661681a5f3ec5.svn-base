package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.fin.Transaccion;
import co.gov.movilidadbogota.sipa.data.fin.TransaccionRepo;
import co.gov.movilidadbogota.sipa.serv.api.EventoFinanciero;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.UUID;

/**
 * Pruebas para el controlador de registros financieros
 *
 * @author arturo.cruz
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class FinancieroServiceImplTest {

    @Mock
    private TransaccionRepo transaccionRepo;

    @Mock
    private ComparendoRepository comparendoRepository;

    @InjectMocks
    private FinancieroServiceImpl financieroServiceImpl;

    @InjectMocks
    private StrategyMultaComparendoPresuntivo strategyMultaComparendoPresuntivo;

    private ObjectMapper mapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(financieroServiceImpl).build();

        Comparendo comparendo = new Comparendo();
        comparendo.setId(UUID.fromString("b5b76777-cf8f-4447-987b-0ce5951b0d3e"));
        Mockito.when(comparendoRepository.findOne(UUID.fromString("b5b76777-cf8f-4447-987b-0ce5951b0d3e")))
                .thenReturn(comparendo);
    }

    //@Test
    public void multaComparendoPresuntivo() throws Exception {

        ArgumentCaptor<Transaccion> transaccionCaptor = ArgumentCaptor.forClass(Transaccion.class);

        EventoFinanciero request = mapper.readValue(
                getTextResource("multaComparendoPresuntivo-sample1.json"),
                EventoFinanciero.class
        );

        financieroServiceImpl.registrar(request);

        Mockito.verify(transaccionRepo).save(transaccionCaptor.capture());

        Transaccion tx = transaccionCaptor.getValue();

        Assert.assertTrue(tx.getEntradas().size() > 0);
    }

    private String getTextResource(String resourceName) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        String fullName = this.getClass().getPackage().getName().replaceAll("[.]", "/") + "/" + resourceName;
        InputStream is = classLoader.getResourceAsStream(fullName);
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer, "UTF-8");
        return writer.toString();
    }

}
