package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.fin.Cuenta;
import co.gov.movilidadbogota.sipa.data.fin.CuentaRepo;
import co.gov.movilidadbogota.sipa.data.fin.NaturalezaCuenta;
import co.gov.movilidadbogota.sipa.data.fin.NaturalezaCuentaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Clase que se encarga de cargar la información básica del módulo financiero. Por ejemplo crea las cuentas contables
 * tipos de cuenta, etc.
 *
 * @author arturo.cruz
 */
@Component
public class FinancieroDataLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FinancieroDataLoader.class);

    @Autowired
    private NaturalezaCuentaRepo naturalezaCuentaRepo;
    @Autowired
    private CuentaRepo cuentaRepo;

    @PostConstruct
    public void init() throws Exception {
        LOGGER.info("Creación de datos básicos");
        try {
            // Regsitros financieros
            createRegistrosFinancieros();
        } catch (Exception e) {
            LOGGER.error("Mientras se creaban los objetos iniciales en la base de datos", e);
            throw e;
        }
    }

    private void createRegistrosFinancieros() {

        // Creación de los diferentes tipos de cuenta contable. Corresponden al primer nivel del PUC.
        NaturalezaCuenta[] list = new NaturalezaCuenta[]{
                NaturalezaCuenta.ACTIVOS,
                NaturalezaCuenta.COSTOS,
                NaturalezaCuenta.GASTOS,
                NaturalezaCuenta.INGRESOS,
                NaturalezaCuenta.ORDEN_ACREEDORAS,
                NaturalezaCuenta.ORDEN_DEUDORAS,
                NaturalezaCuenta.PASIVOS,
                NaturalezaCuenta.PATRIMONIO
        };
        for (NaturalezaCuenta x : list) {
            NaturalezaCuenta old = naturalezaCuentaRepo.findOne(x.getId());
            if (old == null) {
                naturalezaCuentaRepo.save(x);
                LOGGER.info(String.format("Naturaleza cuenta creada: %s", x));
            }
        }

        // Creación de las cuentas contables
        Cuenta[] cuentas = new Cuenta[]{
                Cuenta.ORD_DEU_COMPARENDOS,
                Cuenta.ORD_DEU_COMPARENDOS_CONTRA,
                Cuenta.ACT_COMPARENDOS,
                Cuenta.ACT_HACIENDA,
                Cuenta.ING_COMPARENDOS,
                Cuenta.PAS_CUENTAS_POR_PAGAR,
                Cuenta.ACT_DESCUENTO_COMPARENDOS,
                Cuenta.ORD_DEU_SUBSANACIONES,
                Cuenta.ORD_DEU_SUBSANACIONES_CONTRA,
                Cuenta.ACT_SUBSANACIONES,
                Cuenta.ING_SUBSANACIONES,
                Cuenta.ORD_DEU_PATIOS_GRUAS,
                Cuenta.ORD_DEU_PATIOS_GRUAS_CONTRA,
                Cuenta.ING_PATIOS_GRUAS,
                Cuenta.ACT_PATIOS_GRUAS,
                Cuenta.ORD_DEU_TRANSPORTE_PUBLICO,
                Cuenta.ORD_DEU_TRANSPORTE_PUBLICO_CONTRA,
                Cuenta.ING_TRANSPORTE_PUBLICO,
                Cuenta.ACT_TRANSPORTE_PUBLICO,
                Cuenta.ACT_INTERESES,
                Cuenta.ING_INTERESES,
                Cuenta.PAT_COMPARENDOS,
                Cuenta.PAT_TRANSPORTE_PUBLICO,
                Cuenta.PAT_SUBSANACIONES,
                Cuenta.PAT_PATIOS_GRUAS,
                Cuenta.ACT_COSTAS,
                Cuenta.ING_COSTAS,
                Cuenta.ORD_DEU_POSIBLE_RECAUDO,
                Cuenta.ORD_DEU_POSIBLE_RECAUDO_CONTRA,
                Cuenta.ORD_DEU_RECAUDO,
                Cuenta.ORD_DEU_RECAUDO_CONTRA,
                Cuenta.PAS_DEPOSITO_JUDICIAL,
                Cuenta.ACT_SANCIONES_DISCIPLINARIAS,
                Cuenta.ING_SANCIONES_DISCIPLINARIAS,
                Cuenta.ING_ENTIDADES_EXTERNAS,
                Cuenta.ACT_ENTIDADES_EXTERNAS
        };
        for (Cuenta x : cuentas) {
            Cuenta old = cuentaRepo.findOne(x.getId());
            if (old == null) {
                cuentaRepo.save(x);
                LOGGER.info(String.format("Cuenta creada: %s", x));
            }
        }
    }


}
