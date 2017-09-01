package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Permiso;
import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.aut.UsuarioRepo;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.trans.BaseTableReferenceRepo;
import co.gov.movilidadbogota.sipa.trans.SipaFieldTableReference;
import co.gov.movilidadbogota.sipa.trans.SipaTableReference;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.lang.reflect.Field;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Control encargado de la gestion de las tablas de referencia
 *
 * @author lorenzo.pinango
 */
@Controller
public class TablaReferenciaController extends BaseController {
    public static final String CONTROLLER_PATH = "/tablaReferencia";
    public static final String CONTROLLER_PATH_LIST = CONTROLLER_PATH + "";
    public static final String CONTROLLER_PATH_EDIT = CONTROLLER_PATH + "/edit";
    public static final String CONTROLLER_PATH_EXPORT = CONTROLLER_PATH + "/export";

    private static final Logger LOGGER = LoggerFactory.getLogger(TablaReferenciaController.class);

    @Autowired
    MessageSource messages;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    UsuarioRepo usuarioRepo;

    /**
     * Lista todas las tablas de referencia
     */
    @PreAuthorize("hasAuthority('" + Permiso.LISTAR_TABLA_REFERENCIA + "')")
    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String list(Model model, Locale locale) {
        try {
            LOGGER.info("Buscar las tablas de referencia");
            Reflections reflections = new Reflections("co.gov.movilidadbogota.sipa.data");
            Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(SipaTableReference.class);
            Map<String, String> listTableReference = new HashMap<String, String>();
            listTableReference.put(
                    Constants.LIST_TO_MAP_EMPTY_KEY,
                    messages.getMessage(
                            Constants.LIST_TO_MAP_EMPTY_VALUE_MESSAGE_CODE,
                            new Object[0],
                            locale
                    ));
            for (Class<?> clazz : annotated) {
                SipaTableReference anotacion = clazz.getAnnotation(SipaTableReference.class);
                if (anotacion != null)
                    listTableReference.put(clazz.getName(), anotacion.name());
            }
            model.addAttribute("tablasReferencia", listTableReference);
            model.addAttribute("tablaReferencia", "");
            model.addAttribute("inicioVigencia", "");
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "tabla-referencia-list";
    }

    /**
     * Permite exportar la data de una entidad en un archivo excel
     */
    @RequestMapping(value = CONTROLLER_PATH_EXPORT + "/{clase:.+}", method = RequestMethod.GET)
    public <T, ID extends Serializable, X extends BaseTableReferenceRepo<T, ID>>
    ResponseEntity<?> export(@PathVariable("clase") String clase) {

        try {
            //Se obtiene la clase y la data
            Class<T> clazz = (Class<T>) Class.forName(clase);
            SipaTableReference anotacion = clazz.getAnnotation(SipaTableReference.class);
            // Se obtiene repositorio
            String repoString = anotacion.repository();
            Class<X> repoClass = (Class<X>) Class.forName(repoString);
            BaseTableReferenceRepo<T, ID> repository = applicationContext.getBean(repoClass);
            // Se inicializa excel
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet();
            int rowNum = 0;
            // Se obtiene toda la data
            List<T> datos = repository.findAllByFinVigenciaNull();
            //Se obtienen los campos marcados para exportar
            SipaFieldTableReference anotacionCampo = null;
            List<Field> exportFields = new ArrayList<>();
            Field[] allFields = clazz.getDeclaredFields();
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Field field : allFields) {
                if (field.isAnnotationPresent(SipaFieldTableReference.class)) {
                    exportFields.add(field);
                    anotacionCampo = field.getAnnotation(SipaFieldTableReference.class);
                    Cell cell = row.createCell(colNum++);
                    cell.setCellValue(anotacionCampo.title());
                }
            }
            //Se pinta los datos en el excel
            for (T obj : datos) {
                row = sheet.createRow(rowNum++);
                colNum = 0;
                for (Field field : obj.getClass().getDeclaredFields()) {
                    if (exportFields.contains(field)) {
                        Cell cell = row.createCell(colNum++);
                        cell.setCellValue(getValorField(obj, field));
                    }
                }
            }
            // Se genera respuesta con el excel generado
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            workbook.close();
            InputStream input = new ByteArrayInputStream(baos.toByteArray());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Disposition", "attachment; filename=export.xlsx");
            InputStreamResource resource = new InputStreamResource(input);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(baos.toByteArray().length)
                    .contentType(MediaType.parseMediaType(Constants.MIME_TYPE_XLSX))
                    .body(resource);
        } catch (Exception e) {
            String message;
            if (e instanceof ClassNotFoundException)
                message = String.format("No se encontró alguna de las clases involucradas en " +
                        "la exportacion de los valores de la clase %s", clase);
            if (e instanceof NoSuchMethodException)
                message = String.format("Ocurrió un error con alguno de los métodos getters " +
                        "de la clase %s", clase);
            else
                message = "Ocurrió un error exportando los valores a excel";
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(message);
        }
    }

