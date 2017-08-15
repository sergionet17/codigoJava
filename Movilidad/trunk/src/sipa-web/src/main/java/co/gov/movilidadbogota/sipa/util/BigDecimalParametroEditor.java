package co.gov.movilidadbogota.sipa.util;


import org.hibernate.Session;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

/**
 * Editor para los parámetros tipo BigDecimal
 *
 * @author lorenzo.pinango
 */
public class BigDecimalParametroEditor implements ParametroEditor<BigDecimal> {
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
    public String marshal(BigDecimal obj) {
        return obj.toString();
    }

    @Override
    public BigDecimal unmarshal(String content) {
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator(',');
            symbols.setDecimalSeparator('.');
            String pattern = "#,##0.0#";
            DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
            decimalFormat.setParseBigDecimal(true);
            return (BigDecimal) decimalFormat.parse(content);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void fillModel(Model model, Session session, String metadata, MessageSource messageSource, Locale locale) {

    }

}
