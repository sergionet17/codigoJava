package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.TipoRangoNumeracion;
import co.gov.movilidadbogota.sipa.data.biz.num.RangoNumeracion;
import co.gov.movilidadbogota.sipa.data.biz.num.RangoNumeracionRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RangoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RangoNumeracionRepo rangoNumeracionRepo;


    @Test
    public void testVerficarRangosNumeracionExitoso() {
        RangoNumeracion rangoNumeracion;
        RangoNumeracion rangoNumeracion1;
        RangoNumeracion rangoNumeracion2;
        RangoNumeracion rangoNumeracion4;
        List<RangoNumeracion> listaRangosVerificadosInferior;
        List<RangoNumeracion> listaRangosVerificadosSuperior;
        long count;

        rangoNumeracion = new RangoNumeracion();
        rangoNumeracion.setId(UUID.randomUUID());
        rangoNumeracion.setInicio(80);
        rangoNumeracion.setFin(100);
        rangoNumeracion.setFechaSolicitud(new Date());
        entityManager.persist(rangoNumeracion);

        rangoNumeracion1 = new RangoNumeracion();
        rangoNumeracion1.setId(UUID.randomUUID());
        rangoNumeracion1.setInicio(60);
        rangoNumeracion1.setFin(79);
        rangoNumeracion1.setFechaSolicitud(new Date());
        entityManager.persist(rangoNumeracion1);

        rangoNumeracion2 = new RangoNumeracion();
        rangoNumeracion2.setId(UUID.randomUUID());
        rangoNumeracion2.setInicio(40);
        rangoNumeracion2.setFin(50);
        rangoNumeracion2.setFechaSolicitud(new Date());
        entityManager.persist(rangoNumeracion2);

        rangoNumeracion4 = new RangoNumeracion();
        rangoNumeracion4.setId(UUID.randomUUID());
        rangoNumeracion4.setInicio(1);
        rangoNumeracion4.setFin(30);
        rangoNumeracion4.setFechaSolicitud(new Date());
        entityManager.persist(rangoNumeracion4);


        listaRangosVerificadosInferior = rangoNumeracionRepo.findByInicioLessThanEqualAndFinGreaterThanEqualAndTipoRangoNumeracion(31, 31, TipoRangoNumeracion.COMPARENDO_TRANSITO);
        listaRangosVerificadosSuperior = rangoNumeracionRepo.findByInicioLessThanEqualAndFinGreaterThanEqualAndTipoRangoNumeracion
                (39, 39, TipoRangoNumeracion.INFORME_TRANSPORTE_PUBLICO);

        Assert.assertTrue(listaRangosVerificadosInferior.size() == 0);
        Assert.assertTrue(listaRangosVerificadosSuperior.size() == 0);
    }


    @Test
    public void testVerficarRangosNumeracionFallido() {
        RangoNumeracion rangoNumeracion;
        RangoNumeracion rangoNumeracion1;
        RangoNumeracion rangoNumeracion2;
        List<RangoNumeracion> listaRangosVerificadosInferior;
        List<RangoNumeracion> listaRangosVerificadosSuperior;
        long cantidadRangos;


        rangoNumeracion = new RangoNumeracion();
        rangoNumeracion.setId(UUID.randomUUID());
        rangoNumeracion.setInicio(80);
        rangoNumeracion.setFin(100);
        rangoNumeracion.setFechaSolicitud(new Date());
        entityManager.persist(rangoNumeracion);

        rangoNumeracion1 = new RangoNumeracion();
        rangoNumeracion1.setId(UUID.randomUUID());
        rangoNumeracion1.setInicio(60);
        rangoNumeracion1.setFin(79);
        rangoNumeracion1.setFechaSolicitud(new Date());
        entityManager.persist(rangoNumeracion1);

        rangoNumeracion2 = new RangoNumeracion();
        rangoNumeracion2.setId(UUID.randomUUID());
        rangoNumeracion2.setInicio(30);
        rangoNumeracion2.setFin(50);
        rangoNumeracion2.setFechaSolicitud(new Date());
        entityManager.persist(rangoNumeracion2);


        listaRangosVerificadosInferior = rangoNumeracionRepo.findByInicioLessThanEqualAndFinGreaterThanEqualAndTipoRangoNumeracion(31, 31, TipoRangoNumeracion.COMPARENDO_TRANSITO);
        listaRangosVerificadosSuperior = rangoNumeracionRepo.findByInicioLessThanEqualAndFinGreaterThanEqualAndTipoRangoNumeracion

                (39, 39, TipoRangoNumeracion.INFORME_TRANSPORTE_PUBLICO);

        Assert.assertFalse(listaRangosVerificadosInferior.size() == 0);
        Assert.assertFalse(listaRangosVerificadosSuperior.size() == 0);
    }


}
