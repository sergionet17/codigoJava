package co.gov.movilidadbogota.sipa.serv.comparendos;

import co.gov.movilidadbogota.sipa.data.biz.comp.Infraccion;
import co.gov.movilidadbogota.sipa.data.biz.comp.InfraccionRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoComparendo;
import co.gov.movilidadbogota.sipa.data.util.MensajeHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Fábrica de cadenas de validación de {@link FormatoComparendoValidator} que actúan
 * sobre la clase {@link co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo}
 */
@Component
public class FormatoComparendoChainValidationFactory {

    private ApplicationContext applicationContext;

    /**
     * Objeto en el que se guardan en memoria las cadenas de validación
     * TODO: Hacer que se limpie de vez en cuando
     */
    private Map<String, List<FormatoComparendoValidator>> chainCache = new HashMap<>();

    /**
     * Crea una nueva instancia de {@link FormatoComparendoChainValidationFactory}
     *
     * @param applicationContext Contexto de aplicación en la que se encuentran todos los beans
     */
    public FormatoComparendoChainValidationFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Obtiene una cadena de validación que corresponde a las validaciones configuradas para
     * la correspondiente infracción en la fecha de imposición.
     *
     * @param infraccion La infracción junto con su configuración
     * @return Una lista de validadores {@link FormatoComparendoValidator}
     */
    List<FormatoComparendoValidator> get(Infraccion infraccion) {

        /*
        Busca la cadena en el caché usando la definición de la cadena. Si no la encuentra entonces
        crea una cadena nueva y la agrega al caché. La construccián de la cadena consiste en separar
        por coma la cadena de texto que contiene la definición, luego recortar los espacios sobrantes
        de los elementos, filtrar aquellos que estén en blanco, obtener el bean que tiene el nombre
        correspondiente al elemento y por último convertir el stream a una lista.
         */
        List chain = chainCache.get(infraccion.getValidador());
        if (chain == null) {
            chain = Arrays.stream(StringUtils.split(infraccion.getValidador(), ','))
                    .map(String::trim)
                    .filter(StringUtils::isNotBlank)
                    .map(x -> applicationContext.getBean(x, FormatoComparendoValidator.class))
                    .collect(Collectors.toList());
            chainCache.put(infraccion.getValidador(), chain);
        }
        return chain;
    }

}
