package co.gov.movilidadbogota.sipa.serv.financiero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Fábrica de transacciones. Dependiendo del nombre del evento la fábrica encuentra el objeto correcto que implementa la
 * estrategia de la transacción.
 * La implementación de la fábrica en principio se hace con un registro simple en el que se relaciona el código del
 * evento con el objeto que implementa la transacción.
 *
 * @author arturo.cruz
 */
@Component
public class TransaccionStrategyFactory {

    /**
     * Almacena la correspondencia entre el código del evento y el objeto que implementa la transacción
     */
    private static final Map<String, TransaccionStrategy> registry = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Obtiene la estrategia de creación de entradas a partir del código del evento
     *
     * @param codigoEvento código del evento que se está registrando
     * @return la implementación correspondiente al código del evento
     */
    static TransaccionStrategy get(String codigoEvento) {
        return registry.get(codigoEvento);
    }

    /**
     * Obtiene el conjunto de códigos de evento registrados
     *
     * @return El conjunto de nombres de código de eventos registrados
     */
    static Set<String> getCodigosRegistrados() {
        return registry.keySet();
    }

    /**
     * Se ejecuta al iniciar el Bean para cargar el registro correspondencia entre código de evento y clase
     */
    @PostConstruct
    public void init() {
        registry.clear();
        Map<String, TransaccionStrategy> beans = applicationContext.getBeansOfType(TransaccionStrategy.class);
        for (TransaccionStrategy transaccionStrategy : beans.values()) {
            registry.put(transaccionStrategy.getCodigoEvento(), transaccionStrategy);
        }
    }

}
