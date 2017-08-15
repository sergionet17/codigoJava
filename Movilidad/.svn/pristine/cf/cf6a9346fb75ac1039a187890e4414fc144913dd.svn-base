package co.gov.movilidadbogota.sipa.conf;

import co.gov.movilidadbogota.sipa.data.aut.*;
import co.gov.movilidadbogota.sipa.data.biz.coa.*;
import co.gov.movilidadbogota.sipa.data.biz.comp.*;
import co.gov.movilidadbogota.sipa.data.biz.gen.*;
import co.gov.movilidadbogota.sipa.data.biz.num.EstadoNumeroComparendo;
import co.gov.movilidadbogota.sipa.data.biz.num.EstadoNumeroComparendoRepo;
import co.gov.movilidadbogota.sipa.data.biz.trans.DocumentoInfraccion;
import co.gov.movilidadbogota.sipa.data.biz.trans.DocumentoInfraccionRepo;
import co.gov.movilidadbogota.sipa.data.biz.trans.TipoPruebaProcesoContravencional;
import co.gov.movilidadbogota.sipa.data.biz.trans.TipoPruebaProcesoContravencionalRepo;
import co.gov.movilidadbogota.sipa.data.doc.*;
import co.gov.movilidadbogota.sipa.data.fin.EstadoPago;
import co.gov.movilidadbogota.sipa.data.fin.EstadoPagoRepo;
import co.gov.movilidadbogota.sipa.data.fin.TipoArchivoPagos;
import co.gov.movilidadbogota.sipa.data.fin.TipoArchivoPagosRepo;
import co.gov.movilidadbogota.sipa.data.gen.*;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumentoRepo;
import co.gov.movilidadbogota.sipa.data.loc.*;
import co.gov.movilidadbogota.sipa.data.persona.Persona;
import co.gov.movilidadbogota.sipa.data.persona.PersonaRepo;
import co.gov.movilidadbogota.sipa.data.persona.TipoPersona;
import co.gov.movilidadbogota.sipa.data.persona.TipoPersonaRepository;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoService;
import co.gov.movilidadbogota.sipa.trans.BaseTableReferenceRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.opencsv.CSVReader;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Clase que se encarga de la carga de datos iniciales del sistema en la base de
 * datos.
 *
 * @author arturo.cruz
 */
