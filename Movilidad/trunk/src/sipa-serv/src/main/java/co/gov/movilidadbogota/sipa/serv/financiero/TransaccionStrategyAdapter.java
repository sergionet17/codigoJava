package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.biz.comp.Inmovilizacion;
import co.gov.movilidadbogota.sipa.data.biz.comp.InmovilizacionRepo;
import co.gov.movilidadbogota.sipa.data.biz.trans.Investigacion;
import co.gov.movilidadbogota.sipa.data.biz.trans.InvestigacionRepo;
import co.gov.movilidadbogota.sipa.data.fin.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Adaptador para la interfaz {@link TransaccionStrategy}.
 * <p>
 * Específicamente implementa el método getCodigoEvento transformando el nombre de la clase.
 *
 * @author arturo.cruz
 */
public abstract class TransaccionStrategyAdapter implements TransaccionStrategy {

    @Autowired
    private EntradaRepo entradaRepo;

    @Autowired
    private ComparendoRepository comparendoRepo;

    @Autowired
    private TransaccionRepo transaccionRepo;

    @Autowired
    private InmovilizacionRepo inmovilizacionRepo;

    @Autowired
    private InvestigacionRepo investigacionRepo;

    @Override
    public String getCodigoEvento() {
        String classname = this.getClass().getName();
        classname = classname.substring(classname.lastIndexOf('.') + 1);
        classname = classname.replace("Strategy", "");
        return classname;
    }

    /**
     * Selecciona los elementos de una colección dependiendo del predicado
     *
     * @param collection La colección sobre la cual se realiza la selección
     * @param predicate  La condición que deben cumplir los elementos seleccionados
     * @param <T>        El tipo de objeto de los elementos de la colección
     * @return Una nueva colección que contiene los elementos que cumplen con el predicado
     */
    <T> Collection<T> select(Collection<T> collection, Predicate<T> predicate) {
        if (collection == null)
            return null;
        ArrayList<T> ans = new ArrayList<>();
        for (T t : collection) {
            if (predicate.test(t))
                ans.add(t);
        }
        return ans;
    }


    void checkComparendo(UUID referencia) {
        Comparendo comparendo = comparendoRepo.findOne(referencia);
        if (comparendo == null) {
            String msg = String.format(
                    "La referencia no corresponde a un comparendo: %s",
                    referencia
            );
            throw new IllegalArgumentException(msg);
        }
    }

    Transaccion findTransaccionByCodigoEventoAndTipoReferenciaAndReferencia(
            String codigoEvento, String tipoReferencia, UUID referencia) {
        return transaccionRepo.findByCodigoEventoAndTipoReferenciaAndReferencia(
                codigoEvento,
                tipoReferencia,
                referencia
        );
    }

    BigDecimal saldo(UUID referencia, Cuenta cuenta) {
        return FinancieroUtils.saldo(referencia, cuenta, entradaRepo);
    }

    /**
     * Obtiene una inmovilización por identificador
     *
     * @param referencia número de indentificación de la inmovilización
     * @return El objeto con el id respectivo o null en caso de no encontrar la inmovilización
     */
    Inmovilizacion findInmovilizacion(UUID referencia) {
        return inmovilizacionRepo.findOne(referencia);
    }

    /**
     * Obtiene una investigación al transporte público
     *
     * @param referencia número de identificación de la investigación
     * @return El objeto con el Id respectivo o null en caso de no encontrar la investigación
     */
    Investigacion findInvestigacion(UUID referencia) {
        return investigacionRepo.findOne(referencia);
    }

    List<Entrada> entradas(UUID referencia, Cuenta cuenta) {
        return FinancieroUtils.entradas(referencia, cuenta, entradaRepo);
    }

    BigDecimal saldo(List<Entrada> entradas) {
        return FinancieroUtils.saldo(entradas);
    }

}


