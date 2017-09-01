package co.gov.movilidadbogota.sipa.serv.comparendos;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.gen.ClaseServicioVehiculo;
import co.gov.movilidadbogota.sipa.data.biz.gen.TipoVehiculo;
import co.gov.movilidadbogota.sipa.data.biz.gen.Vehiculo;
import co.gov.movilidadbogota.sipa.data.biz.gen.VehiculoRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Valida la información del vehículo en el formato del comparendo
 */
@Component
public class ValVehiculo implements FormatoComparendoValidator {

    @Autowired
    private VehiculoRepo vehiculoRepo;

    @Override
    public boolean validate(FormatoComparendo formato, Comparendo comparendo, FormatoComparendoValidatorErrors error) {
        boolean ret = true;

        // Verifica si el número de placa del vehículo se encuentra presente
        // en el formato.
        String placa = formato.getPlacaVehiculo();
        if (StringUtils.isBlank(placa)) {
            error.reject("placaVehiculo", "La placa del vehículo es obligatoria");
            ret = false;
        } else {
            Vehiculo vehiculo = vehiculoRepo.findByPlacaVehiculo(placa);
            if (vehiculo == null) {
                String tipoStr = formato.getTipoVehiculo();
                TipoVehiculo tipo = null;
                if (StringUtils.isBlank(tipoStr) || !StringUtils.isNumeric(tipoStr)) {
                    error.reject("tipoVehiculo", "El tipo de vehículo no es válido: " + tipoStr);
                    ret = false;
                } else {
                    tipo = TipoVehiculo.FULL_SET.stream()
                            .filter(x -> x.getId().equals(Integer.valueOf(tipoStr)))
                            .findAny()
                            .orElse(null);
                }

                String claseStr = formato.getClaseServicioVehiculo();
                ClaseServicioVehiculo clase = null;
                if (StringUtils.isBlank(claseStr) || !StringUtils.isNumeric(claseStr)) {
                    error.reject("claseServicioVehiculo", "La clase del vehículo no es válida: " + claseStr);
                    ret = false;
                } else {
                    clase = ClaseServicioVehiculo.FULL_SET.stream()
                            .filter(x -> x.getId().equals(Integer.valueOf(claseStr)))
                            .findAny().orElse(null);
                    if (clase == null) {
                        error.reject("claseServicioVehiculo", "La clase del vehículo no es válida: " + claseStr);
                        ret = false;
                    }
                }

                if (ret) {
                    vehiculo = new Vehiculo(placa, tipo, clase);
                    vehiculoRepo.save(vehiculo);
                }
            }
            comparendo.setVehiculo(vehiculo);
        }

        return ret;
    }
}
