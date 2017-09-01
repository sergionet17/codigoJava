package co.gov.movilidadbogota.sipa.util;

import org.hibernate.Session;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import java.util.Locale;

/**
 * Editor para los parámetros tipo File
 *
 * @author lorenzo.pinango
 */
public class FileParametroEditor implements ParametroEditor<String> {
    @Override
    public String getTemplate() {
        return "";
    }

    @Override
    public String getWidget(String valor) {
        return "<input type=\"hidden\" class=\"form-control\" name=\"valor\" " +
                "value=\"file\" id=\"valor\"/>\n" +
                "<@l.formGroupFile name=\"valorFile\" label=\"Valor\" " +
                "placeholder=\"Valor\" value=\"\" class=\"form-control\" accept=\"\"/>";
    }

    @Override
    public String marshal(String obj) {
        return obj;
    }

    @Override
    public String unmarshal(String content) {
        return content;
    }

    @Override
    public void fillModel(Model model, Session session, String metadata, MessageSource messageSource, Locale locale) {

    }

}
