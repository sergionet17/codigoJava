package co.gov.movilidadbogota.sipa.sso.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.HistoricoPassword;
import co.gov.movilidadbogota.sipa.data.aut.HistoricoPasswordRepo;
import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.aut.UsuarioRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.gen.Parametro;
import co.gov.movilidadbogota.sipa.data.gen.ValorParametroRepo;
import co.gov.movilidadbogota.sipa.data.util.MensajeHelper;
import co.gov.movilidadbogota.sipa.sso.repo.LdapRepo;
import co.gov.movilidadbogota.sipa.trans.UsuarioActiveDirectory;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Controlador para login
 *
 * @author lorenzo.pinango
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    public static final String CONTROLLER_PATH = "";
    public static final String CONTROLLER_PATH_REDIRECT = "/cambiar-password";
    public static final String CONTROLLER_PATH_REDIRECT_RESET = "/reset-password";
    public static final String CONTROLLER_PATH_EDIT = "/editar";
    public static final String CONTROLLER_PATH_RESET = "/reset";
    public static final String CONTROLLER_PATH_EDIT_PASSWD = "/editarPasswd";
    public static final String CONTROLLER_PATH_RESET_PASSWD = "/resetPasswd";

    private int tiempoRetardoRespuesta = 10;

    @Autowired
    private
    OAuth2RestTemplate oAuth2RestTemplate;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Value("${app.oauth2-server:http://localhost:8082/sipa-cas}/api")
    private
    String oauth2ResourceUrl;

    @Value("${app.oauth2-server:http://localhost:8082/sipa-cas}")
    String authPath;

    @Autowired
    LdapTemplate ldapTemplate;

    @Autowired
    private
    ValorParametroRepo valorParametroRepo;

    @Autowired
    private MessageSource messages;

    @Autowired
    private MensajeHelper mensajeHelper;

    @Value("${ldap.user-base:ou=users}")
    String userBase;

    @GetMapping(value = {"", "/"})
    public String index() {
        return "home-index";
    }


    @GetMapping("/login")
    public String login() {
        LOGGER.info("Se muestra vista para login");
        return "login-index";
    }

    @Autowired
    private
    HistoricoPasswordRepo historicoPasswordRepo;

    @RequestMapping(path = "/loginError/{error}", method = RequestMethod.GET)
    public String loginError(@PathVariable("error") String error, Model model) {
        LOGGER.info("Se configura mensaje de error por falla en la autenticacion");
        model.addAttribute("error", true);
        model.addAttribute("errorMensaje", error);
        try {
            LOGGER.info("Se retarda tiempo para la respuesta");
            TimeUnit.SECONDS.sleep(tiempoRetardoRespuesta);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "login-index";
    }


    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Se realiza logout del usuario");
        new SecurityContextLogoutHandler().logout(request, null, null);
        try {
            response.sendRedirect(request.getHeader("referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(CONTROLLER_PATH_REDIRECT)
    public String cambiarPasswd() {
        return "passwd-edit";
    }

    @GetMapping(CONTROLLER_PATH_REDIRECT_RESET)
    public String resetPasswd() {
        return "passwd-reset";
    }

    @PostMapping(CONTROLLER_PATH_EDIT)
    public String edit(@RequestParam("username") String username,
                       Model model, RedirectAttributes redirectAttributes,
                       Locale locale) {
        String tipoUsuario = "usuarioSistema";
        LdapRepo ldapRepo = new LdapRepo();
        ldapRepo.setLdapTemplate(ldapTemplate);
        UsuarioActiveDirectory userldap = null;
        String path = "passwd-edit";
        try {
            userldap = ldapRepo.getUserDetails(username, userBase);
            if (userldap != null) {
                tipoUsuario = "activeDirectory";
                model.addAttribute("errorMessage", "El usuario del directorio activo");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (tipoUsuario.equals("usuarioSistema")) {
            Usuario usuario = usuarioRepo.findOneByUsername(username);
            if (usuario != null) {
                model.addAttribute("usuario", usuario);
                path = "passwd-edit2";
            }
            model.addAttribute("errorMessage", "El usuario no existe en base de datos ");
        }
        return path;
    }


    @PostMapping(CONTROLLER_PATH_RESET)
    public String reset(@RequestParam("email") String email,
                        Model model, RedirectAttributes redirectAttributes,
                        Locale locale) throws Exception {

        List<Usuario> usuarios = usuarioRepo.findAllByEmail(email);
        Usuario usuariobd = null;
        String path = "login-index";

        for (Usuario usuario : usuarios) {
            String username = usuario.getUsername();
            LdapRepo ldapRepo = new LdapRepo();
            ldapRepo.setLdapTemplate(ldapTemplate);
            UsuarioActiveDirectory userldap = null;
            try {
                userldap = ldapRepo.getUserDetails(username, userBase);
                if (userldap != null) {

                } else {
                    usuariobd = usuario;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Usuario usuario = usuariobd;

        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            String msg = String.format(
                    "Por favor dirijase al siguiente link para restablecer su contraseña" +
                            "<a href=" + "%s" + "/reset-password/" + "%s" + ">Haga click aqui para restablecer su contraseña</a>", authPath, usuario.getId()
            );
            mensajeHelper.enviarMensaje(usuario.SISTEMA, msg, TipoNotificacion.EMAIL);
            LOGGER.info(String.format("Se crea el mensaje para restablecer contraseña: %s", msg));
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                    messages.getMessage("passwd.action.send.success", new Object[]{usuario.getEmail()}, locale));
            return String.format("redirect:%s", CONTROLLER_PATH);
        } else {
            model.addAttribute("errorMessage", "El usuario no existe en base de datos");
        }
        return path;
    }

    @RequestMapping(path = CONTROLLER_PATH_REDIRECT_RESET+"/{usuarioId}", method = RequestMethod.GET)
    public String restablecerPasswd(@PathVariable("usuarioId") UUID usuarioId, Model model) {
        try {
            Usuario usuario = usuarioRepo.findOne(usuarioId);
            model.addAttribute("usuario", usuario);
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "passwd-reset2";
    }


    @PostMapping(CONTROLLER_PATH_EDIT_PASSWD)
    public String editPasswd(@ModelAttribute("usuario") Usuario usuario,
                             BindingResult result,
                             @RequestParam("passwordAnterior") String passwordAnteriorDigitada,
                            // @RequestParam("passwordOld") String passwordOld,
                             @RequestParam("newPassword") String passwordNuevo,
                             @RequestParam("passwordNuevoConf") String passwordNuevoConf,
                             RedirectAttributes redirectAttributes,
                             Locale locale, Model model) throws IOException {

        //usuario.setPassword(passwordNuevo);
        if (!usuario.getPassword().isEmpty()
                && usuario.getPassword() != null) {
            //Cantidad de caracteres especiales
           // int specials = usuario.getPassword().split(Constants.SPECIAL_CHARS_REGEX, -1).length - 1;
            //Cantidad de mayusculas, minusculas y Numeros
            int upperCase = 0;
            int lowerCase = 0;
            int numbers = 0;

            for (char c : passwordNuevo.toCharArray()) {
                // Check for uppercase letters.
                if (Character.isUpperCase(c)) upperCase++;
                // Check for lowercase letters.
                if (Character.isLowerCase(c)) lowerCase++;
                // Check for numbers letters.
                if (c >= '0' && c <= '9') numbers++;
            }

            String SPECIAL_CHARS_REGEX = "[!@#$%^&*()\\[\\]|;',./{}\\\\:\"<>?]";
            int specials = passwordNuevo.split(SPECIAL_CHARS_REGEX, -1).length - 1;

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
                    ) {
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
            } else {
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
        if (result.hasErrors()) {
            LOGGER.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
            return "passwd-edit2";
        }

        Boolean validatePasswdNew;
        if (passwordNuevo.equals(passwordNuevoConf)) {
            validatePasswdNew = true;
        } else {
            validatePasswdNew = false;
        }

        Boolean validatePassOld;
        String passwdAnteriorEnc = DigestUtils.sha1Hex(passwordAnteriorDigitada);
        if (passwdAnteriorEnc.equals(usuario.getPassword())) {
            validatePassOld = true;
        } else {
            validatePassOld = false;
        }

        if (validatePasswdNew != false && validatePassOld != false) {
            usuario.setPassword(DigestUtils.sha1Hex(passwordNuevo));
            usuarioRepo.save(usuario);
            HistoricoPassword historico = new HistoricoPassword();
            historico.setId(UUID.randomUUID());
            historico.setPassword(usuario.getPassword());
            historico.setUsuario(usuario);
            historico.setFechaRegistro(new Date());
            historicoPasswordRepo.save(historico);
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                    messages.getMessage("passwd.action.edit.success", new Object[]{usuario.getUsername()}, locale));
            return String.format("redirect:%s", CONTROLLER_PATH);
        }

        if (validatePasswdNew && validatePassOld.equals(false)) {
            model.addAttribute("error", "Valide contraseña actual");
        } else if (validatePassOld && validatePasswdNew.equals(false)) {
            model.addAttribute("error", "Contraseña nueva no coicide con la confirmación");
        } else if (validatePasswdNew && validatePassOld) {
            model.addAttribute("error", "Informacion no valida");
        }

        return "passwd-edit2";
    }


    @PostMapping(CONTROLLER_PATH_RESET_PASSWD)
    public String resetPasswd(@ModelAttribute("usuario") Usuario usuario,
                              BindingResult result,
                              @RequestParam("password") String passwordNuevo,
                              @RequestParam("passwordNuevoConf") String passwordNuevoConf,
                              RedirectAttributes redirectAttributes,
                              Locale locale, Model model) throws IOException {

        if (!usuario.getPassword().isEmpty()
                && usuario.getPassword() != null) {
            //Cantidad de caracteres especiales
            // int specials = usuario.getPassword().split(Constants.SPECIAL_CHARS_REGEX, -1).length - 1;
            //Cantidad de mayusculas, minusculas y Numeros
            int upperCase = 0;
            int lowerCase = 0;
            int numbers = 0;

            for (char c : passwordNuevo.toCharArray()) {
                // Check for uppercase letters.
                if (Character.isUpperCase(c)) upperCase++;
                // Check for lowercase letters.
                if (Character.isLowerCase(c)) lowerCase++;
                // Check for numbers letters.
                if (c >= '0' && c <= '9') numbers++;
            }

            String SPECIAL_CHARS_REGEX = "[!@#$%^&*()\\[\\]|;',./{}\\\\:\"<>?]";
            int specials = passwordNuevo.split(SPECIAL_CHARS_REGEX, -1).length - 1;

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
                    ) {
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
            } else {
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
        if (result.hasErrors()) {
            LOGGER.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
            return "passwd-edit2";
        }

        Boolean validatePasswdNew;
        if (passwordNuevo.equals(passwordNuevoConf)) {
            validatePasswdNew = true;
        } else {
            validatePasswdNew = false;
        }

        if (validatePasswdNew != false) {
            usuario.setPassword(DigestUtils.sha1Hex(passwordNuevo));
            usuarioRepo.save(usuario);
            HistoricoPassword historico = new HistoricoPassword();
            historico.setId(UUID.randomUUID());
            historico.setPassword(usuario.getPassword());
            historico.setUsuario(usuario);
            historico.setFechaRegistro(new Date());
            historicoPasswordRepo.save(historico);
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                    messages.getMessage("passwd.action.edit.success", new Object[]{usuario.getUsername()}, locale));
            return String.format("redirect:%s", CONTROLLER_PATH);
        }
        if (validatePasswdNew.equals(false)) {
            model.addAttribute("error", "Contraseña nueva no coicide con la confirmación");
        }
        return "passwd-reset2";
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
