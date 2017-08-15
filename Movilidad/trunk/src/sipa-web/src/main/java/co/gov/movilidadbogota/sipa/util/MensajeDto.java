package co.gov.movilidadbogota.sipa.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego Gomez on 11/07/2017.
 */
public class MensajeDto {
    /**
     * Asunto del mensaje
     */
    private String asunto;

    /**
     * Direcciones de destino
     */
    private List<String> destinos;

    /**
     * Cuerpo del mensaje
     */
    private PlantillaDto plantilla;

    /**
     * Obtiene el valor de asunto.
     *
     * @return asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Asigna el valor de asunto.
     *
     * @param asunto asunto
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * Obtiene el valor de destino.
     *
     * @return destinos
     */
    public String getDestino() {
        return destinos.get(0);
    }

    /**
     * Asigna el valor de destino.
     *
     * @param destino destino
     */
    public void setDestino(String destino) {
        this.destinos = new ArrayList<>();
        this.destinos.add(destino);
    }

    /**
     * Obtiene el valor de destinos.
     *
     * @return destinos
     */
    public List<String> getDestinos() {
        return destinos;
    }

    /**
     * Asigna el valor de destinos.
     *
     * @param destinos destinos
     */
    public void setDestinos(List<String> destinos) {
        this.destinos = destinos;
    }

    /**
     * Obtiene el valor de plantilla.
     *
     * @return plantilla
     */
    public PlantillaDto getPlantilla() {
        return plantilla;
    }

    /**
     * Asigna el valor de plantilla.
     *
     * @param plantilla plantilla
     */
    public void setPlantilla(PlantillaDto plantilla) {
        this.plantilla = plantilla;
    }


}