    /**
     * Permite obtener el valor de un campo de una entidad
     */
    private String getValorField(Object objTable, Field field) throws Exception {
        String valor;
        String[] splitRoute;
        String nameField = field.getName();
        SipaFieldTableReference anotacionCampo = field.getAnnotation(SipaFieldTableReference.class);
        String fieldRoute = anotacionCampo.fieldRoute();
        if (!fieldRoute.isEmpty()) {
            Object obj = objTable.getClass().getMethod("get" +
                    WordUtils.capitalize(nameField))
                    .invoke(objTable);
            splitRoute = fieldRoute.split("[.]");
            String nameLastMethod;
            if (splitRoute.length > 0) {
                for (int i = 0; i < (splitRoute.length - 1); i++) {
                    obj = obj.getClass().getMethod(String.format("get%s",
                            WordUtils.capitalize(splitRoute[i])))
                            .invoke(obj);
                }
                nameLastMethod = splitRoute[splitRoute.length - 1];
            } else
                nameLastMethod = fieldRoute;
            valor = obj.getClass().getMethod(String.format("get%s",
                    WordUtils.capitalize(
                            nameLastMethod)))
                    .invoke(obj).toString();
        } else {
            valor = objTable.getClass().getMethod(String.format("get%s",
                    WordUtils.capitalize(nameField)))
                    .invoke(objTable).toString();
        }
        return valor;
    }