@Component
public class DatabaseInitialLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitialLoader.class);
    @Autowired
    PaisRepo paisRepo;
    @Autowired
    DepartamentoRepo departamentoRepo;
    @Autowired
    MunicipioRepo municipioRepo;
    @Value("${sipa.loadInitialData:false}")
    private
    Boolean loadInitialData;
    @Autowired
    private
    UsuarioRepo usuarioRepo;
    @Autowired
    private
    PerfilRepo perfilRepo;
    @Autowired
    private
    PermisoRepo permisoRepo;
    @Autowired
    private
    RolRepo rolRepo;
    @Autowired
    private
    PersonaRepo personaRepo;
    @Autowired
    private
    InfraccionRepo infraccionRepo;
    @Autowired
    private
    HistoricoPasswordRepo historicoPasswordRepo;
    @Autowired
    private
    CategoriaParametroRepo categoriaParametroRepo;
    @Autowired
    private
    ParametroRepo parametroRepo;
    @Autowired
    private
    ApplicationContext applicationContext;
    @Autowired
    private
    AlarmaRepo alarmaRepo;
    @Autowired
    private DocumentoService documentoService;
    @Autowired
    private DocumentoRepo documentoRepo;
    @Autowired
    private DocumentoInfraccionRepo documentoInfraccionRepo;


    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() throws Exception {

        if (!loadInitialData)
            return;

        try {
            load(UnidadMedida.FULL_LIST, UnidadMedidaRepo.class, UnidadMedida::getId);
            load(TipoComparendo.FULL_LIST, TipoComparendoRepo.class, TipoComparendo::getId);
            load(EstadoComparendo.FULL_LIST, EstadoComparendoRepo.class, EstadoComparendo::getId);
            load(DecisionComparendo.FULL_SET, DecisionComparendoRepo.class, DecisionComparendo::getId);
            load(EstadoVehiculo.FULL_SET, EstadoVehiculoRepo.class, EstadoVehiculo::getId);
            load(TipoVehiculo.FULL_SET, TipoVehiculoRepo.class, TipoVehiculo::getId);
            load(ClaseServicioVehiculo.FULL_SET, ClaseServicioVehiculoRepo.class, ClaseServicioVehiculo::getId);
            load(TipoDocumento.FULL_SET, TipoDocumentoRepo.class, TipoDocumento::getId);
            load(TipoPersona.FULL_SET, TipoPersonaRepository.class, TipoPersona::getId);
            load(EstadoNumeroComparendo.FULL_SET, EstadoNumeroComparendoRepo.class, EstadoNumeroComparendo::getId);
            load(TipoRangoNumeracion.FULL_SET, TipoRangoNumeracionRepo.class, TipoRangoNumeracion::getId);
            load(EstadoAsistenciaCurso.FULL_SET, EstadoAsistenciaCursoRepo.class, EstadoAsistenciaCurso::getId);
            load(EstadoCurso.FULL_SET, EstadoCursoRepo.class, EstadoCurso::getId);
            load(TipoInfractor.FULL_SET, TipoInfractorRepo.class, TipoInfractor::getId);
            load(CategoriaLicencia.FULL_SET, CategoriaLicenciaRepo.class, CategoriaLicencia::getId);
            load(TipoAudienciaComparendo.FULL_SET, TipoAudienciaComparendoRepo.class, TipoAudienciaComparendo::getId);
            load(TipoNotificacion.FULL_SET, TipoNotificacionRepo.class, TipoNotificacion::getId);
            load(TipoArchivoPagos.FULL_SET, TipoArchivoPagosRepo.class, TipoArchivoPagos::getId);
            load(EstadoPago.FULL_SET, EstadoPagoRepo.class, EstadoPago::getId);
            load(OrganismoTransito.FULL_SET, OrganismoTransitoRepo.class, OrganismoTransito::getId);
            load(SistemaOrigen.FULL_SET, SistemaOrigenRepo.class, SistemaOrigen::getId);
            load(EstadoTitulo.FULL_LIST, EstadoTituloRepo.class, EstadoTitulo::getId);
            load(EtapaTituloEjecutivo.FULL_LIST, EtapaTituloEjecutivoRepo.class, EtapaTituloEjecutivo::getId);
            load(TipoDocumental.FULL_LIST, TipoDocumentalRepo.class, TipoDocumental::getId);
            load(TipoActuacion.FULL_SET, TipoActuacionRepo.class, TipoActuacion::getId);
            load(TipoDia.FULL_SET, TipoDiaRepo.class, TipoDia::getId);
            load(Actuacion.FULL_SET, ActuacionRepo.class, Actuacion::getId);
            load(TipoPlantilla.FULL_SET, TipoPlantillaRepo.class, TipoPlantilla::getId);
            load(TipoPruebaProcesoContravencional.FULL_SET, TipoPruebaProcesoContravencionalRepo.class, TipoPruebaProcesoContravencional::getId);

            loadTableReference(Pais.FULL_SET, PaisRepo.class, Pais::getIdentifier);
            loadTableReference(FuenteTituloEjecutivo.FULL_SET, FuenteTituloEjecutivoRepo.class, FuenteTituloEjecutivo::getIdentifier);

            // Carga de la estructura política de Colombia y del distrito capital
            loadCSVTableReference("divipola-departamentos.csv", Departamento.class, DepartamentoRepo.class,
                    (x, s) -> x.setPais(Pais.COLOMBIA_OBJ),
                    Departamento::setId,
                    Departamento::setNombre
            );
            loadCSVTableReference("divipola-municipios.csv", Municipio.class, MunicipioRepo.class,
                    (x, s) -> x.setDepartamento(departamentoRepo.findByIdAndFecha(s, new Date())),
                    Municipio::setId,
                    Municipio::setNombre
            );
            loadCSVTableReference("divipola-localidades.csv", Localidad.class, LocalidadRepo.class,
                    (x, s) -> x.setMunicipio(municipioRepo.findByIdAndFecha(s, new Date())),
                    Localidad::setId,
                    Localidad::setNombre
            );
            loadCSV("infracciones.csv", Infraccion.class, InfraccionRepo.class, Integer::valueOf,
                    (x, s) -> x.setId(Integer.valueOf(s)),
                    Infraccion::setCodigo,
                    Infraccion::setNombre,
                    (x, s) -> x.setInicioVigencia(Constants.DATE_FORMAT.parse(s)),
                    (x, s) -> x.setFinVigencia(Constants.DATE_FORMAT.parse(s)),
                    (x, s) -> x.setValor(new BigDecimal(s)),
                    (x, s) -> x.setUnidadMedida(new UnidadMedida(Integer.valueOf(s))),
                    (x, s) -> x.setGrado(new Integer(s)),
                    (x, s) -> x.setReincidencias(Integer.valueOf(s)),
                    Infraccion::setValidador
            );
            loadCSV("festivos.csv", Festivo.class, FestivoRepo.class,
                    s -> {
                        try {
                            return Constants.DATE_FORMAT.parse(s);
                        } catch (Exception e) {
                            throw new RuntimeException("Día no laboral no es válido: " + s);
                        }
                    },
                    (x, s) -> x.setFecha(Constants.DATE_FORMAT.parse(s))
            );
            loadCSV("plantillas.csv", Plantilla.class, PlantillaRepo.class,
                    UUID::fromString,
                    (x, s) -> x.setId(UUID.fromString(s)),
                    Plantilla::setNombre,
                    (x, s) -> x.setSeSustancia(Boolean.parseBoolean(s)),
                    (x, s) -> x.setSeVerifica(Boolean.parseBoolean(s)),
                    (x, s) -> x.setSeFirma(Boolean.parseBoolean(s)),
                    (x, s) -> x.setSeFirmaManual(Boolean.parseBoolean(s)),
                    Plantilla::setBeanExtraccion,
                    (x, s) -> x.setTipoDocumental(TipoDocumental.FULL_LIST.stream().filter(a -> a.getId().equals(UUID.fromString(s))).findAny().orElse(null)),
                    (x, s) -> x.setTipoPlantilla(TipoPlantilla.FULL_SET.stream().filter(a -> a.getId().equals(Integer.valueOf(s))).findAny().orElse(null)),
                    Plantilla::setContentTypeProducido,
                    Plantilla::setNombreArchivo,
                    (x, s) -> x.setDocumento(new Documento(UUID.randomUUID())), // TODO: No debe ser automático
                    (x, s) -> x.getDocumento().setContentType(s),
                    (x, s) -> {
                        x.getDocumento().setOriginalName(s);
                        x.getDocumento().setTipoDocumental(TipoDocumental.PLANTILLA);
                        byte[] bytes;
                        try {
                            bytes = IOUtils.toByteArray(Plantilla.class.getResourceAsStream("/doc-templates/" + s));
                        } catch (Exception e) {
                            throw new RuntimeException("No puede leer el contenido para la plantilla: " + x.getNombre(), e);
                        }
                        if (documentoService.getDocumento(x.getDocumento().getId()) == null) {
                            documentoService.addDocumento(x.getDocumento(), bytes);
                        }
                    }
            );
            PlantillaRepo plantillaRepo = applicationContext.getBean(PlantillaRepo.class);
            List<Plantilla> plantillas = plantillaRepo.findAll();
            for (Plantilla p : plantillas) {
                byte[] bytes = IOUtils.toByteArray(Plantilla.class.getResourceAsStream("/doc-templates/" + p.getDocumento().getOriginalName()));
                String hash = DigestUtils.md5Hex(bytes);
                if(!hash.equals(p.getDocumento().getHashContenido())) {
                    documentoService.actualizarDocumento(p.getDocumento(), bytes);
                }
            }

            createDependencia();
            cargarPermisos();
            createRoot();
            cargarParametros();
            createAlarma();

            // TODO: Eliminar porque no son datos de inicio del sistema sino datos de prueba. Recomendación, usar otro mecanismo para hacer esto
            cargarDocumentos();
            // TODO: Eliminar porque no son datos de inicio del sistema sino datos de prueba. Recomendación, usar otro mecanismo para hacer esto
            cargarInfraccionesTransportePublico();

        } catch (Exception e) {
            LOGGER.error("Mientras se creaban los objetos iniciales en la base de datos", e);
            throw e;
        }
    }

    // TODO: Eliminar porque no son datos de inicio del sistema sino datos de prueba. Recomendación, usar otro mecanismo para hacer esto
    private void cargarInfraccionesTransportePublico() {
        List<Infraccion> infracciones = infraccionRepo.findAll();
        List<Documento> documentos = documentoRepo.findAll();

        Random rand = new Random();

        for(int x = 0; x < 10; x++) {
            int randomNumInfra = rand.nextInt((infracciones.size() - 1) + 1);
            Infraccion infraccion = infracciones.get(randomNumInfra);

            int randomNumDocu = rand.nextInt((documentos.size() - 1) + 1);
            Documento documento = documentos.get(randomNumDocu);

            DocumentoInfraccion documentoInfraccion = new DocumentoInfraccion();
            documentoInfraccion.setId(UUID.randomUUID());
            Date date = new Date();
            documentoInfraccion.setFecha(date);
            documentoInfraccion.setAsunto("Asunto " + date.getTime());
            documentoInfraccion.setRadicado(rand.nextInt((50000 - 20000) + 1) + 20000);
            documentoInfraccion.setDocumento(documento);
            documentoInfraccion.setInfraccion(infraccion);

            documentoInfraccionRepo.save(documentoInfraccion);
        }

    }

    // TODO: Eliminar porque no son datos de inicio del sistema sino datos de prueba. Recomendación, usar otro mecanismo para hacer esto
    private void cargarDocumentos() {
        //UUID::fromString,
        loadCSVTableReferenceJpaRepository("documentos.csv", Documento.class, DocumentoRepo.class,
                (x, s) -> x.setId(UUID.randomUUID()),
                (x, s) -> x.setFechaCreacion(Constants.DATE_FORMAT.parse(s)),
                (x, s) -> x.setContentType(s),
                (x, s) -> x.setOriginalName(s),
                (x, s) -> x.setTipoDocumental(TipoDocumental.INFRACCION_TRANSPORTE_PUBLICO)
        );

    }

    private void createDependencia() {
    }

    private <T, ID extends Serializable, X extends BaseTableReferenceRepo<T, ID>>
    void loadCSVTableReference(
            String resource,
            Class<T> clazz,
            Class<X> repoClass,
            Setter<T>... setters
    ) {
        try {
            BaseTableReferenceRepo<T, ID> repo = applicationContext.getBean(repoClass);

            ClassLoader classLoader = getClass().getClassLoader();
            InputStream fis = classLoader.getResourceAsStream(resource);
            CSVReader reader = new CSVReader(new InputStreamReader(fis));
            String[] line;
            Long version = repo.getCurrentVersion();
            if (version == null)
                version = Long.valueOf(1);
            else
                version = version + Long.valueOf(1);
            while ((line = reader.readNext()) != null) {
                // Ignora las lineas que no tienen dos campos o que comiencen con el caracter #
                if ((line.length == 1 && StringUtils.isBlank(line[0])) || line[0].trim().startsWith("#"))
                    continue;
                T x = repo.findByIdAndFecha(line[1], new Date());

                if (x == null) {
                    x = clazz.newInstance();
                    for (int i = 0; i < line.length && i < setters.length; i++) {
                        setters[i].set(x, line[i]);
                    }
                    // Campos para la auditoria
                    Date fecha = Constants.DATE_FORMAT.parse("1870-01-01");
                    clazz.getMethod("setIdentifier", UUID.class)
                            .invoke(x, UUID.randomUUID());
                    clazz.getMethod("setInicioVigencia", Date.class)
                            .invoke(x, fecha);
                    clazz.getMethod("setFechaCreacion", Date.class)
                            .invoke(x, fecha);
                    clazz.getMethod("setVersion", Long.class)
                            .invoke(x, version);
                    // Se guarda el objeto
                    repo.save(x);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private <T, ID extends Serializable, X extends JpaRepository<T, ID>>
        void loadCSVTableReferenceJpaRepository(
            String resource,
            Class<T> clazz,
            Class<X> repoClass,
            Setter<T>... setters
    ) {
        try {
            JpaRepository<T, ID> repo = applicationContext.getBean(repoClass);

            ClassLoader classLoader = getClass().getClassLoader();
            InputStream fis = classLoader.getResourceAsStream(resource);
            CSVReader reader = new CSVReader(new InputStreamReader(fis));
            String[] line;
            while ((line = reader.readNext()) != null) {
                // Ignora las lineas que no tienen dos campos o que comiencen con el caracter #
                if ((line.length == 1 && StringUtils.isBlank(line[0])) || line[0].trim().startsWith("#"))
                    continue;
                    T x = clazz.newInstance();

                    for (int i = 0; i < line.length && i < setters.length; i++) {
                        setters[i].set(x, line[i]);
                    }
                    // Se guarda el objeto
                    repo.save(x);
                //}
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @SafeVarargs
    private final <T, ID extends Serializable, X extends JpaRepository<T, ID>>
    void loadCSV(
            String resource,
            Class<T> clazz,
            Class<X> repoClass,
            Converter<String, ID> idConverter,
            Setter<T>... setters
    ) {
        try {
            JpaRepository<T, ID> repo = applicationContext.getBean(repoClass);

            ClassLoader classLoader = getClass().getClassLoader();
            InputStream fis = classLoader.getResourceAsStream(resource);
            CSVReader reader = new CSVReader(new InputStreamReader(fis));
            String[] line;
            while ((line = reader.readNext()) != null) {
                // Ignora las lineas que no tienen dos campos o que comiencen con el caracter #
                if ((line.length == 1 && StringUtils.isBlank(line[0])) || line[0].trim().startsWith("#"))
                    continue;
                T x = repo.findOne(idConverter.convert(line[0]));
                if (x == null) {
                    x = clazz.newInstance();
                    for (int i = 0; i < line.length && i < setters.length; i++) {
                        setters[i].set(x, line[i]);
                    }
                    repo.save(x);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private <T, ID extends Serializable, X extends BaseTableReferenceRepo<T, ID>>
    void loadTableReference(
            Collection<T> collection,
            Class<X> repoClass,
            Converter<T, ID> getIdentifier
    ) throws Exception {
        BaseTableReferenceRepo<T, ID> repo = applicationContext.getBean(repoClass);
        for (T x : collection)
            if (repo.findOne(getIdentifier.convert(x)) == null)
                repo.save(x);
    }

    private <T, ID extends Serializable, X extends JpaRepository<T, ID>>
    void load(
            Collection<T> collection,
            Class<X> repoClass,
            Converter<T, ID> getId
    ) {
        JpaRepository<T, ID> repo = applicationContext.getBean(repoClass);
        for (T x : collection)
            if (repo.findOne(getId.convert(x)) == null)
                repo.save(x);
    }

    private void cargarPermisos() throws IOException {
        LOGGER.info("Se cargan los permisos del texto plano");
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream fis = classLoader.getResourceAsStream("permisos.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(fis));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Ignora las lineas que no tienen dos campos o que comiencen con el caracter #
                if (nextLine.length != 2 || nextLine[0].trim().startsWith("#"))
                    continue;
                Permiso permiso = permisoRepo.findByNombre(nextLine[0]);
                if (permiso == null) {
                    permiso = new Permiso();
                    permiso.setNombre(nextLine[0]);
                    permiso.setDescripcion(nextLine[1]);
                    permisoRepo.save(permiso);
                }
            }
        } catch (Exception e) {
            LOGGER.info("Error mientras se crean los permisos", e);
            throw e;
        }
    }

    private void createRoot() throws ParseException {

        LOGGER.info("Creación del usuario ROOT");

        Rol rol = rolRepo.findOne(Usuario.ROOT.getPerfil().getRoles().get(0).getId());
        if (rol == null) {
            rol = Usuario.ROOT.getPerfil().getRoles().get(0);
        } else {
            rol.setPermisos(Usuario.ROOT.getPerfil().getRoles().get(0).getPermisos());
        }
        rolRepo.save(rol);

        Perfil perfil = perfilRepo.findOne(Usuario.ROOT.getPerfil().getId());
        if (perfil == null) {
            perfil = Usuario.ROOT.getPerfil();
        } else {
            perfil.setRoles(Usuario.ROOT.getPerfil().getRoles());
        }
        perfilRepo.save(perfil);

        Persona personaRoot = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(
                Persona.PERSONA_ROOT.getTipoDocumentoIdentidad(),
                Persona.PERSONA_ROOT.getNumeroDocumentoIdentidad()
        );
        if (personaRoot == null) {
            personaRoot = Persona.PERSONA_ROOT;
            personaRepo.save(personaRoot);
        }

        Usuario root = usuarioRepo.findOne(Usuario.ROOT.getId());
        if (root == null) {
            root = Usuario.ROOT;
            root.setCargo("Autoridad");
            usuarioRepo.save(root);
            HistoricoPassword histRoot = new HistoricoPassword();
            histRoot.setId(UUID.randomUUID());
            histRoot.setPassword(root.getPassword());
            histRoot.setUsuario(root);
            histRoot.setFechaRegistro(new Date());
            historicoPasswordRepo.save(histRoot);
        }

        Persona personaSistema = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(
                Persona.PERSONA_SISTEMA.getTipoDocumentoIdentidad(),
                Persona.PERSONA_SISTEMA.getNumeroDocumentoIdentidad()
        );
        if (personaSistema == null) {
            personaSistema = Persona.PERSONA_SISTEMA;
            personaRepo.save(personaSistema);
        }

        Usuario sistema = usuarioRepo.findOne(Usuario.SISTEMA.getId());
        if (sistema == null) {
            sistema = Usuario.SISTEMA;
            sistema.setCargo("Autoridad");
            usuarioRepo.save(sistema);
            HistoricoPassword histRoot = new HistoricoPassword();
            histRoot.setId(UUID.randomUUID());
            histRoot.setPassword(sistema.getPassword());
            histRoot.setUsuario(sistema);
            histRoot.setFechaRegistro(new Date());
            historicoPasswordRepo.save(histRoot);
        }
    }

    private void cargarParametros() throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        List<CategoriaParametro> categorias = mapper.readValue(
                new ClassPathResource("parametros.yml").getURL(),
                new TypeReference<List<CategoriaParametro>>() {
                });
        if (categorias != null) {
            LOGGER.info("Se cargan y validan los parametros");
            List<String> claves = new ArrayList<>();
            for (CategoriaParametro current : categorias) {
                CategoriaParametro categoria = validarParametros(current);
                categoriaParametroRepo.save(categoria);
                List<String> obtenidas = obtenerListaParametros(current);
                claves.addAll(obtenidas);
            }
            validarConstantesParametros(claves);
        }
    }

    private CategoriaParametro validarParametros(CategoriaParametro categoria) {
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN);
        Date date = null;
        try {
            date = dateFormat.parse("1870-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (categoria.getParametros() != null) {
            List<Parametro> parametros = new ArrayList<>();
            for (Parametro param : categoria.getParametros()) {
                Parametro old = parametroRepo.findOne(param.getClave());
                if (old == null) {
                    List<ValorParametro> valores = new ArrayList<>();
                    if (param.getValores() != null) {
                        for (ValorParametro valor : param.getValores()) {
                            valor.setId(UUID.randomUUID());
                            valor.setFechaCreacion(date);
                            valor.setInicioVigencia(date);
                            valor.setParametro(param);
                            valores.add(valor);
                        }
                        param.setValores(valores);
                    }
                } else {
                    param.setValores(old.getValores());
                }
                parametros.add(param);
            }
            categoria.setParametros(parametros);
        }
        if (categoria.getCategorias() != null) {
            List<CategoriaParametro> dependientes = new ArrayList<>();
            for (CategoriaParametro c : categoria.getCategorias()) {
                dependientes.add(validarParametros(c));
            }
            categoria.setCategorias(dependientes);
        }
        return categoria;
    }

    private List<String> obtenerListaParametros(CategoriaParametro categoria) {
        List<String> claves = new ArrayList<>();
        if (categoria.getParametros() != null) {
            for (Parametro param : categoria.getParametros()) {
                claves.add(param.getClave());
            }
        }
        if (categoria.getCategorias() != null) {
            for (CategoriaParametro c : categoria.getCategorias()) {
                claves.addAll(obtenerListaParametros(c));
            }
        }
        return claves;
    }

    private void validarConstantesParametros(List<String> claves) {
        Field[] fields = Parametro.class.getDeclaredFields();
        for (Field f : fields) {
            if (f.getType().equals(String.class)
                    && f.getModifiers() == (Modifier.FINAL | Modifier.STATIC | Modifier.PUBLIC)
                    && !claves.contains(f.getName())) {
                throw new RuntimeException("La constante " +
                        f.getName() + " de la clase parámetro no se encuentra " +
                        "contenido en el archivo parametros.yml");
            }
        }
    }

    private interface Converter<T, P> {
        P convert(T t);
    }

    private interface Setter<T> {
        void set(T x, String s) throws Exception;
    }

    private void createAlarma() {
        Alarma alarma = Alarma.TAREAS_EN_PROGRESO;
        AlarmaRango rango1 = alarma.getRangos().get(0);
        Usuario root = usuarioRepo.findOneByUsername("root");

        rango1.setAlarma(alarma);
        rango1.setTipoDia(TipoDia.CALENDARIO);
        rango1.setTipoNotificacion(TipoNotificacion.SIN_NOTIFICACION);
        rango1.setSuperior(root);

        AlarmaRango rango2 = alarma.getRangos().get(1);
        rango2.setAlarma(alarma);
        rango2.setTipoDia(TipoDia.CALENDARIO);
        rango2.setTipoNotificacion(TipoNotificacion.SIN_NOTIFICACION);
        rango2.setSuperior(root);

        AlarmaRango rango3 = alarma.getRangos().get(2);
        rango3.setAlarma(alarma);
        rango3.setTipoDia(TipoDia.CALENDARIO);
        rango3.setTipoNotificacion(TipoNotificacion.SIN_NOTIFICACION);
        rango3.setSuperior(root);

        alarmaRepo.save(alarma);
    }

}

