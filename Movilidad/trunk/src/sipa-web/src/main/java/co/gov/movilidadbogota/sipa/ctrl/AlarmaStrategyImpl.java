package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.aut.UsuarioRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;
import co.gov.movilidadbogota.sipa.data.gen.*;
import co.gov.movilidadbogota.sipa.data.util.BaseUtils;
import javafx.util.Pair;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by Admin on 26/07/2017.
 */

@Component
public class AlarmaStrategyImpl implements AlarmaStrategy<Integer>{

    @Autowired
    MensajeRepo mensajeRepo;

    @Autowired
    UsuarioRepo usuarioRepo;

    @Autowired
    AlarmaRepo alarmaRepo;

    Map<Integer, Date> derechosPeticion;

    public AlarmaStrategyImpl() {
        derechosPeticion = new HashMap<>();
        try {
            derechosPeticion.put(1, Constants.DATE_FORMAT.parse("2017-07-23"));
            derechosPeticion.put(2, Constants.DATE_FORMAT.parse("2017-07-25"));
            derechosPeticion.put(3, Constants.DATE_FORMAT.parse("2017-07-24"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, Date> findDateMap() {
        Map<Integer, Date> map = new HashMap<>();
        for(Integer k : derechosPeticion.keySet()) {
            Date fechaRadicacion = derechosPeticion.get(k);
            Date fechaVencimiento = DateUtils.addDays(fechaRadicacion, 5);
            map.put(k, fechaVencimiento);
        }
        return map;
    }

    @Override
    public MensajeAlarma getMessage(Integer id) {
        MensajeAlarma mensaje = new MensajeAlarma();
        mensaje.setMensaje("Por favor responder el derecho de petición " + id);
        mensaje.setDestinatario(Usuario.ROOT);
        return mensaje;
    }

}
