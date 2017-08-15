package co.gov.movilidadbogota.sipa.data.biz.coa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by maria on 5/3/17.
 */
@Entity
public class DecisionObjecionSecuestro {

    @Id
    Integer id;

    @NotNull
    String name;
}
