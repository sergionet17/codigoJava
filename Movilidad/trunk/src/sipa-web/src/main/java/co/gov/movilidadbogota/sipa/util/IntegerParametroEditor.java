package co.gov.movilidadbogota.sipa.util;

import org.hibernate.Session;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import java.util.Locale;

/**
 * Editor para los parámetros tipo Integer
 *
 * @author lorenzo.pinango
 */
public class IntegerParametroEditor implements ParametroEditor<Integer> {
    @Override
    public String getTemplate() {
        return "";
    }

    @Override
    public String getWidget(String valor) {
        return "<@l.formGroupText name=\"valor\" label=\"Valor\" placeholder=\"Valor\"" +
                " value=\"" + valor + "\" validator=\"numberValidator\" size=\"10\" " +
                "class=\"form-control\" min=\"0\" max=\"2147483647\"/>";
    }

    @Override
    public String marshal(Integer obj) {
        return obj.toString();
    }

    @Override
    public Integer unmarshal(String content) {
        return Integer.parseInt(content);
    }

    @Override
    public void fillModel(Model model, Session session, String metadata, MessageSource messageSource, Locale locale) {

    }

}
