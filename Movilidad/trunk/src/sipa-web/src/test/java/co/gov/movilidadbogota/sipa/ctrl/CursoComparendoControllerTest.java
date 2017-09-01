package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.*;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendo;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Prueba unitaria del controlador de CursoComparendoController
 * Created by paola.charry on 08/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CursoComparendoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CursoPedagogicoRepo cursoPedagogicoRepo;

    @Mock
    private CursoComparendoRepo cursoComparendoRepo;

    @Mock
    private ComparendoRepository comparendoRepository;

    @InjectMocks
    private CursoComparendoController cursoComparendoController;


    @Before
    public void setUp() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(cursoComparendoController).build();

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

        List<Curso> cursos = new ArrayList<>();

        cursos.add(curso);

        Mockito.when(comparendoRepository.findByNumeroConsecutivo(1)).thenReturn(comparendo);
        Mockito.when(cursoComparendoRepo.countByComparendoId(comparendo.getId())).thenReturn(0);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String fechaActual = formatter.format(new Date());
        try {
            Mockito.when(cursoPedagogicoRepo.findByFechaAfterAndEstadoIdOrFechaAndEstadoId(formatter.parse(fechaActual),
                    EstadoCurso.VIGENTE.getId(),
                    formatter.parse(fechaActual),
                    EstadoCurso.VIGENTE.getId())).thenReturn(cursos);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Mockito.when(cursoComparendoRepo.countByCursoId(curso.getId())).thenReturn(0);
        Mockito.when(cursoPedagogicoRepo.findById(curso.getId())).thenReturn(curso);


    }

    @Test
    public void insertarCursoComparendoExitoso() throws Exception {
        this.mockMvc.perform(get("/curso_comparendo/save/{id}/{numero}", "0010f196-1e00-40c2-ad06-58477cb580cc", 1))
                .andExpect(redirectedUrl("/comparendo/general/1"));
    }


    public void cursosInscripcionExito() throws Exception {
        this.mockMvc.perform(get("/curso_comparendo/{numero}", 1))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/comparendo/general/1"))
                .andExpect(flash().attributeExists("FLASH_MESSAGE_ERROR"));
    }


    public void cursosInscripcionFallido() throws Exception {
        this.mockMvc.perform(get("/curso_comparendo/{numero}", 500))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/comparendo/general/500"))
                .andExpect(flash().attributeExists("FLASH_MESSAGE_ERROR"));
    }
}

