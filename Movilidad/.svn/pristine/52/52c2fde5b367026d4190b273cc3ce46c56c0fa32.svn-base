package co.gov.movilidadbogota.sipa.bean;

import co.gov.movilidadbogota.sipa.data.loc.Municipio;
import co.gov.movilidadbogota.sipa.data.loc.MunicipioRepo;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import co.gov.movilidadbogota.sipa.util.ParametroEditor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.Locale;

/**
 * Bean para el editor de parametro de municipio
 *
 * @author lorenzo.pinango
 */
public class MunicipioParametroEditor implements ParametroEditor<Municipio> {

    @Autowired
    MunicipioRepo municipioRepo;

    @Override
    public String getTemplate() {
        return "editor-object.ftl";
    }

    @Override
    public String getWidget(String valor) {
        return "<@editorObject list=__MUNICIPIO_LIST__ />";
    }

    @Override
    public String marshal(Municipio obj) {
        return obj.getId();
    }

    @Override
    public Municipio unmarshal(String content) {
        return municipioRepo.findByIdAndFecha(content, new Date());
    }

    @Override
    public void fillModel(Model model, Session session, String metadata,
                          MessageSource messageSource, Locale locale) {
        model.addAttribute("__MUNICIPIO_LIST__",
                ControllerUtils.listToMapWithDefault(municipioRepo.findAll(new Sort(
                                Sort.Direction.ASC,
                                "nombre"
                        )),
                        "nombre", messageSource, locale));
    }
}
