package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.*;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Control encargado de la gestion de los perfiles de usuario del sistema
 *
 * @author lorenzo.pinango
 */
@Controller
@RequestMapping(value = PerfilController.CONTROLLER_PATH)
public class PerfilController extends BaseController {

    public static final String CONTROLLER_PATH = "/perfil";
    public static final String CONTROLLER_PATH_LIST = "";
    public static final String CONTROLLER_PATH_CREATE = "/create";
    public static final String CONTROLLER_PATH_EDIT = "/edit";
    public static final String CONTROLLER_PATH_ESTADO = "/estado";

    private static final Logger LOGGER = LoggerFactory.getLogger(PerfilController.class);

    @Autowired
    PerfilRepo perfilRepo;
    @Autowired
    RolRepo rolRepo;
    @Autowired
    UsuarioRepo usuarioRepo;
    @Autowired
    MessageSource messages;

    /**
     * Lista todos los perfiles
     */
    @PreAuthorize("hasAuthority('" + Permiso.LISTAR_PERFIL + "')")
    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String list(Model model) {
        try {
            LOGGER.info("Buscar los perfiles");
            model.addAttribute("perfiles", perfilRepo.findAll());
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "perfil-list";
    }

    /**
     * Se inicializa el formulario para la captura de la información del perfil
     *
     * @return Se retorna a la pagina de registro al perfil
     */
    @PreAuthorize("hasAuthority('" + Permiso.CREAR_PERFIL + "')")
    @GetMapping(value = CONTROLLER_PATH_CREATE)
    public String create(Model model) {
        try {
            //Inicializa la forma para realizar el registro de un perfil
            model.addAttribute("perfilForm", new Perfil());
            model.addAttribute("roles", rolRepo.findAllActivos());
            model.addAttribute("editar", 0);
            LOGGER.info("Formulario de registro de un perfil");

        } catch (Exception e) {

            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "perfil-create";
    }

    /**
     * Se raliza las validaciones de tipo de dato y de negocio.
     * Se persiste un perfil {@link Perfil}
     *
     * @param perfil
     * @param result
     * @param roles
     * @param editar
     * @param model
     * @param redirectAttributes
     * @param locale
     * @return en caso de no pasar las validaciones de tipo de dato y de negocio se retorna al formulario de registro
     * en caso contrario se persiste un {@link Perfil}
     */
    @PostMapping(CONTROLLER_PATH_LIST)
    public String persistirPerfil(
            @Valid @ModelAttribute("perfilForm") Perfil perfil,
            BindingResult result,
            @RequestParam("rolesAsString") String roles,
            @RequestParam("editar") Integer editar,
            Model model,
            RedirectAttributes redirectAttributes,
            Locale locale) {

        LOGGER.info(String.format("Los roles seleccionados son: %s", roles));
        String[] split = roles.split(",");
        List<UUID> selectedValues = new ArrayList<>();
        List<Rol> rolesList = new ArrayList<>();
        for (String str : split) {
            if (!str.isEmpty()) {
                Rol rol = rolRepo.findById(UUID.fromString(str));
                rolesList.add(rol);
                selectedValues.add(UUID.fromString(str));
            }
        }
        perfil.setRoles(rolesList);
        try {
            // Si existen errores de formato retorna al mismo formulario
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("Se realiza las validaciones de negocio");
            new PerfilValidator().validate(perfil, result);
            if (selectedValues.size() > 0) {
                if (editar == 0) {
                    if (perfilRepo.countByEqualRoles(selectedValues, selectedValues.size()) > 0)
                        result.rejectValue("roles", "perfil.roles.validated");
                } else {
                    if (perfilRepo.countByEqualRolesNotPerfil(selectedValues, selectedValues.size(), perfil.getId().toString()) > 0)
                        result.rejectValue("roles", "perfil.roles.validated");
                }
            }
            if (result.hasErrors()) {
                if (LOGGER.isDebugEnabled())
                    LOGGER.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
                //Se carga todos los roles
                List<Rol> rolesDisponibles = rolRepo.findAllActivos();
                rolesDisponibles.removeAll(rolesList);
                model.addAttribute("roles", rolesDisponibles);
                model.addAttribute("rolesSeleccionados", rolesList);
                model.addAttribute("editar", editar);
                return "perfil-create";
            }
            if (editar == 0)
                perfil.setId(UUID.randomUUID());
            //Se guarda la información de la forma en el objeto Perfil
            perfilRepo.save(perfil);

        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }

        if (editar == 0) {
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                    messages.getMessage("perfil.action.create.success", new Object[]{perfil.getNombre()}, locale));
            return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
        } else {
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                    messages.getMessage("perfil.action.edit.success", new Object[]{perfil.getNombre()}, locale));
            return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
        }
    }

    /**
     * Se inicializa el formulario para editar la información del perfil
     *
     * @return Se retorna a la pagina de editar al perfil
     */
    @PreAuthorize("hasAuthority('" + Permiso.MODIFICAR_PERFIL + "')")
    @RequestMapping(path = CONTROLLER_PATH_EDIT + "/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") UUID id, Model model) {
        try {
            //Inicializa la forma para realizar la modificacion de un perfil
            Perfil perfil = perfilRepo.findOne(id);
            model.addAttribute("perfilForm", perfil);
            //Se carga todas los roles
            List<Rol> rolesList = perfil.getRoles();
            List<Rol> rolesDisponibles = rolRepo.findAllActivos();
            rolesDisponibles.removeAll(rolesList);
            model.addAttribute("roles", rolesDisponibles);
            model.addAttribute("rolesSeleccionados", rolesList);
            model.addAttribute("editar", 1);
            LOGGER.info("Formulario de modificacion de un perfil");

        } catch (Exception e) {

            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "perfil-create";
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
     * Funcionalidad para cambiar de estado el perfil
     *
     * @return Se retorna la pagina para listar perfiles
     */
    @RequestMapping(path = CONTROLLER_PATH_ESTADO + "/{id}", method = RequestMethod.GET)
    public String cambiarEstado(@PathVariable("id") UUID id,
                                RedirectAttributes redirectAttributes,
                                Model model, Locale locale) {
        try {
            //Se obtiene el perfil
            Perfil perfil = perfilRepo.findOne(id);
            Boolean estado = !perfil.getActivo();
            //Se valida la cantidad de perfiles asociados al usuario
            if (usuarioRepo.countByUsuarioPerfilId(id) > 0 && !estado)
                redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_ERROR,
                        messages.getMessage("perfil.action.status.error", new Object[]{perfil.getNombre()}, locale));
            else {
                perfil.setActivo(estado);
                perfilRepo.save(perfil);
                redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                        messages.getMessage("perfil.action.status.success", new Object[]{perfil.getNombre()}, locale));
            }

        } catch (Exception e) {

            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
    }
}
