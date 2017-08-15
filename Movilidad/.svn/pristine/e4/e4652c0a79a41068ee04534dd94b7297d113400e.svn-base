/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.menu.Menu;
import co.gov.movilidadbogota.sipa.menu.MenuBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador que muestra lo referente a mapa de navegación
 *
 * @author sergio.guerrero
 */
@Controller
public class NavigationController extends BaseController {

    public static final String CONTROLLER_PATH = "/navigation";
    public static final String CONTROLLER_NAVIGATION_MAP = CONTROLLER_PATH + "/mapa";

    @Autowired
    MenuBuilder menuBuilder;

    @GetMapping(value = CONTROLLER_NAVIGATION_MAP)
    public String map(Model model) {
        // No hay que dejar nada específico en el modelo ya que el menú lo pone UserInfoInterceptor
        // La manera en la que se obtiene el menú es la siguiente
        Menu menu = menuBuilder.getMenu();
        model.addAttribute("mapaMenu", menu);
        return "navigation-map";
    }

}