    /**
     * Se raliza las validaciones de tipo de dato y de negocio.
     * Se persiste una tabla de referencia {@link Object}
     *
     * @param documento
     * @param model
     * @param redirectAttributes
     * @param locale
     * @param principal
     * @return en caso de no pasar las validaciones de tipo de dato y de negocio se retorna al formulario de registro
     * en caso contrario se persiste un {@link Object}
     */
    @PostMapping(CONTROLLER_PATH_LIST)
    public <T, ID extends Serializable, X extends BaseTableReferenceRepo<T, ID>> String persistirTablaReferencia(
            @RequestParam(value = "inicioVigencia") String inicioVigencia,
            @RequestParam(value = "tablasReferencia") String tablaReferencia,
            @RequestParam(value = "documento") MultipartFile documento,
            Model model,
            RedirectAttributes redirectAttributes,
            Locale locale,
            Principal principal) {

        List<T> registros = new ArrayList<>();
        Class<T> clazz = null;
        SipaTableReference anotacion = null;
        BaseTableReferenceRepo<T, ID> repository = null;
        Field[] allFields = null;
        List<Field> importFields = new ArrayList<>();
        List<Field> fieldsConstants = new ArrayList<>();
        Date fechaInicioVigencia = null;
        try {
            Boolean error;
            error = false;
            if (inicioVigencia.isEmpty() || inicioVigencia.equals("____-__-__ __:__")) {
                model.addAttribute("errorInicioVigencia", messages.getMessage(
                        "tablaReferencia.fecha.required",
                        new Object[0],
                        locale
                ));
                error = true;
            }
            if (tablaReferencia.isEmpty()) {
                model.addAttribute("errorTablaReferencia", messages.getMessage(
                        "tablaReferencia.tabla.required",
                        new Object[0],
                        locale
                ));
                error = true;
            }
            if (documento.isEmpty()) {
                model.addAttribute("errorDocumento", messages.getMessage(
                        "tablaReferencia.documento.required",
                        new Object[0],
                        locale
                ));
                error = true;
            }
            if (!documento.isEmpty()
                    && !Arrays.asList(Constants.CONTENIDO_XLSX).contains(documento.getContentType())) {
                model.addAttribute("errorDocumento", messages.getMessage(
                        "documento.error.contentType",
                        new Object[0],
                        locale
                ));
                error = true;
            }
            if (!error) {
                // Se lee y en caso de error se retorna a la vista
                try {
                    //Se obtiene la clase y la data
                    clazz = (Class<T>) Class.forName(tablaReferencia);
                    anotacion = clazz.getAnnotation(SipaTableReference.class);
                    // Se obtiene repositorio
                    String repoString = anotacion.repository();
                    Class<X> repoClass = (Class<X>) Class.forName(repoString);
                    repository = applicationContext.getBean(repoClass);

                    //Se obtienen los campos marcados para importar
                    allFields = clazz.getDeclaredFields();

                    for (Field field : allFields) {
                        if (field.isAnnotationPresent(SipaFieldTableReference.class))
                            importFields.add(field);
                        if (field.getType().equals(String.class) && ReflectionUtils.isPublicStaticFinal(field))
                            fieldsConstants.add(field);
                    }
                    // Campos adicionales
                    SimpleDateFormat fmt = new SimpleDateFormat(Constants.DATE_TIME_FORMAT_PATTERN);
                    fechaInicioVigencia = fmt.parse(inicioVigencia);
                    //Se busca el usuario
                    Usuario usuario = null;
                    if (principal != null)
                        usuario = usuarioRepo.findOneByUsername(principal.getName());
                    registros = readFile(clazz, repository, importFields, documento, fechaInicioVigencia, usuario);
                    //Se valida que las constantes declaradas en la clase se encuentren en el archivo
                    validateConstants(fieldsConstants, (List<Object>) registros);
                } catch (Exception ex) {
                    if (ex instanceof IOException)
                        model.addAttribute("errorDocumento", "Ocurrio un error con la lectura del archivo");
                    else
                        model.addAttribute("errorDocumento", ex.getMessage());
                    error = true;
                }
            }
            if (error) {
                model.addAttribute("tablaReferencia", tablaReferencia);
                model.addAttribute("inicioVigencia", inicioVigencia);
                Reflections reflections = new Reflections("co.gov.movilidadbogota.sipa.data");
                Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(SipaTableReference.class);
                Map<String, String> listTableReference = new HashMap<String, String>();
                listTableReference.put(
                        Constants.LIST_TO_MAP_EMPTY_KEY,
                        messages.getMessage(
                                Constants.LIST_TO_MAP_EMPTY_VALUE_MESSAGE_CODE,
                                new Object[0],
                                locale
                        ));
                for (Class<?> clase : annotated) {
                    anotacion = clase.getAnnotation(SipaTableReference.class);
                    if (anotacion != null)
                        listTableReference.put(clase.getName(), anotacion.name());
                }
                model.addAttribute("tablasReferencia", listTableReference);
                return "tabla-referencia-list";
            }
            // Se realiza guardado
            guardarRegistros(registros, clazz, repository, inicioVigencia);
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            return "tabla-referencia-list";
        }
        redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                messages.getMessage("tablaReferencia.action.edit.success",
                        new Object[]{anotacion.name()}, locale));
        return String.format("redirect:%s", CONTROLLER_PATH_LIST);
    }

    /**
     * Funcion que permite leer el archivo excel con los registros de la tabla
     */
    private <T, ID extends Serializable, X extends BaseTableReferenceRepo<T, ID>>
    List<T> readFile(Class<T> clazz, BaseTableReferenceRepo<T, ID> repository, List<Field> importFields,
                     MultipartFile archivo, Date inicioVigencia, Usuario usuario) throws Exception {

        //Se obtienen los campos marcados para importar
        SipaFieldTableReference anotacionCampo = null;
        Long version = repository.getCurrentVersion();
        if (version == null)
            version = Long.valueOf(1);
        else
            version = version + Long.valueOf(1);
        List<T> datos = new ArrayList();
        byte[] byteArr = archivo.getBytes();
        InputStream inputStream = new ByteArrayInputStream(byteArr);

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = (Row) iterator.next();
            // Not creating country object for header
            if (nextRow.getRowNum() == 0)
                continue;

            T object = getObjectReadFile(clazz, importFields, nextRow);
            // Campos adicionales
            try {
                clazz.getMethod("setIdentifier", UUID.class).invoke(object, UUID.randomUUID());
                clazz.getMethod("setInicioVigencia", Date.class).invoke(object, inicioVigencia);
                clazz.getMethod("setFechaCreacion", Date.class).invoke(object, new Date());
                clazz.getMethod("setUsuario", Usuario.class).invoke(object, usuario);
                clazz.getMethod("setVersion", Long.class).invoke(object, version);
            } catch (Exception e) {
                throw new NoSuchMethodException(String.format("Ocurrió un error asociado a los métodos setter de los " +
                                "campos de auditorio (identifier, inicioVigencia, fechaCreacion, usuario y " +
                                "version), revisar que la clase %s extiende de la clase BaseTableReference",
                        clazz.getSimpleName()));
            }
            datos.add(object);
        }
        if (LOGGER.isDebugEnabled())
            LOGGER.debug(String.format("Cantidad de registros leidos: %s", datos.size()));
        workbook.close();
        inputStream.close();
        return datos;
    }

    /**
     * Funcion que permite retorna un objeto de la lectura de archivo
     */
    private <T, ID extends Serializable, X extends BaseTableReferenceRepo<T, ID>> T getObjectReadFile(Class<T> clazz, List<Field> importFields, Row nextRow) throws InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Boolean findField;
        SipaFieldTableReference anotacionCampo;
        T object = null;
        object = clazz.newInstance();
        findField = false;
        Iterator cellIterator = nextRow.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = (Cell) cellIterator.next();
            int columnIndex = cell.getColumnIndex();
            for (Field field : importFields) {
                anotacionCampo = field.getAnnotation(SipaFieldTableReference.class);
                if ((columnIndex + 1) == anotacionCampo.numberColumn()) {
                    findField = true;
                    String valor = cell.getStringCellValue();
                    if (field.isAnnotationPresent(javax.validation.constraints.NotNull.class))
                        if (valor.isEmpty())
                            throw new NullPointerException(String.format("Es requerido el valor para el campo %s en la fila %d",
                                    anotacionCampo.title(), nextRow.getRowNum()));
                    if (anotacionCampo.repository().isEmpty()) {
                        try {
                            clazz.getMethod(String.format("set%s",
                                    WordUtils.capitalize(field.getName())),
                                    field.getType()).invoke(object, ControllerUtils.stringToObject(field.getType(), valor));
                        } catch (Exception e) {
                            throw new NoSuchMethodException(String.format("Ocurrió un error asociado al método " +
                                            "setter set%s de la clase %s", WordUtils.capitalize(field.getName()),
                                    clazz.getSimpleName()));
                        }
                    } else {
                        Class<X> repoFieldClass = null;
                        repoFieldClass = getRepoBase(anotacionCampo);
                        BaseTableReferenceRepo<T, ID> repoField = applicationContext.getBean(repoFieldClass);
                        Object objectData = repoField.findByIdAndFecha(valor, new Date());
                        if (objectData != null)
                            try {
                                clazz.getMethod(String.format("set%s",
                                        WordUtils.capitalize(field.getName())
                                ), field.getType()).invoke(object, objectData);
                            } catch (Exception e) {
                                throw new NoSuchMethodException(String.format("Ocurrió un error asociado al método " +
                                                "setter set%s de la clase %s", WordUtils.capitalize(field.getName()),
                                        clazz.getSimpleName()));
                            }
                        else
                            throw new NullPointerException(String.format("No se encontró registro vigente para el campo %s " +
                                            "con valor %s en la fila %d", anotacionCampo.title(),
                                    valor, nextRow.getRowNum()));
                    }
                }
                if (!findField)
                    throw new NullPointerException(String.format("La columna %d del archivo  no se encuentra mapeada " +
                            "en la clase %s", columnIndex + 1, clazz.getSimpleName()));
            }
        }
        return object;
    }

    /**
     * Funcion que permite retorna el repositorio extendido de BaseTableReferenceRepo
     */
    private <T, ID extends Serializable, X extends BaseTableReferenceRepo<T, ID>> Class<X> getRepoBase(SipaFieldTableReference anotacionCampo) throws ClassNotFoundException {
        Class<X> repoFieldClass;
        try {
            repoFieldClass = (Class<X>) Class.forName(anotacionCampo.repository());
        } catch (Exception e) {
            throw new ClassNotFoundException(String.format("No se pudo encontrar la " +
                    "clase %s", anotacionCampo.repository()));
        }
        return repoFieldClass;
    }

    /**
     * Funcion que permite guardar los registros
     */
    private <T, ID extends Serializable, X extends BaseTableReferenceRepo<T, ID>>
    void guardarRegistros(List<T> registros, Class<T> clazz, BaseTableReferenceRepo<T, ID> repository,
                          String inicioVigencia) throws Exception {
        //Se cierran los registros vigentes
        SimpleDateFormat fmt = new SimpleDateFormat(Constants.DATE_TIME_FORMAT_PATTERN);
        Date fecha = fmt.parse(inicioVigencia);
        repository.setFinVigenciaByVigentes(fecha);

        //Se persisten los registros
        for (T object : registros) {
            repository.save(object);
        }
    }

    /**
     * Valida que las constantes se encuentren en el archivo
     */
    private void validateConstants(List<Field> fieldsConstants, List<Object> registros) throws Exception {
        Boolean encontrado;
        String valorConstant = null;
        String valor = null;
        if (fieldsConstants.size() > 0 && registros.size() > 0) {
            for (Field field : fieldsConstants) {
                encontrado = false;
                try {
                    valorConstant = field.get(null).toString().trim();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                for (Object object : registros) {
                    try {
                        valor = object.getClass().getMethod("getId")
                                .invoke(object).toString().trim();
                    } catch (Exception e) {
                        throw new NoSuchMethodException(String.format("Ocurrió un error asociado al método " +
                                "getId de la clase %s en la validacion de las constantes", object.getClass().getSimpleName()));
                    }
                    if (valor.equals(valorConstant)) {
                        encontrado = true;
                        continue;
                    }
                }
                if (!encontrado)
                    throw new RuntimeException(String.format("La constante %s, de la clase %s cuyo valor es %s " +
                                    "no se encuentra contenido en el archivo excel", field.getName(),
                            field.getClass().getSimpleName(), valorConstant));
            }
        }
    }
}
