package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.*;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendo;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Prueba unitaria del controlador de cursoAsistenciaController
 * Created by paola.charry on  21/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CursoAsistenciaControllerTest {

    private MockMvc mockMvc;
    @Mock
    private CursoComparendoRepo cursoComparendoRepo;
    @Mock
    private ComparendoRepository comparendoRepository;
    @Mock
    private CursoPedagogicoRepo cursoPedagogicoRepo;

    @InjectMocks
    private CursoAsistenciaController cursoAsistenciaController;

    @Before
    public void setUp() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(cursoAsistenciaController).build();

        cursoComparendoRepo = Mockito.mock(CursoComparendoRepo.class);
        cursoPedagogicoRepo = Mockito.mock(CursoPedagogicoRepo.class);
        cursoComparendoRepo = Mockito.mock(CursoComparendoRepo.class);
        comparendoRepository = Mockito.mock(ComparendoRepository.class);


        NumeroComparendo numeroComparendo = new NumeroComparendo();
        numeroComparendo.setConsecutivo(1);
        numeroComparendo.setValor("1");
        Comparendo comparendo = new Comparendo();
        comparendo.setId(UUID.fromString("b5b76777-cf8f-4447-987b-0ce5951b0d3e"));
        comparendo.setNumero(numeroComparendo);
        Mockito.when(comparendoRepository.findOne(UUID.fromString("b5b76777-cf8f-4447-987b-0ce5951b0d3e")))
                .thenReturn(comparendo);

        Curso curso = new Curso();
        curso.setId(UUID.fromString("0010f196-1e00-40c2-ad06-58477cb580cc"));
        Mockito.when(cursoPedagogicoRepo.findOne(UUID.fromString("0010f196-1e00-40c2-ad06-58477cb580cc")))
                .thenReturn(curso);

        CursoComparendo cursoComparendo = new CursoComparendo();
        cursoComparendo.setId(1);
        cursoComparendo.setComparendo(comparendo);
        cursoComparendo.setCurso(curso);
        Mockito.when(cursoComparendoRepo.findOne(1))
                .thenReturn(cursoComparendo);

        List<Curso> cursos = new ArrayList<>();
        cursos.add(curso);
        List<Comparendo> comparendos = new ArrayList<>();
        comparendos.add(comparendo);
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN_DDMMYY);
        String fechaActual = formatter.format(new Date());
        try {
            Mockito.when(cursoPedagogicoRepo.findByFechaAndEstadoId(formatter.parse(fechaActual),
                    EstadoCurso.VIGENTE.getId())).thenReturn(cursos);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Mockito.when(cursoPedagogicoRepo.findById(curso.getId())).thenReturn(curso);
        Mockito.when(cursoComparendoRepo.findByCursoId(curso.getId())).thenReturn(comparendos);
        Mockito.when(cursoComparendoRepo.findBycomaprendoId(comparendo.getId())).thenReturn(cursoComparendo);

    }

    @Test
    public void consultarExitoso() throws Exception {
        this.mockMvc.perform(get("/curso_asistencia/")
                .param("curso", "1"))
                .andExpect(model().errorCount(0));
    }

    @Test
    public void cursoAsistenciaExitoso() throws Exception {
        this.mockMvc.perform(get("/curso_asistencia/consultar/{id}", "0010f196-1e00-40c2-ad06-58477cb580cc"))
                .andExpect(model().errorCount(0));
    }

    //@Test
    public void saveExitoso() throws Exception {

        this.mockMvc.perform(post("/curso_asistencia/save")
                .param("asistente", "b5b76777-cf8f-4447-987b-0ce5951b0d3e"))
                .andExpect(redirectedUrl("/curso_asistencia"))
                .andExpect(flash().attributeExists("FLASH_MESSAGE_OK"));

    }
}