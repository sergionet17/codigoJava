package co.gov.movilidadbogota.sipa.util;

import org.hibernate.Session;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import java.util.Locale;

/**
 * Editor para los parámetros tipo Decimal
 *
 * @author lorenzo.pinango
 */
public class DecimalParametroEditor implements ParametroEditor<Float> {
    @Override
    public String getTemplate() {
        return "";
    }

    @Override
    public String getWidget(String valor) {
        return "<@l.formGroupText name=\"valor\" label=\"Valor\" placeholder=\"Valor\" " +
                "value=\"" + valor + "\" validator=\"decimalValidator\" size=\"15\" " +
                "class=\"form-control\" min=\"0\" max=\"2147483647\"/>";
    }

    @Override
    public String marshal(Float obj) {
        return obj.toString();
    }

    @Override
    public Float unmarshal(String content) {
        return Float.parseFloat(content);
    }

    @Override
    public void fillModel(Model model, Session session, String metadata, MessageSource messageSource, Locale locale) {

    }

}
