package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.*;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumentoRepo;
import co.gov.movilidadbogota.sipa.data.loc.Dependencia;
import co.gov.movilidadbogota.sipa.data.loc.DependenciaRepo;
import co.gov.movilidadbogota.sipa.data.persona.Persona;
import co.gov.movilidadbogota.sipa.data.persona.PersonaRepo;
import co.gov.movilidadbogota.sipa.data.persona.TipoPersona;
import co.gov.movilidadbogota.sipa.data.persona.TipoPersonaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Prueba unitaria del controlador de usuario
 *
 * @author lorenzo.pinango
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioControllerTest {
    @Mock
    PerfilRepo perfilRepo;
    @Mock
    PersonaRepo personaRepo;
    @Mock
    UsuarioRepo usuarioRepo;
    @Mock
    DependenciaRepo dependenciaRepo;
    @Mock
    TipoPersonaRepository tipoPersonaRepo;
    @Mock
    TipoDocumentoRepo tipoDocumentoRepo;
    @Mock
    HistoricoPasswordRepo historicoPasswordRepo;
    @Mock
    MessageSource messages;
    @InjectMocks
    private UsuarioController usuarioController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        List<Dependencia> dependencias = new ArrayList<>();
        Mockito.when(dependenciaRepo.findAll()).thenReturn(dependencias);

        Dependencia dependencia = new Dependencia();
        dependencia.setId(UUID.fromString("6e7e6abe-1aef-4770-8ea4-f168d72649a2"));

        Mockito.when(dependenciaRepo.findOne(UUID.fromString(
                "6e7e6abe-1aef-4770-8ea4-f168d72649a2"))).thenReturn(dependencia);

        Mockito.when(dependenciaRepo.findById(UUID.fromString(
                "6e7e6abe-1aef-4770-8ea4-f168d72649a2"))).thenReturn(dependencia);

        List<Perfil> perfiles = new ArrayList<>();
        Mockito.when(perfilRepo.findAll()).thenReturn(perfiles);

        List<TipoPersona> tiposPersona = new ArrayList<>();
        Mockito.when(tipoPersonaRepo.findAll()).thenReturn(tiposPersona);

        List<TipoDocumento> tiposDocumentos = new ArrayList<>();
        Mockito.when(tipoDocumentoRepo.findAll()).thenReturn(tiposDocumentos);

        Usuario u = mock(Usuario.class);
        Documento documentoSoporte = new Documento();
        documentoSoporte.setId(UUID.fromString("3108536d-3dde-4541-9dd2-a4e838521004"));
        Mockito.when(u.getDocumentoSoporte()).thenReturn(documentoSoporte);
        byte[] array = null;
        Mockito.when(u.getFirma()).thenReturn(array);

        Usuario usuario = new Usuario();
        usuario.setId(UUID.fromString("3108536d-3dde-4541-9dd2-a4e838521002"));
        usuario.setFirma(array);
        usuario.setDocumentoSoporte(documentoSoporte);
        Mockito.when(usuarioRepo.findOne(UUID.fromString(
                "3108536d-3dde-4541-9dd2-a4e838521002"))).thenReturn(usuario);

        Persona persona = new Persona();
        persona.setPrimerNombre("Pedro");
        persona.setPrimerApellido("Perez");
        persona.setTipoDocumentoIdentidad(TipoDocumento.CC);
        persona.setNumeroDocumentoIdentidad("111111");
        persona.setTipoPersona(TipoPersona.NATURAL);

        Mockito.when(personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(any(TipoDocumento.class), any(String.class))).thenReturn(persona);

        Mockito.when(messages.getMessage(anyString(), any(Object[].class), any(Locale.class)))
                .thenReturn("Operacion exitosa");

    }

    @Test
    public void crearUsuarioExitoso() throws Exception {
        byte[] array = null;
        MockMultipartFile documento = new MockMultipartFile("documento", array);
        MockMultipartFile firma = new MockMultipartFile("imgFirma", array);
        this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/usuario/create/")
                .file(documento)
                .file(firma)
                .param("tipoUsuario", "usuarioSistema")
                .param("username", "test")
                .param("password", "aaaBBB111***")
                .param("activo", "true")
                .param("fechaCaducidad", "2017-08-01 14:00")
                .param("dependencia.id", "6e7e6abe-1aef-4770-8ea4-f168d72649a2")
                .param("cargo", "Cargo de prueba")
                .param("email", "aaaa@hotmail.com")
                .param("perfil.id", "3108536d-3dde-4541-9dd2-a4e838521001")
                .param("autorizador.id", "")
                .param("persona.tipoPersona.id", "1")
                .param("persona.documentoIdentidad.tipo.id", "1")
                .param("persona.documentoIdentidad.numero", "111111")
                .param("persona.primerNombre", "Pedro")
                .param("persona.primerApellido", "Perez"))
                .andExpect(model().errorCount(0));
    }

    @Test
    public void editarUsuarioExitoso() throws Exception {
        byte[] array = null;
        MockMultipartFile documento = new MockMultipartFile("documento", array);
        MockMultipartFile firma = new MockMultipartFile("imgFirma", array);
        this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/usuario/edit/")
                .file(documento)
                .file(firma)
                .param("tipoUsuario", "usuarioSistema")
                .param("id", "3108536d-3dde-4541-9dd2-a4e838521002")
                .param("username", "test")
                .param("passwordAnterior", "18a644e6b0310f2f0f0519ae674a740cc4949f1e")
                .param("password", "18a644e6b0310f2f0f0519ae674a740cc4949f1e")
                .param("cambioPassword", "NO")
                .param("displayFirma", "no")
                .param("activo", "true")
                .param("fechaCaducidad", "2017-08-01 14:00")
                .param("dependencia.id", "6e7e6abe-1aef-4770-8ea4-f168d72649a2")
                .param("cargo", "Cargo de prueba")
                .param("email", "aaaa@hotmail.com")
                .param("perfil.id", "3108536d-3dde-4541-9dd2-a4e838521001")
                .param("autorizador.id", "")
                .param("persona.tipoPersona.id", "1")
                .param("persona.documentoIdentidad.tipo.id", "1")
                .param("persona.documentoIdentidad.numero", "111111")
                .param("persona.primerNombre", "Pedro")
                .param("persona.primerApellido", "Perez"))
                .andExpect(model().errorCount(0));
    }
}