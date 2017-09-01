package co.gov.movilidadbogota.sipa.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Marcel on 7/06/2017.
 */

@Controller
public class Error404Controller {

    @GetMapping("/error_404")
    public String login() {
        return "error_404";
    }

}
