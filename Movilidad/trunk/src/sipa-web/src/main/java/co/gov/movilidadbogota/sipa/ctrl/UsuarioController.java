package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import co.gov.movilidadbogota.sipa.data.aut.*;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.TipoDocumental;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.gen.Parametro;
import co.gov.movilidadbogota.sipa.data.gen.ValorParametroRepo;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumentoRepo;
import co.gov.movilidadbogota.sipa.data.loc.Dependencia;
import co.gov.movilidadbogota.sipa.data.loc.DependenciaRepo;
import co.gov.movilidadbogota.sipa.data.persona.Persona;
import co.gov.movilidadbogota.sipa.data.persona.PersonaRepo;
import co.gov.movilidadbogota.sipa.data.persona.TipoPersonaRepository;
import co.gov.movilidadbogota.sipa.trans.UsuarioActiveDirectory;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

/**
 * Controlador para administrar los usuarios
 *
 * @author lorenzo.pinango
 */
@Controller
@RequestMapping(value = UsuarioController.CONTROLLER_PATH)
public class UsuarioController extends BaseController {
    public static final String CONTROLLER_PATH = "/usuario";
    public static final String CONTROLLER_PATH_LIST = "";
    public static final String CONTROLLER_PATH_CREATE = "/create";
    public static final String CONTROLLER_PATH_EDIT = "/edit";
    public static final String CONTROLLER_PATH_USERNAME = "/username";
    public static final String CONTROLLER_PATH_LDAP = "/ldap";
    public static final String CONTROLLER_PATH_FIRMA = "/firma";
    public static final String CONTROLLER_PATH_ESTADO = "/estado";

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private
    OAuth2RestTemplate oAuth2RestTemplate;

    @Value("${app.oauth2-server:http://localhost:8082/sipa-cas}/api")
    private
    String oauth2ResourceUrl;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private
    DependenciaRepo dependenciaRepo;

    @Autowired
    private
    PerfilRepo perfilRepo;

    @Autowired
    private
    TipoPersonaRepository tipoPersonaRepo;

    @Autowired
    private
    TipoDocumentoRepo tipoDocumentoRepo;

    @Autowired
    private
    PersonaRepo personaRepo;

    @Autowired
    private
    HistoricoPasswordRepo historicoPasswordRepo;

    @Autowired
    private
    ValorParametroRepo valorParametroRepo;

    @Autowired
    private MessageSource messages;

    @Autowired
    private SipaServicesClient sipaServicesClient;

