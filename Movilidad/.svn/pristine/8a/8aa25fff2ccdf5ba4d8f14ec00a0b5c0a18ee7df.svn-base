package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Permiso;
import co.gov.movilidadbogota.sipa.data.aut.PermisoRepo;
import co.gov.movilidadbogota.sipa.data.aut.Rol;
import co.gov.movilidadbogota.sipa.data.aut.RolRepo;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


/**
 * Prueba unitaria del controlador de rol
 * @author lorenzo.pinango
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RolControllerTest {

    @Mock
    RolRepo rolRepo;

    @Mock
    PermisoRepo permisoRepo;

    @Mock
    MessageSource messages;

    @InjectMocks
    private RolController rolController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(rolController).build();

        Permiso adminRol = new Permiso();
        adminRol.setNombre("ADMIN_ROL");
        adminRol.setDescripcion("Permite administrar los roles");

        Permiso listarRol = new Permiso();
        listarRol.setNombre("LISTAR_ROL");
        listarRol.setDescripcion("Permite listar los roles");

        List<Permiso> permisosDisponibles = new ArrayList<>();
        permisosDisponibles.add(adminRol);
        permisosDisponibles.add(listarRol);

        Mockito.when(permisoRepo.findAll()).thenReturn(permisosDisponibles);

        List<String> permisosConsulta = new ArrayList<>();
        permisosConsulta.add("ADMIN_ROL");

        Mockito.when(rolRepo.countByEqualPermisos(permisosConsulta,1)).thenReturn(1);
        Mockito.when(rolRepo.countByEqualPermisosNotRol(permisosConsulta,1,
                "1d7393f1-cce5-4954-b2dc-f0cd625c463c")).thenReturn(1);

        Mockito.when(messages.getMessage(anyString(), any(Object[].class), any(Locale.class)))
                .thenReturn("Operacion exitosa");

    }

    @Test
    public void crearRolExitoso() throws Exception {

        this.mockMvc.perform(post("/rol/")
                .param("name", "Rol prueba")
                .param("descripcion", "Rol creado para el test junit")
                .param("permisosAsString", "ADMIN_ROL,LISTAR_ROL")
                .param("editar","0"))
                .andExpect(model().errorCount(0));
    }

    @Test
    public void crearRolNombreVacio() throws Exception {

        this.mockMvc.perform(post("/rol/")
                .param("name", "")
                .param("descripcion", "Rol creado para el test junit")
                .param("permisosAsString", "ADMIN_ROL,LISTAR_ROL")
                .param("editar","0"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("rol-create"));
    }

    @Test
    public void crearRolPermisosVacio() throws Exception {

        this.mockMvc.perform(post("/rol/")
                .param("name", "Rol prueba")
                .param("descripcion", "Rol creado para el test junit")
                .param("permisosAsString", "")
                .param("editar","0"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("rol-create"));
    }

    @Test
    public void crearRolPermisosRelacionados() throws Exception {

        this.mockMvc.perform(post("/rol/")
                .param("name", "Rol prueba")
                .param("descripcion", "Rol creado para el test junit")
                .param("permisosAsString", "ADMIN_ROL")
                .param("editar","0"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("rol-create"));
    }

    @Test
    public void editarRolExitoso() throws Exception {

        this.mockMvc.perform(post("/rol/")
                .param("id", "1d7393f1-cce5-4954-b2dc-f0cd625c463c")
                .param("name", "Rol prueba")
                .param("descripcion", "Rol creado para el test junit")
                .param("permisosAsString", "ADMIN_ROL,LISTAR_ROL")
                .param("editar","1"))
                .andExpect(model().errorCount(0));
    }

    @Test
    public void editarRolNombreVacio() throws Exception {

        this.mockMvc.perform(post("/rol/")
                .param("id", "1d7393f1-cce5-4954-b2dc-f0cd625c463c")
                .param("name", "")
                .param("descripcion", "Rol creado para el test junit")
                .param("permisosAsString", "ADMIN_ROL,LISTAR_ROL")
                .param("editar","1"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("rol-create"));
    }

    @Test
    public void editarRolPermisosVacio() throws Exception {

        this.mockMvc.perform(post("/rol/")
                .param("id", "1d7393f1-cce5-4954-b2dc-f0cd625c463c")
                .param("name", "Rol prueba")
                .param("descripcion", "Rol creado para el test junit")
                .param("permisosAsString", "")
                .param("editar","1"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("rol-create"));
    }

    @Test
    public void editarRolPermisosRelacionados() throws Exception {

        this.mockMvc.perform(post("/rol/")
                .param("id", "1d7393f1-cce5-4954-b2dc-f0cd625c463c")
                .param("name", "Rol prueba")
                .param("descripcion", "Rol creado para el test junit")
                .param("permisosAsString", "ADMIN_ROL")
                .param("editar","1"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("rol-create"));
    }

}