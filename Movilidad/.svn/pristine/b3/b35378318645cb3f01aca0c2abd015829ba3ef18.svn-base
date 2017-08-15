package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.Curso;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.gen.Parametro;
import co.gov.movilidadbogota.sipa.data.gen.ValorParametroRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que valida la información de {@link Curso}.
 *
 * @author paola.charry
 */
public class CursoValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(CursoValidator.class);

    public String MAX_HORAS_PERMITIDAS_CURSO_PEDAGOGICO;

    private ValorParametroRepo valorParametroRepo;

    public CursoValidator(ValorParametroRepo valorParametroRepo) {
        MAX_HORAS_PERMITIDAS_CURSO_PEDAGOGICO = valorParametroRepo.findValorVigenteByClave(
                Parametro.MAX_HORAS_PERMITIDAS_CURSO_PEDAGOGICO,
                new Date()
        ).getValor();
    }

    @Override
    public boolean supports(Class aClass) {
        return Curso.class.equals(aClass);
    }

    /**
     * Valida la información ingresada en el formulario
     *
     * @param o      Data del formulario
     * @param errors Errores luego de realizar la inspeccion de validaciones de negocio
     *               la data ingresada en el formulario
     */

    @Override
    public void validate(Object o, Errors errors) {
        logger.info("Se realiza validaciones a la data del formulario");
        Curso curso = (Curso) o;
        //Se valida que los campos no tenga vacia
        if (curso.getTema() == null) {
            errors.rejectValue("tema", "curso.tema.required");
        } else if (curso.getTema().isEmpty()) {
            errors.rejectValue("tema", "curso.tema.required");
        }
        SimpleDateFormat cDate = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN_DDMMYY);
        String date2 = cDate.format(new Date());

        if (curso.getFecha() == null) {
            errors.rejectValue("fecha", "curso.fecha.required");
        } else {
            cDate = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN_DDMMYY);
            String date1 = cDate.format(curso.getFecha());

            if (date1.compareTo(date2) < 0) {
                errors.rejectValue("fecha", "curso.fecha.menor");
            }
        }
        if (curso.getHoraInicial() == null || curso.getHoraInicial().isEmpty() || curso.getHoraInicial().equals("__:__")) {
            errors.rejectValue("horaInicial", "curso.horaInicial.required");
        }
        if (curso.getHoraFinal() == null || curso.getHoraFinal().isEmpty() || curso.getHoraFinal().equals("__:__")) {
            errors.rejectValue("horaFinal", "curso.horaFinal.required");
        }
        if (!curso.getHoraInicial().isEmpty() && !curso.getHoraFinal().isEmpty() && !curso.getHoraFinal().equals("__:__") && !curso.getHoraInicial().equals("__:__")) {
            String[] splitString = curso.getHoraInicial().split(":");

            Integer horaInicial = Integer.valueOf(splitString[0]);
            Integer minutoInicial = Integer.valueOf(splitString[1]);

            splitString = curso.getHoraFinal().split(":");

            Integer horaFinal = Integer.valueOf(splitString[0]);
            Integer minutoFinal = Integer.valueOf(splitString[1]);


            Integer diferencia = horaFinal - horaFinal;

            if (horaInicial > horaFinal) {
                errors.rejectValue("horaInicial", "curso.horaInicial.menor");
            } else if (horaInicial == horaFinal && minutoInicial > minutoFinal) {
                errors.rejectValue("horaInicial", "curso.horaInicial.minutomenor");
            } else if (
                    diferencia > new Integer(MAX_HORAS_PERMITIDAS_CURSO_PEDAGOGICO)) {
                errors.rejectValue("horaInicial", "curso.horaInicial.horamayor");
            }
        }
        if (curso.getSalon() == null) {
            errors.rejectValue("salon.id", "curso.salon.required");
        } else if (curso.getSalon().getId() == null) {
            errors.rejectValue("salon.id", "curso.salon.required");
        }
        if (curso.getInstructor() == null) {
            errors.rejectValue("instructor", "curso.instructor.required");
        }
        if (curso.getMaximoAsistentes() != null) {
            if (curso.getMaximoAsistentes() < 0) {
                errors.rejectValue("maximoAsistentes", "curso.maximoAsistentes.negative");
            } else if (curso.getMaximoAsistentes() == 0) {
                errors.rejectValue("maximoAsistentes", "curso.maximoAsistentes.zero");
            }
        }
    }


}
