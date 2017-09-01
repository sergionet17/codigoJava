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
 * Control encargado de la gestion de los roles del sistema
 *
 * @author lorenzo.pinango
 */
@Controller
@RequestMapping(value = RolController.CONTROLLER_PATH)
public class RolController extends BaseController {

    public static final String CONTROLLER_PATH = "/rol";
    public static final String CONTROLLER_PATH_LIST = "";
    public static final String CONTROLLER_PATH_CREATE = "/create";
    public static final String CONTROLLER_PATH_EDIT = "/edit";
    public static final String CONTROLLER_PATH_ESTADO = "/estado";

    private static final Logger LOGGER = LoggerFactory.getLogger(RolController.class);

    @Autowired
    PermisoRepo permisoRepo;
    @Autowired
    RolRepo rolRepo;
    @Autowired
    PerfilRepo perfilRepo;
    @Autowired
    MessageSource messages;

    /**
     * Lista todos los roles
     */
    @PreAuthorize("hasAuthority('" + Permiso.LISTAR_ROL + "')")
    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String list(Model model) {
        try {
            LOGGER.info("Buscar los roles");
            model.addAttribute("roles", rolRepo.findAll());
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "rol-list";
    }

    /**
     * Se inicializa el formulario para la captura de la información del rol
     *
     * @return Se retorna a la pagina de registro al rol
     */
    @PreAuthorize("hasAuthority('" + Permiso.CREAR_ROL + "')")
    @GetMapping(value = CONTROLLER_PATH_CREATE)
    public String create(Model model) {
        try {
            //Inicializa la forma para realizar el registro de un rol
            model.addAttribute("rolForm", new Rol());
            model.addAttribute("permisos", permisoRepo.findAll());
            model.addAttribute("editar", 0);
            LOGGER.info("Formulario de registro de un rol");

        } catch (Exception e) {

            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "rol-create";
    }

    /**
     * Se raliza las validaciones de tipo de dato y de negocio.
     * Se persiste un rol {@link Rol}
     *
     * @param rol
     * @param result
     * @param permisos
     * @param editar
     * @param model
     * @param redirectAttributes
     * @param locale
     * @return en caso de no pasar las validaciones de tipo de dato y de negocio se retorna al formulario de registro
     * en caso contrario se persiste un {@link Rol}
     */
    @PostMapping(CONTROLLER_PATH_LIST)
    public String persistirRol(
            @Valid @ModelAttribute("rolForm") Rol rol,
            BindingResult result,
            @RequestParam("permisosAsString") String permisos,
            @RequestParam("editar") Integer editar,
            Model model,
            RedirectAttributes redirectAttributes,
            Locale locale) {

        LOGGER.info(String.format("Los permisos seleccionados son: %s", permisos));
        String[] split = permisos.split(",");
        List<String> selectedValues = new ArrayList<>();
        List<Permiso> permisosList = new ArrayList<>();
        if (!permisos.equals("")) {
            for (String str : split) {
                Permiso permiso = new Permiso(str);
                permisosList.add(permiso);
                selectedValues.add(str);
            }
        }
        rol.setPermisos(permisosList);
        try {
            // Si existen errores de formato retorna al mismo formulario
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("Se realiza las validaciones de negocio");
            new RolValidator().validate(rol, result);
            if (selectedValues.size() > 0) {
                if (editar == 0) {
                    if (rolRepo.countByEqualPermisos(selectedValues, selectedValues.size()) > 0)
                        result.rejectValue("permisos", "rol.permisos.validated");
                } else {
                    if (rolRepo.countByEqualPermisosNotRol(selectedValues, selectedValues.size(), rol.getId().toString()) > 0)
                        result.rejectValue("permisos", "rol.permisos.validated");
                }
            }
            if (result.hasErrors()) {
                if (LOGGER.isDebugEnabled())
                    LOGGER.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
                //Se carga todos los permisos
                List<Permiso> permisosDisponibles = permisoRepo.findAll();
                permisosDisponibles.removeAll(permisosList);
                model.addAttribute("permisos", permisosDisponibles);
                model.addAttribute("permisosSeleccionados", permisosList);
                model.addAttribute("editar", editar);
                return "rol-create";
            }
            if (editar == 0)
                rol.setId(UUID.randomUUID());
            //Se guarda la información de la forma en el objeto Rol
            rolRepo.save(rol);

        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }

        if (editar == 0) {
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                    messages.getMessage("rol.action.create.success", new Object[]{rol.getName()}, locale));
            return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
        } else {
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                    messages.getMessage("rol.action.edit.success", new Object[]{rol.getName()}, locale));
            return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
        }
    }

    /**
     * Se inicializa el formulario para editar la información del rol
     *
     * @return Se retorna a la pagina de editar al rol
     */
    @PreAuthorize("hasAuthority('" + Permiso.MODIFICAR_ROL + "')")
    @RequestMapping(path = CONTROLLER_PATH_EDIT + "/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") UUID id, Model model) {
        try {
            //Inicializa la forma para realizar la modificacion de un rol
            Rol rol = rolRepo.findById(id);
            model.addAttribute("rolForm", rol);
            //Se carga todas los permisos
            List<Permiso> permisosList = rol.getPermisos();
            List<Permiso> permisosDisponibles = permisoRepo.findAll();
            permisosDisponibles.removeAll(permisosList);
            model.addAttribute("permisos", permisosDisponibles);
            model.addAttribute("permisosSeleccionados", permisosList);
            model.addAttribute("editar", 1);
            LOGGER.info("Formulario de modificacion de un rol");

        } catch (Exception e) {

            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "rol-create";
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
     * Funcionalidad para cambiar de estado el rol
     *
     * @return Se retorna la pagina para listar roles
     */
    @RequestMapping(path = CONTROLLER_PATH_ESTADO + "/{id}", method = RequestMethod.GET)
    public String cambiarEstado(@PathVariable("id") UUID id,
                                RedirectAttributes redirectAttributes,
                                Model model, Locale locale) {
        try {
            //Se obtiene el rol
            Rol rol = rolRepo.findById(id);
            Boolean estado = !rol.getActivo();
            //Se valida la cantidad de perfiles asociados al usuario
            if (perfilRepo.countByPerfilRolId(id) > 0 && !estado)
                redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_ERROR,
                        messages.getMessage("rol.action.status.error", new Object[]{rol.getName()}, locale));
            else {
                rol.setActivo(estado);
                rolRepo.save(rol);
                redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                        messages.getMessage("rol.action.status.success", new Object[]{rol.getName()}, locale));
            }

        } catch (Exception e) {

            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
    }
}
