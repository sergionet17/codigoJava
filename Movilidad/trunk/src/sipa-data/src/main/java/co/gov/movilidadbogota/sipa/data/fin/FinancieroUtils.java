package co.gov.movilidadbogota.sipa.data.fin;

import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Clase que contiene métodos utilitarios para obtener valores financieros
 */
public final class FinancieroUtils {

    public static BigDecimal saldoDeudaComparendo(UUID idComparendo, EntradaRepo repo) {
        return saldo(entradasDeudaComparendo(idComparendo, repo));
    }

    public static List<Entrada> entradasDeudaComparendo(UUID idComparendo, EntradaRepo repo) {
        List<Entrada> entradas = entradas(idComparendo, Cuenta.ORD_DEU_COMPARENDOS, repo);
        entradas.addAll(entradas(idComparendo, Cuenta.ACT_COMPARENDOS, repo));
        return entradas;
    }

    public static List<Entrada> entradas(UUID referencia, Cuenta cuenta, EntradaRepo repo) {
        return repo.findByCuentaAndReferencia(cuenta, referencia);
    }

    public static List<Entrada> entradas(UUID referencia, EntradaRepo repo) {
        return repo.findByReferencia(referencia, new Sort(Sort.Direction.ASC, "fechaEvento"));
    }

    /**
     * Calcula la suma de los valores de las entradas que se pasan como parámetro
     *
     * @param entradas El listado de entradas sobre las cuales se realiza la suma
     * @return El total de la suma de valores de las entradas
     */
    public static BigDecimal saldo(Collection<Entrada> entradas) {
        BigDecimal sum = BigDecimal.ZERO;
        if (entradas == null)
            return sum;
        for (Entrada entrada : entradas) {
            sum = sum.add(entrada.getValor());
        }
        return sum;
    }

    /**
     * Calcula el saldo de una cuenta para una referencia específica
     *
     * @param referencia  El identificador de la referencia
     * @param cuenta      La cuenta
     * @param entradaRepo Repositorio de entradas
     * @return La suma de las entradas correspondientes
     */
    public static BigDecimal saldo(UUID referencia, Cuenta cuenta, EntradaRepo entradaRepo) {
        return saldo(entradas(referencia, cuenta, entradaRepo));
    }

    /**
     * Calcula el saldo de una cuenta para una referencia específica
     *
     * @param referencia  El identificador de la referencia
     * @param idCuenta    El identificador de la cuenta
     * @param entradaRepo Repositorio de entradas
     * @return La suma de las entradas correspondientes
     */
    public static BigDecimal saldo(UUID referencia, UUID idCuenta, EntradaRepo entradaRepo) {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(idCuenta);
        return saldo(referencia, cuenta, entradaRepo);
    }


    public static BigDecimal interesesDeudaComparendo(UUID idComparendo, EntradaRepo repo) {
        List<Entrada> entradas = repo.findByCuentaAndReferencia(Cuenta.ACT_COMPARENDOS, idComparendo);
        return BigDecimal.ZERO;
    }

}
