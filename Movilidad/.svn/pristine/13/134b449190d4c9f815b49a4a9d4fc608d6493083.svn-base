package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.*;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Prueba unitaria del controlador de CursoPedagogicoController
 * Created by paola.charry on 05/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CursoPedagogicoControllerTest {


    private MockMvc mockMvc;

    @Mock
    private CursoPedagogicoRepo cursoPedagogicoRepo;

    @Mock
    private SedeRepo sedeRepo;

    @Mock
    private SalonRepo salonRepo;

    @Mock
    private EstadoCursoRepo estadoRepo;

    @Mock
    private InstructorRepository instructorRepo;

    @InjectMocks
    private CursoPedagogicoController cursoPedagogicoController;

    @Before
    public void setUp() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(cursoPedagogicoController).build();

        cursoPedagogicoRepo = Mockito.mock(CursoPedagogicoRepo.class);
        sedeRepo = Mockito.mock(SedeRepo.class);
        salonRepo = Mockito.mock(SalonRepo.class);
        instructorRepo = Mockito.mock(InstructorRepository.class);
        estadoRepo = Mockito.mock(EstadoCursoRepo.class);


        Sede sede = new Sede();
        sede.setId(UUID.fromString("9df8a9c3-c4ec-4831-b6a1-4fa1b2100407"));
        sede.setNombre("sede1");
        Mockito.when(sedeRepo.findOne(UUID.fromString("9df8a9c3-c4ec-4831-b6a1-4fa1b2100407")))
                .thenReturn(sede);

        Salon salon = new Salon();
        salon.setId(UUID.fromString("f23bac35-bd45-422a-84e2-b71bfe911994"));
        salon.setSede(sede);
        salon.setNombre("salon1");
        Mockito.when(sedeRepo.findOne(UUID.fromString("f23bac35-bd45-422a-84e2-b71bfe911994")))
                .thenReturn(sede);


        Instructor instructor = new Instructor();
        instructor.setId(UUID.fromString("eefe9558-7a26-4feb-a3cd-ced98228a82f"));
        Mockito.when(instructorRepo.findOne(UUID.fromString("eefe9558-7a26-4feb-a3cd-ced98228a82f")))
                .thenReturn(instructor);

        List<Instructor> instructores = new ArrayList<>();
        instructores.add(instructor);


        List<Sede> sedes = new ArrayList<>();
        sedes.add(sede);

        EstadoCurso estado = new EstadoCurso();
        estado.setId(1);
        estado.setNombre("VIGENTE");
        Mockito.when(estadoRepo.findOne(1))
                .thenReturn(estado);

        List<Curso> cursos = new ArrayList<>();

        Mockito.when(sedeRepo.findAll()).thenReturn(sedes);
        Mockito.when(instructorRepo.findAll()).thenReturn(instructores);
        Mockito.when(cursoPedagogicoRepo.findBySalonAndFecha(salon.getId(), new Date())).thenReturn(cursos);

    }

    @Test
    public void insertaCursoExitoso() throws Exception {

        this.mockMvc.perform(post("/cursos/")
                .param("tema", "Accidentalidad de Transporte")
                .param("fecha", "2017-06-20T00:00:00.000Z")
                .param("horaInicial", "14:00")
                .param("horaFinal", "15:00")
                .param("maximoAsistentes", "2")
                .param("estado.id", "1")
                .param("instructor.id", "eefe9558-7a26-4feb-a3cd-ced98228a82f")
                .param("salon.id", "f23bac35-bd45-422a-84e2-b71bfe911994")
                .param("fechaRegistro", "2017-06-20T00:00:00.000Z"))
                .andExpect(model().errorCount(0));
    }

    @Test
    public void insertaCursoTemaFallido() throws Exception {
        this.mockMvc.perform(post("/cursos/")
                .param("tema", "")
                .param("fecha", "2017-06-20T00:00:00.000Z")
                .param("horaInicial", "14:00")
                .param("horaFinal", "15:00")
                .param("maximoAsistentes", "2")
                .param("estado.id", "1")
                .param("instructor.id", "eefe9558-7a26-4feb-a3cd-ced98228a82f")
                .param("salon.id", "f23bac35-bd45-422a-84e2-b71bfe911994")
                .param("fechaRegistro", "2017-06-20T00:00:00.000Z"))
                .andExpect(flash().attributeExists("FLASH_MESSAGE_ERROR"));

    }

    @Test
    public void insertaCursoAsistentesFallido() throws Exception {
        this.mockMvc.perform(post("/cursos/")
                .param("tema", "Transporte publico")
                .param("fecha", "2017-06-20T00:00:00.000Z")
                .param("horaInicial", "14:00")
                .param("horaFinal", "15:00")
                .param("maximoAsistentes", "0")
                .param("estado.id", "1")
                .param("instructor.id", "eefe9558-7a26-4feb-a3cd-ced98228a82f")
                .param("salon.id", "f23bac35-bd45-422a-84e2-b71bfe911994")
                .param("fechaRegistro", "2017-06-20T00:00:00.000Z"))
                .andExpect(flash().attributeExists("FLASH_MESSAGE_ERROR"));
    }
}