package co.gov.movilidadbogota.sipa.data.biz.gen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Representa el repositorio mediante el cual se maneja la persistencia de la entidad {@link Vehiculo}
 * Created by maria on 6/12/17.
 */
public interface VehiculoRepo extends JpaRepository<Vehiculo, UUID> {

    /**
     * Encuentra un vehículo por placa. La búsqueda es exácta.
     *
     * @param placa La placa del vehículo
     * @return
     */
    Vehiculo findByPlacaVehiculo(String placa);
}
