package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.PerfilRepo;
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
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Prueba unitaria del controlador de perfil
 * @author lorenzo.pinango
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PerfilControllerTest {
    @Mock
    RolRepo rolRepo;

    @Mock
    PerfilRepo perfilRepo;

    @Mock
    MessageSource messages;

    @InjectMocks
    private PerfilController perfilController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(perfilController).build();

        Rol rolUsuario = new Rol();
        rolUsuario.setId(UUID.fromString("1d7393f1-cce5-4955-b2dc-f0cd625c463c"));
        rolUsuario.setName("USUARIO");
        rolUsuario.setDescripcion("Rol para usuarios");

        Rol rolPerfiles = new Rol();
        rolUsuario.setId(UUID.fromString("1d7393f1-cce5-4956-b2dc-f0cd625c463c"));
        rolPerfiles.setName("PERFILES");
        rolPerfiles.setDescripcion("Rol perfiles");

        List<Rol> rolesDisponibles = new ArrayList<>();
        rolesDisponibles.add(rolUsuario);
        rolesDisponibles.add(rolPerfiles);

        Mockito.when(rolRepo.findAll()).thenReturn(rolesDisponibles);

        List<UUID> rolesConsulta = new ArrayList<>();
        rolesConsulta.add(UUID.fromString("1d7393f1-cce5-4955-b2dc-f0cd625c463c"));

        Mockito.when(perfilRepo.countByEqualRoles(rolesConsulta,1)).thenReturn(1);
        Mockito.when(perfilRepo.countByEqualRolesNotPerfil(rolesConsulta,1,
                "1d7393f1-cce5-4954-b2dc-f0cd625c463c")).thenReturn(1);

        Mockito.when(messages.getMessage(anyString(), any(Object[].class), any(Locale.class)))
                .thenReturn("Operacion exitosa");

    }

    @Test
    public void crearPerfilExitoso() throws Exception {

        this.mockMvc.perform(post("/perfil/")
                .param("nombre", "Perfil prueba")
                .param("descripcion", "Perfil creado para el test junit")
                .param("rolesAsString", "1d7393f1-cce5-4955-b2dc-f0cd625c463c,1d7393f1-cce5-4956-b2dc-f0cd625c463c")
                .param("editar","0"))
                .andExpect(model().errorCount(0));
    }

    @Test
    public void crearPerfilNombreVacio() throws Exception {

        this.mockMvc.perform(post("/perfil/")
                .param("nombre", "")
                .param("descripcion", "Rol creado para el test junit")
                .param("rolesAsString", "1d7393f1-cce5-4955-b2dc-f0cd625c463c,1d7393f1-cce5-4956-b2dc-f0cd625c463c")
                .param("editar","0"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("perfil-create"));
    }

    @Test
    public void crearPerfilRolesVacio() throws Exception {

        this.mockMvc.perform(post("/perfil/")
                .param("nombre", "Perfil prueba")
                .param("descripcion", "Rol creado para el test junit")
                .param("rolesAsString", "")
                .param("editar","0"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("perfil-create"));
    }

    @Test
    public void crearPerfilRolesRelacionados() throws Exception {

        this.mockMvc.perform(post("/perfil/")
                .param("nombre", "Perfil prueba")
                .param("descripcion", "Rol creado para el test junit")
                .param("rolesAsString", "1d7393f1-cce5-4955-b2dc-f0cd625c463c")
                .param("editar","0"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("perfil-create"));
    }

    @Test
    public void editarPerfilExitoso() throws Exception {

        this.mockMvc.perform(post("/perfil/")
                .param("id", "1d7393f1-cce5-4954-b2dc-f0cd625c463c")
                .param("nombre", "Perfil prueba")
                .param("descripcion", "Rol creado para el test junit")
                .param("rolesAsString", "1d7393f1-cce5-4955-b2dc-f0cd625c463c,1d7393f1-cce5-4956-b2dc-f0cd625c463c")
                .param("editar","1"))
                .andExpect(model().errorCount(0));
    }

    @Test
    public void editarPerfilNombreVacio() throws Exception {

        this.mockMvc.perform(post("/perfil/")
                .param("id", "1d7393f1-cce5-4954-b2dc-f0cd625c463c")
                .param("nombre", "")
                .param("descripcion", "Rol creado para el test junit")
                .param("rolesAsString", "1d7393f1-cce5-4955-b2dc-f0cd625c463c,1d7393f1-cce5-4956-b2dc-f0cd625c463c")
                .param("editar","1"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("perfil-create"));
    }

    @Test
    public void editarPerfilRolesVacio() throws Exception {

        this.mockMvc.perform(post("/perfil/")
                .param("id", "1d7393f1-cce5-4954-b2dc-f0cd625c463c")
                .param("nombre", "Perfil prueba")
                .param("descripcion", "Rol creado para el test junit")
                .param("rolesAsString", "")
                .param("editar","1"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("perfil-create"));
    }

    @Test
    public void editarPerfilRolesRelacionados() throws Exception {

        this.mockMvc.perform(post("/perfil/")
                .param("id", "1d7393f1-cce5-4954-b2dc-f0cd625c463c")
                .param("nombre", "Perfil prueba")
                .param("descripcion", "Rol creado para el test junit")
                .param("rolesAsString", "1d7393f1-cce5-4955-b2dc-f0cd625c463c")
                .param("editar","1"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("perfil-create"));
    }


}