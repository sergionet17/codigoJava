package co.gov.movilidadbogota.sipa.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by acpreda on 7/6/17.
 */
@Controller
public class ErrorController extends BaseController {

    @GetMapping("/404")
    public String e404() {
        return "error/404";
    }

    @GetMapping("/401")
    public String e401() {
        return "error/401";
    }

    @GetMapping("/403")
    public String e403() {
        return "error/401";
    }

    @GetMapping("/500")
    public String e500() {
        return "error/500";
    }

    @GetMapping("/error-intencional")
    public String errorIntencional() {
        throw new RuntimeException("Este es un error intencional para pruebas de la página 500");
    }

}
