package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.InfraccionRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.InmovilizacionRepo;
import co.gov.movilidadbogota.sipa.serv.comparendos.validator.OldFormatoComparendoValidator;
import co.gov.movilidadbogota.sipa.serv.externos.DuppsWebServicesImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;

/**
 * Created by Admin on 28/06/2017.
 */

@RunWith(SpringRunner.class)
public class FormatoValidorTest {

    @InjectMocks
    OldFormatoComparendoValidator oldFormatoComparendoValidator;

    @Mock
    InfraccionRepo infraccionRepo;

    @Mock
    InmovilizacionRepo inmovlizacionRepo;

    @Mock
    DuppsWebServicesImpl duupsWebsServices;


    @Test
    public void testFormatValidator() {
        MapBindingResult mapBindingResult;
        FormatoComparendo formatoComparendo;
        formatoComparendo = new FormatoComparendo();
        mapBindingResult = new MapBindingResult(new HashMap<String, String>(), "formatoValidator");
        // oldFormatoComparendoValidator.validar(formatoComparendo, mapBindingResult);
        mapBindingResult.toString();
    }
}
