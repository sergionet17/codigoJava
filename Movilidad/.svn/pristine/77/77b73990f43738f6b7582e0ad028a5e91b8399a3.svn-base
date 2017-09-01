package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Permiso;
import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.aut.UsuarioRepo;
import co.gov.movilidadbogota.sipa.data.gen.*;
import co.gov.movilidadbogota.sipa.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Controlador para administrar parametros
 *
 * @author lorenzo.pinango
 */
@Controller
public class ParametroController extends BaseController {
    public static final String CONTROLLER_PATH = "/parametro";
    public static final String CONTROLLER_PATH_LIST = CONTROLLER_PATH + "";
    public static final String CONTROLLER_PATH_EDIT = CONTROLLER_PATH + "/edit";
    public static final String CONTROLLER_PATH_PARAMS = CONTROLLER_PATH + "/parametros";
    public static final String CONTROLLER_PATH_DOWNLOAD = CONTROLLER_PATH + "/download";

    private static final Logger LOGGER = LoggerFactory.getLogger(ParametroController.class);

    @Autowired
    ParametroRepo parametroRepo;
    @Autowired
    CategoriaParametroRepo categoriaParametroRepo;
    @Autowired
    ValorParametroRepo valorParametroRepo;
    @Autowired
    ValorParametroFileRepo valorParametroFileRepo;
    @Autowired
    MessageSource messages;
    @Autowired
    UsuarioRepo usuarioRepo;
    @Autowired
    EntityManager em;

