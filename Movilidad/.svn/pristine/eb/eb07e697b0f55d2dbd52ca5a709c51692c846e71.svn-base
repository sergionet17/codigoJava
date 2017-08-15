package co.gov.movilidadbogota.sipa.ctrl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



/**
 * Created by Marcel.Bohorquez on 3/14/17.
 */
@Controller
@RequestMapping(value = "/tareas")
public class TareasController extends UtilController {


    private static final String ESTADO_FORMATO_COMPARENDO_INCONSISTENTE = "3";


    private static final Logger LOG = LoggerFactory.getLogger(TareasConsulta.class);
    @Autowired


    @RequestMapping(value = "/consulta")
    public String consulta() {
        return "tareas-consulta";
    }

    public interface StringArrayMapper<T> {
        String[] build(T t);
    }


}