    /**
     * Lista todos los usuarios
     */
    @PreAuthorize("hasAuthority('" + Permiso.LISTAR_USUARIO + "')")
    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String list(Model model) {
        try {
            LOGGER.info("Buscar los usuarios");
            model.addAttribute("usuarios", usuarioRepo.findAll());
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "usuario-list";
    }

    /**
     * Se inicializa el formulario para la captura de la información del usuario
     *
     * @return Se retorna a la pagina de registro de usuario
     */
    @PreAuthorize("hasAuthority('" + Permiso.CREAR_USUARIO + "')")
    @GetMapping(value = CONTROLLER_PATH_CREATE)
    public String create(Model model, Locale locale) {
        try {
            //Inicializa la forma para realizar el registro de un rol
            model.addAttribute("usuarioForm", new Usuario());
            model.addAttribute("dependencias", dependenciaRepo.findAllDependenciasPrimarias());
            model.addAttribute("personas", ControllerUtils.listToMapWithDefault(personaRepo.findAll(), "primerNombre,segundoNombre,primerApellido,segundoApellido", messages, locale));
            model.addAttribute("perfiles", ControllerUtils.listToMapWithDefault(perfilRepo.findAllActivos(), "nombre", messages, locale));
            model.addAttribute("tipospersona", ControllerUtils.listToMapWithDefault(tipoPersonaRepo.findAll(), "nombre", messages, locale));
            model.addAttribute("tiposdocumento", ControllerUtils.listToMapWithDefault(tipoDocumentoRepo.findAll(), "sigla", messages, locale));
            LOGGER.info("Formulario de registro de un usuario");

        } catch (Exception e) {

            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "usuario-create";
    }

    /**
     * Se busca el usuario en LDAP {@link UsuarioActiveDirectory}
     *
     * @param username representa el username del usuario a consultar en el ldap
     * @return Se retorna el usuario del directorio activo
     */
    @GetMapping(value = CONTROLLER_PATH_LDAP + "/{username}", produces = "application/json")
    @ResponseBody
    public UsuarioActiveDirectory getUserLdap(@PathVariable("username") String username) {
        UsuarioActiveDirectory userldap = this.getUsuarioLdap(username);
        if (userldap != null) {
            Usuario user = usuarioRepo.findOneByUsername(username);
            if (user == null) {
                return userldap;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Se busca el usuario en la BD {@link Usuario}
     *
     * @param username representa el username del usuario a consultar en la BD
     * @return Se retorna el usuario de la bd
     */
    @GetMapping(value = CONTROLLER_PATH_USERNAME + "/{username}", produces = "application/json")
    @ResponseBody
    public Usuario getUserBd(@PathVariable("username") String username) {
        return usuarioRepo.findOneByUsername(username);
    }

    /**
     * Se raliza las validaciones de tipo de dato y de negocio.
     * Se persiste un rol {@link Usuario}
     *
     * @param usuario
     * @param result
     * @param model
     * @param redirectAttributes
     * @param locale
     * @return en caso de no pasar las validaciones de tipo de dato y de negocio se retorna al formulario de registro
     * en caso contrario se persiste un {@link Usuario}
     */
    @PostMapping(CONTROLLER_PATH_CREATE)
    public String crearUsuario(
            @Valid @ModelAttribute("usuarioForm") Usuario usuario,
            BindingResult result,
            @RequestParam("tipoUsuario") String tipoUsuario,
            @RequestParam("imgFirma") MultipartFile firma,
            @RequestParam("documento") MultipartFile documentoSoporte,
            Model model,
            RedirectAttributes redirectAttributes,
            Locale locale) throws IOException {

        // Si existen errores de formato retorna al mismo formulario
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("Se realiza las validaciones de negocio");
        Documento documento = null;
        new UsuarioValidator().validate(usuario, result);
        if (tipoUsuario == null || tipoUsuario.isEmpty()) {
            result.reject("username");
            model.addAttribute("error", "Debe seleccionar el tipo de usuario");
        } else if (tipoUsuario.equals("activeDirectory") && usuario.getUsername() != null
                && !usuario.getUsername().isEmpty()) {
            UsuarioActiveDirectory userldap = this.getUsuarioLdap(usuario.getUsername());
            if (userldap != null) {
                Usuario user = usuarioRepo.findOneByUsername(usuario.getUsername());
                if (user != null) {
                    result.rejectValue("username", "usuario.username.database");
                }
            } else {
                result.rejectValue("username", "usuario.username.notldap");
            }
        } else if (tipoUsuario.equals("usuarioSistema") && usuario.getUsername() != null
                && !usuario.getUsername().isEmpty()) {
            Usuario user = usuarioRepo.findOneByUsername(usuario.getUsername());
            if (user != null) {
                result.rejectValue("username", "usuario.username.database");
            }
        }
        if (!firma.isEmpty()
                && !Arrays.asList(Constants.CONTENIDO_IMAGENES).contains(firma.getContentType())) {
            result.rejectValue("firma", "documento.error.contentType");
        }
        //Validaciones de la contrasena
        if (tipoUsuario.equals("usuarioSistema") && !usuario.getPassword().isEmpty() && usuario.getPassword() != null) {
            //Cantidad de caracteres especiales
            int specials = usuario.getPassword().split(Constants.SPECIAL_CHARS_REGEX, -1).length - 1;
            //Cantidad de mayusculas, minusculas y Numeros
            int upperCase = 0;
            int lowerCase = 0;
            int numbers = 0;

            for (char c : usuario.getPassword().toCharArray()) {
                // Check for uppercase letters.
                if (Character.isUpperCase(c)) upperCase++;
                // Check for lowercase letters.
                if (Character.isLowerCase(c)) lowerCase++;
                // Check for numbers letters.
                if (c >= '0' && c <= '9') numbers++;
            }

            if (usuario.getPassword().length() < Integer.parseInt(
                    valorParametroRepo.findValorVigenteByClave(Parametro.LOGITUD_MINIMA_PASSWORD, new Date()).getValor())
                    || upperCase < Integer.parseInt(
                    valorParametroRepo.findValorVigenteByClave(
                            Parametro.CANTIDAD_MINIMA_MAYUSCULAS_PASSWORD, new Date()).getValor())
                    || lowerCase < Integer.parseInt(
                    valorParametroRepo.findValorVigenteByClave(
                            Parametro.CANTIDAD_MINIMA_MINUSCULAS_PASSWORD, new Date()).getValor())
                    || specials < Integer.parseInt(
                    valorParametroRepo.findValorVigenteByClave(
                            Parametro.CANTIDAD_MINIMA_ESPECIALES_PASSWORD, new Date()).getValor())
                    || numbers < Integer.parseInt(
                    valorParametroRepo.findValorVigenteByClave(
                            Parametro.CANTIDAD_MINIMA_NUMEROS_PASSWORD, new Date()).getValor())
                    )
                result.rejectValue("password",
                        "usuario.password.validate",
                        new Object[]{valorParametroRepo.findValorVigenteByClave(
                                Parametro.LOGITUD_MINIMA_PASSWORD, new Date()).getValor(),
                                valorParametroRepo.findValorVigenteByClave(
                                        Parametro.CANTIDAD_MINIMA_MAYUSCULAS_PASSWORD, new Date()).getValor(),
                                valorParametroRepo.findValorVigenteByClave(
                                        Parametro.CANTIDAD_MINIMA_MINUSCULAS_PASSWORD, new Date()).getValor(),
                                valorParametroRepo.findValorVigenteByClave(
                                        Parametro.CANTIDAD_MINIMA_ESPECIALES_PASSWORD, new Date()).getValor(),
                                valorParametroRepo.findValorVigenteByClave(
                                        Parametro.CANTIDAD_MINIMA_NUMEROS_PASSWORD, new Date()).getValor()}, null);
        }
        if (!result.hasErrors()
                && !documentoSoporte.isEmpty() && documentoSoporte != null) {
            try {
                documento = sipaServicesClient.getDocumentos().addDocumento(
                        new Documento(
                                documentoSoporte.getContentType(),
                                documentoSoporte.getOriginalFilename(),
                                TipoDocumental.AUTORIZACION_CREACION_USUARIO
                        ),
                        documentoSoporte.getBytes()
                );
            } catch (Exception e) {
                result.rejectValue("documentoSoporte", "usuario.documentoSoporte.error");
            }
        }
        if (result.hasErrors()) {
            if (LOGGER.isDebugEnabled())
                LOGGER.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
            model.addAttribute("tipoUsuario", tipoUsuario);
            model.addAttribute("dependencias", dependenciaRepo.findAllDependenciasPrimarias());
            model.addAttribute("personas", ControllerUtils.listToMapWithDefault(
                    personaRepo.findAll(),
                    "primerNombre,segundoNombre,primerApellido,segundoApellido", messages, locale));
            model.addAttribute("perfiles", ControllerUtils.listToMapWithDefault(
                    perfilRepo.findAllActivos(), "nombre", messages, locale));
            model.addAttribute("tipospersona", ControllerUtils.listToMapWithDefault(
                    tipoPersonaRepo.findAll(), "nombre", messages, locale));
            model.addAttribute("tiposdocumento", ControllerUtils.listToMapWithDefault(
                    tipoDocumentoRepo.findAll(), "sigla", messages, locale));
            return "usuario-create";
        }
        //Se realiza la persistencia de la persona
        Persona persona = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(
                usuario.getPersona().getTipoDocumentoIdentidad(),
                usuario.getPersona().getNumeroDocumentoIdentidad()
        );
        if (persona == null) {
            usuario.getPersona().setId(UUID.randomUUID());
            personaRepo.save(usuario.getPersona());
        } else {
            persona.setPrimerNombre(usuario.getPersona().getPrimerNombre());
            persona.setSegundoNombre(usuario.getPersona().getSegundoNombre());
            persona.setPrimerApellido(usuario.getPersona().getPrimerApellido());
            persona.setSegundoApellido(usuario.getPersona().getSegundoApellido());
            persona.setTipoPersona(usuario.getPersona().getTipoPersona());
            personaRepo.save(persona);
            usuario.setPersona(persona);
        }
        usuario.setId(UUID.randomUUID());
        if (documento != null)
            usuario.setDocumentoSoporte(documento);
        if (!firma.isEmpty())
            usuario.setFirma(firma.getBytes());
        if (tipoUsuario.equals("usuarioSistema")) {
            usuario.setPassword(DigestUtils.sha1Hex(usuario.getPassword()));
        }
        if (usuario.getDependencia() != null) {
            Dependencia dependencia = dependenciaRepo.findById(usuario.getDependencia().getId());
            usuario.setDependencia(dependencia);
        }
        if (usuario.getAutorizador() != null && usuario.getAutorizador().getId() != null) {
            Persona autorizador = personaRepo.findOne(usuario.getAutorizador().getId());
            usuario.setAutorizador(autorizador);
        }
        //Se guarda la información de la forma en el objeto Usuario
        usuarioRepo.save(usuario);
        if (tipoUsuario.equals("usuarioSistema")) {
            //Se guarda el historico de la contrasena
            HistoricoPassword historico = new HistoricoPassword();
            historico.setId(UUID.randomUUID());
            historico.setPassword(usuario.getPassword());
            historico.setUsuario(usuario);
            historico.setFechaRegistro(new Date());
            historicoPasswordRepo.save(historico);
        }

        redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                messages.getMessage("usuario.action.create.success", new Object[]{usuario.getUsername()}, locale));
        return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
    }

    /**
     * Se inicializa el formulario para editar la información del usuario
     *
     * @return Se retorna a la pagina de editar el usuario
     */
    @PreAuthorize("hasAuthority('" + Permiso.MODIFICAR_USUARIO + "')")
    @RequestMapping(path = CONTROLLER_PATH_EDIT + "/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") UUID id, Model model, Locale locale) {
        try {
            //Inicializa la forma para realizar la modificacion de un rol
            Usuario usuario = usuarioRepo.findOne(id);
            model.addAttribute("usuarioForm", usuario);
            String tipoUsuario = "usuarioSistema";
            UsuarioActiveDirectory userldap = this.getUsuarioLdap(usuario.getUsername());
            if (userldap != null) {
                tipoUsuario = "activeDirectory";
                if (userldap.getPwdAccountLockedTime() != null)
                    usuario.setFechaCaducidad(userldap.getPwdAccountLockedTime());
                usuario.setPassword(userldap.getUserPassword());
            }
            //Se carga todas los permisos
            model.addAttribute("tipoUsuario", tipoUsuario);
            model.addAttribute("passwordAnterior", usuario.getPassword());
            model.addAttribute("dependencias", dependenciaRepo.findAllDependenciasPrimarias());
            model.addAttribute("personas", ControllerUtils.listToMapWithDefault(personaRepo.findAll(), "primerNombre,segundoNombre,primerApellido,segundoApellido", messages, locale));
            model.addAttribute("perfiles", ControllerUtils.listToMapWithDefault(perfilRepo.findAllActivos(), "nombre", messages, locale));
            model.addAttribute("tipospersona", ControllerUtils.listToMapWithDefault(tipoPersonaRepo.findAll(), "nombre", messages, locale));
            model.addAttribute("tiposdocumento", ControllerUtils.listToMapWithDefault(tipoDocumentoRepo.findAll(), "sigla", messages, locale));
            if (usuario.getFirma() != null)
                model.addAttribute("displayFirma", "si");
            else
                model.addAttribute("displayFirma", "no");
            LOGGER.info("Formulario de modificacion de un rol");
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "usuario-edit";
    }

    /**
     * Redirecciona en el caso de que se intente ingresar al editar sin id
     *
     * @return string
     */
    @RequestMapping(value = CONTROLLER_PATH_EDIT, method = RequestMethod.GET)
    public String editHandler() {
        return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
    }

    /**
     * Se raliza las validaciones de tipo de dato y de negocio.
     * Se persiste un usuario {@link Usuario}
     *
     * @param usuario
     * @param result
     * @param model
     * @param redirectAttributes
     * @param locale
     * @return en caso de no pasar las validaciones de tipo de dato y de negocio se retorna al formulario de registro
     * en caso contrario se persiste un {@link Usuario}
     */
    @PostMapping(CONTROLLER_PATH_EDIT)
    public String editarUsuario(
            @Valid @ModelAttribute("usuarioForm") Usuario usuario,
            BindingResult result,
            @RequestParam("tipoUsuario") String tipoUsuario,
            @RequestParam("imgFirma") MultipartFile firma,
            @RequestParam("documento") MultipartFile documentoSoporte,
            @RequestParam("passwordAnterior") String passwordAnterior,
            @RequestParam("cambioPassword") String stCambioPassword,
            @RequestParam("displayFirma") String displayFirma,
            Model model,
            RedirectAttributes redirectAttributes,
            Locale locale) throws IOException {

        // Si existen errores de formato retorna al mismo formulario
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("Se realiza las validaciones de negocio");
        Documento documento = null;
        Boolean cambioPassword;
        cambioPassword = false;
        if (tipoUsuario.equals("usuarioSistema"))
            if (stCambioPassword.equals("SI"))
                cambioPassword = true;
            else
                usuario.setPassword(passwordAnterior);
        new UsuarioValidator().validate(usuario, result);
        if (!firma.isEmpty()
                && !Arrays.asList(Constants.CONTENIDO_IMAGENES).contains(firma.getContentType())) {
            result.rejectValue("firma", "documento.error.contentType");
        }

        //Validaciones de la contrasena
        if (tipoUsuario.equals("usuarioSistema") && !usuario.getPassword().isEmpty()
                && usuario.getPassword() != null && cambioPassword) {
            //Cantidad de caracteres especiales
            int specials = usuario.getPassword().split(Constants.SPECIAL_CHARS_REGEX, -1).length - 1;
            //Cantidad de mayusculas, minusculas y Numeros
            int upperCase = 0;
            int lowerCase = 0;
            int numbers = 0;

            for (char c : usuario.getPassword().toCharArray()) {
                // Check for uppercase letters.
                if (Character.isUpperCase(c)) upperCase++;
                // Check for lowercase letters.
                if (Character.isLowerCase(c)) lowerCase++;
                // Check for numbers letters.
                if (c >= '0' && c <= '9') numbers++;
            }

            if (usuario.getPassword().length() < Integer.parseInt(
                    valorParametroRepo.findValorVigenteByClave(Parametro.LOGITUD_MINIMA_PASSWORD, new Date()).getValor())
                    || upperCase < Integer.parseInt(
                    valorParametroRepo.findValorVigenteByClave(Parametro.CANTIDAD_MINIMA_MAYUSCULAS_PASSWORD, new Date()).getValor())
                    || lowerCase < Integer.parseInt(
                    valorParametroRepo.findValorVigenteByClave(Parametro.CANTIDAD_MINIMA_MINUSCULAS_PASSWORD, new Date()).getValor())
                    || specials < Integer.parseInt(
                    valorParametroRepo.findValorVigenteByClave(Parametro.CANTIDAD_MINIMA_ESPECIALES_PASSWORD, new Date()).getValor())
                    || numbers < Integer.parseInt(
                    valorParametroRepo.findValorVigenteByClave(Parametro.CANTIDAD_MINIMA_NUMEROS_PASSWORD, new Date()).getValor())
                    )
                result.rejectValue("password",
                        "usuario.password.validate",
                        new Object[]{valorParametroRepo.findValorVigenteByClave(
                                Parametro.LOGITUD_MINIMA_PASSWORD, new Date()).getValor(),
                                valorParametroRepo.findValorVigenteByClave(
                                        Parametro.CANTIDAD_MINIMA_MAYUSCULAS_PASSWORD, new Date()).getValor(),
                                valorParametroRepo.findValorVigenteByClave(
                                        Parametro.CANTIDAD_MINIMA_MINUSCULAS_PASSWORD, new Date()).getValor(),
                                valorParametroRepo.findValorVigenteByClave(
                                        Parametro.CANTIDAD_MINIMA_ESPECIALES_PASSWORD, new Date()).getValor(),
                                valorParametroRepo.findValorVigenteByClave(
                                        Parametro.CANTIDAD_MINIMA_NUMEROS_PASSWORD, new Date()).getValor()}, null);
            else {
                String passwordValidar = DigestUtils.sha1Hex(usuario.getPassword());
                List<HistoricoPassword> historicoList = historicoPasswordRepo.findByUsuarioId(
                        usuario.getId(),
                        new PageRequest(0,
                                Integer.parseInt(valorParametroRepo.findValorVigenteByClave(
                                        Parametro.CANTIDAD_PASSWORDS_ANTERIOR, new Date()).getValor()),
                                new Sort(
                                        new Sort.Order(
                                                Sort.Direction.DESC, "fechaRegistro"))));
                Boolean errorPassword;
                errorPassword = false;
                for (HistoricoPassword h : historicoList) {
                    if (h.getPassword().equals(passwordValidar)) {
                        errorPassword = true;
                        break;
                    }
                }
                if (errorPassword)
                    result.rejectValue("password",
                            "usuario.password.repeat",
                            new Object[]{valorParametroRepo.findValorVigenteByClave(
                                    Parametro.CANTIDAD_PASSWORDS_ANTERIOR, new Date()).getValor()}, null);
            }
        }
        if (!result.hasErrors()
                && !documentoSoporte.isEmpty() && documentoSoporte != null) {
            try {
                documento = sipaServicesClient.getDocumentos().addDocumento(
                        new Documento(
                                documentoSoporte.getContentType(),
                                documentoSoporte.getOriginalFilename(),
                                TipoDocumental.AUTORIZACION_CREACION_USUARIO
                        ),
                        documentoSoporte.getBytes()
                );
            } catch (Exception e) {
                result.rejectValue("documentoSoporte", "usuario.documentoSoporte.error");
            }
        }
        if (result.hasErrors()) {
            if (LOGGER.isDebugEnabled())
                LOGGER.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
            model.addAttribute("tipoUsuario", tipoUsuario);
            model.addAttribute("passwordAnterior", passwordAnterior);
            model.addAttribute("displayFirma", displayFirma);
            model.addAttribute("dependencias", dependenciaRepo.findAllDependenciasPrimarias());
            model.addAttribute("personas", ControllerUtils.listToMapWithDefault(personaRepo.findAll(), "primerNombre,segundoNombre,primerApellido,segundoApellido", messages, locale));
            model.addAttribute("perfiles", ControllerUtils.listToMapWithDefault(perfilRepo.findAllActivos(), "nombre", messages, locale));
            model.addAttribute("tipospersona", ControllerUtils.listToMapWithDefault(tipoPersonaRepo.findAll(), "nombre", messages, locale));
            model.addAttribute("tiposdocumento", ControllerUtils.listToMapWithDefault(tipoDocumentoRepo.findAll(), "sigla", messages, locale));
            return "usuario-edit";
        }
        //Se realiza la persistencia de la persona
        Persona persona = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(
                usuario.getPersona().getTipoDocumentoIdentidad(),
                usuario.getPersona().getNumeroDocumentoIdentidad()
        );
        if (persona == null) {
            usuario.getPersona().setId(UUID.randomUUID());
            personaRepo.save(usuario.getPersona());
        } else {
            persona.setPrimerNombre(usuario.getPersona().getPrimerNombre());
            persona.setSegundoNombre(usuario.getPersona().getSegundoNombre());
            persona.setPrimerApellido(usuario.getPersona().getPrimerApellido());
            persona.setSegundoApellido(usuario.getPersona().getSegundoApellido());
            persona.setTipoPersona(usuario.getPersona().getTipoPersona());
            personaRepo.save(persona);
            usuario.setPersona(persona);
        }
        if (usuario.getDependencia() != null) {
            Dependencia dependencia = dependenciaRepo.findById(usuario.getDependencia().getId());
            usuario.setDependencia(dependencia);
        }
        if (usuario.getAutorizador() != null && usuario.getAutorizador().getId() != null) {
            Persona autorizador = personaRepo.findOne(usuario.getAutorizador().getId());
            usuario.setAutorizador(autorizador);
        }
        if (documento != null)
            usuario.setDocumentoSoporte(documento);
        if (!firma.isEmpty())
            usuario.setFirma(firma.getBytes());
        if (documento == null || firma.isEmpty()) {
            Usuario datos = usuarioRepo.findOne(usuario.getId());
            if (documento == null)
                usuario.setDocumentoSoporte(datos.getDocumentoSoporte());
            if (firma.isEmpty())
                usuario.setFirma(datos.getFirma());
        }
        if (tipoUsuario.equals("usuarioSistema") && cambioPassword) {
            usuario.setPassword(DigestUtils.sha1Hex(usuario.getPassword()));
        }
        //Se guarda la información de la forma en el objeto Usuario
        usuarioRepo.save(usuario);

        if (cambioPassword && tipoUsuario.equals("usuarioSistema")) {
            //Se guarda el historico de la contrasena
            HistoricoPassword historico = new HistoricoPassword();
            historico.setId(UUID.randomUUID());
            historico.setPassword(usuario.getPassword());
            historico.setUsuario(usuario);
            historico.setFechaRegistro(new Date());
            historicoPasswordRepo.save(historico);
        }

        redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                messages.getMessage("usuario.action.edit.success", new Object[]{usuario.getUsername()}, locale));
        return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
    }

    /**
     * Permite rendderizar la firma
     *
     * @return Se retorna la imagen
     */
    @ResponseBody
    @RequestMapping(path = CONTROLLER_PATH_FIRMA + "/{id}",
            method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getFirma(@PathVariable("id") UUID id) {
        Usuario usuario = usuarioRepo.findOne(id);
        return usuario.getFirma();
    }

    /**
     * Funcionalidad para cambiar de estado el usuario
     *
     * @return Se retorna la pagina para listar usuarios
     */
    @RequestMapping(path = CONTROLLER_PATH_ESTADO + "/{id}", method = RequestMethod.GET)
    public String cambiarEstado(@PathVariable("id") UUID id,
                                RedirectAttributes redirectAttributes,
                                Model model, Locale locale) {
        try {
            //Se obtiene el usuario
            Usuario usuario = usuarioRepo.findOne(id);
            usuario.setActivo(!usuario.getActivo());
            usuarioRepo.save(usuario);
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                    messages.getMessage("usuario.action.status.success", new Object[]{usuario.getUsername()}, locale));

        } catch (Exception e) {

            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
    }


    /**
     * Realiza una consulta al CAS para obtener un usuario del LDAP
     *
     * @param username
     * @return UsuarioActiveDirectory
     */
    private UsuarioActiveDirectory getUsuarioLdap(String username) {
        UsuarioActiveDirectory user = new UsuarioActiveDirectory();
        if (!username.equals("")) {
            String url = String.format("%s/user/ldap/%s", oauth2ResourceUrl, username);
            user = oAuth2RestTemplate.getForObject(url, UsuarioActiveDirectory.class);
        }
        if (LOGGER.isDebugEnabled())
            LOGGER.debug(String.format("Usuario del directorio Activo %s", user));
        return user;
    }

}
