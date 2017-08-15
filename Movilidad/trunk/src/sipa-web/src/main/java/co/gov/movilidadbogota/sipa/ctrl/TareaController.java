package co.gov.movilidadbogota.sipa.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Marcel on 23/5/2017.
 */

@Controller
public class TareaController {
    @GetMapping("/tarea")
    public String list() {
        return "tarea";
    }
}
