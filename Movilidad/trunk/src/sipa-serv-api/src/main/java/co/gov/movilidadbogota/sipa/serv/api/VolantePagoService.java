package co.gov.movilidadbogota.sipa.serv.api;

import co.gov.movilidadbogota.sipa.data.doc.Documento;

import java.util.UUID;

public interface VolantePagoService {

    /**
     * Generar uvolante de pago asociado a un comparendo
     *
     * @param comparendoId   Es el  identificador del comparendo
     * @throws ComparendoException Cuando ocurre un error que impide almacenar
     *                             la información en los repositorios o por inconsistencias
     *                             graves que impiden validar la información del formato
     */
    Documento generarVolantePago(
            UUID comparendoId
    ) throws Exception;
}
