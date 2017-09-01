package co.gov.movilidadbogota.sipa.data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by acpreda on 7/13/17.
 */
@MappedSuperclass
public class IdSupport<T> {

    @Id
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

}