    @Autowired
    ApplicationContext applicationContext;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Lista todas los parametros
     */
    @PreAuthorize("hasAuthority('" + Permiso.LISTAR_PARAMETRO + "')")
    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String list(Model model) {
        try {
            LOGGER.info("Buscar las categorias");
            model.addAttribute("categorias", categoriaParametroRepo.findAllCategoriasPrimarias());
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "parametro-list";
    }

    /**
     * Se busca los parametros de acuerdo a una categoria {@link CategoriaParametro}
     *
     * @param clave representa el identificador de la categoria
     * @return Se retorna una coleccion de parametros de acuerdo a un Categoria
     */
    @GetMapping(value = CONTROLLER_PATH_PARAMS + "/{clave}", produces = "application/json")
    @ResponseBody
    public CategoriaParametro getParametros(@PathVariable("clave") String clave) {
        LOGGER.debug(String.format("Buscando la categoria %s", clave));
        try {
            return categoriaParametroRepo.findAllParametrosByClaveLastValue(clave);
        } catch (Exception e) {
            LOGGER.error(String.format("Mientras buscaba la categoria %s", clave), e);
            return null;
        }
    }

    /**
     * Se inicializa el formulario para editar el valor de un parametro
     *
     * @return Se retorna a la pagina de editar valor del parametro
     */
    @PreAuthorize("hasAuthority('" + Permiso.MODIFICAR_PARAMETRO + "')")
    @RequestMapping(path = CONTROLLER_PATH_EDIT + "/{clave}", method = RequestMethod.GET)
    public String edit(@PathVariable("clave") String clave, Model model, Locale locale) {
        try {
            //Inicializa la forma para realizar la modificacion del valor de un parametro
            ValorParametro valorParametro = valorParametroRepo.findValorVigente(clave);
            if (valorParametro == null) {
                Parametro parametro = parametroRepo.findOne(clave);
                valorParametro = new ValorParametro();
                valorParametro.setParametro(parametro);
                Date fecha = new Date();
                valorParametro.setFechaCreacion(fecha);
            }

            Parametro parametro = valorParametro.getParametro();
            String tipoParametro = parametro.getTipo();

            ParametroEditor editor;
            if (StringUtils.isEmpty(parametro.getEditor())) {
                editor = obtenerEditor(tipoParametro);
            } else {
                if (parametro.getEditor().startsWith("#")) {
                    try {
                        editor = applicationContext.getBean(parametro.getEditor().substring(1), ParametroEditor.class);
                    } catch (NoSuchBeanDefinitionException ex) {
                        throw new NoSuchBeanDefinitionException("No se encontró el Bean " + parametro.getEditor().substring(1));
                    }
                } else {
                    editor = obtenerEditor(tipoParametro);
                }
                Session session = em.unwrap(Session.class);
                editor.fillModel(model, session, parametro.getEditor(), messages, locale);
            }
            model.addAttribute("editor", editor);
            model.addAttribute("valorParametroForm", valorParametro);
            LOGGER.info("Formulario de modificacion del valor de un parametro");
        } catch (Exception e) {
            LOGGER.error("Error " + e.getMessage());
            model.addAttribute("error", e.getMessage());
        }
        return "parametro-edit";
    }

    /**
     * Obtiene el editor dependiendo del tipo de dato
     *
     * @return Objeto {@link ParametroEditor}
     */
    private ParametroEditor obtenerEditor(String tipo) {
        switch (tipo) {
            case "String":
                return new StringParametroEditor();
            case "Integer":
                return new IntegerParametroEditor();
            case "Decimal":
                return new DecimalParametroEditor();
            case "BigDecimal":
                return new BigDecimalParametroEditor();
            case "Date":
                return new DateParametroEditor();
            case "Datetime":
                return new DateTimeParametroEditor();
            case "Time":
                return new TimeParametroEditor();
            case "File":
                return new FileParametroEditor();
            case "Object":
                return new ObjectParametroEditor();
            default:
                return null;
        }
    }

    /**
     * Redirecciona en el caso de que se intente ingresar al editar sin id
     *
     * @return string
     */
    @RequestMapping(value = CONTROLLER_PATH_EDIT, method = RequestMethod.GET)
    public String editHandler() {
        return String.format("redirect:%s", CONTROLLER_PATH_LIST);
    }

    /**
     * Se raliza las validaciones de tipo de dato y de negocio.
     * Se persiste un valor {@link ValorParametroRepo}
     *
     * @param valorParametro
     * @param result
     * @param valorFile
     * @param model
     * @param redirectAttributes
     * @param locale
     * @param principal
     * @return en caso de no pasar las validaciones de tipo de dato y de negocio se retorna al formulario de registro
     * en caso contrario se persiste un {@link ValorParametroRepo}
     */
    @PostMapping(CONTROLLER_PATH_LIST)
    public String persistirValorParametro(
            @Valid @ModelAttribute("valorParametroForm") ValorParametro valorParametro,
            BindingResult result,
            @RequestParam(value = "valorFile", required = false) MultipartFile valorFile,
            Model model,
            RedirectAttributes redirectAttributes,
            Locale locale,
            Principal principal) {

        try {
            //Se busca el objecto parametro
            Parametro parametro = parametroRepo.findOne(valorParametro.getParametro().getClave());
            // Si existen errores de formato retorna al mismo formulario
            if (LOGGER.isDebugEnabled())
                LOGGER.debug(String.format("Se realizan las validaciones de negocio para " +
                        "el valor del parametro: %s", valorParametro.getParametro().getClave()));
            Date fechaRegistro = new Date();
            valorParametro.setFechaCreacion(fechaRegistro);
            valorParametro.setParametro(parametro);
            if (parametro.getTipo().equals("File")) {
                if (valorFile.isEmpty()) {
                    result.rejectValue("valor", "valorParametro.valor.file.required");
                }
            } else {
                new ValorParametroValidator().validate(valorParametro, result);
            }
            if (result.hasErrors()) {
                if (LOGGER.isDebugEnabled())
                    LOGGER.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
                String tipoParametro = parametro.getTipo();
                ParametroEditor editor;
                if (StringUtils.isEmpty(parametro.getEditor())) {
                    editor = obtenerEditor(tipoParametro);
                } else {
                    if (parametro.getEditor().startsWith("#")) {
                        try {
                            editor = applicationContext.getBean(parametro.getEditor().substring(1), ParametroEditor.class);
                        } catch (NoSuchBeanDefinitionException ex) {
                            throw new NoSuchBeanDefinitionException("No se encontró el Bean " + parametro.getEditor().substring(1));
                        }
                    } else {
                        editor = obtenerEditor(tipoParametro);
                    }
                    Session session = em.unwrap(Session.class);
                    editor.fillModel(model, session, parametro.getEditor(), messages, locale);
                }
                model.addAttribute("editor", editor);
                return "parametro-edit";
            }
            //Se busca el usuario
            Usuario usuario = null;
            if (principal != null)
                usuario = usuarioRepo.findOneByUsername(principal.getName());
            valorParametro.setUsuario(usuario);
            //Se cierra el parametro anterior
            ValorParametro valorParametroAnterior = valorParametroRepo.findValorVigente(
                    valorParametro.getParametro().getClave());
            if (valorParametroAnterior != null) {
                valorParametroAnterior.setFinVigencia(valorParametro.getInicioVigencia());
                //Se cierra parametro
                valorParametroRepo.save(valorParametroAnterior);
            }
            valorParametro.setId(UUID.randomUUID());
            //Se guarda la información de la forma en el objeto ValorParametro
            valorParametroRepo.save(valorParametro);
            //En caso de ser tipo file se guarda el archivo
            if (valorParametro.getParametro().getTipo().equals("File")) {
                ValorParametroFile archivo = new ValorParametroFile();
                archivo.setId(UUID.randomUUID());
                archivo.setValorParametro(valorParametro);
                archivo.setArchivo(valorFile.getBytes());
                archivo.setMimeType(valorFile.getContentType());
                valorParametroFileRepo.save(archivo);
            }

        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }

        redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                messages.getMessage("valorParametro.action.edit.success",
                        new Object[]{valorParametro.getParametro().getClave()}, locale));
        return String.format("redirect:%s", CONTROLLER_PATH_LIST);
    }

    /**
     * Permite renderizar un archivo para ser descargado
     *
     * @param id
     * @return renderizado del documento
     */
    @RequestMapping(path = CONTROLLER_PATH_DOWNLOAD + "/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@PathVariable("id") UUID id) throws IOException {
        ValorParametroFile archivo = valorParametroFileRepo.findByValorParametroId(id);
        InputStream input = new ByteArrayInputStream(archivo.getArchivo());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        InputStreamResource resource = new InputStreamResource(input);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(archivo.getArchivo().length)
                .contentType(MediaType.parseMediaType(archivo.getMimeType()))
                .body(resource);
    }

}
