package co.gov.movilidadbogota.sipa.data.gen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio para la clase {@link CategoriaParametro}
 *
 * @author lorenzo.pinango
 */
public interface CategoriaParametroRepo extends JpaRepository<CategoriaParametro, String> {
    /**
     * Encuentra las categorias que no tienen categoria superior
     *
     * @return Lista de categorias
     */
    @Query("SELECT c FROM CategoriaParametro c WHERE c.parent IS NULL")
    List<CategoriaParametro> findAllCategoriasPrimarias();

    /**
     * Lista los parametros de una categoria
     *
     * @param clave Identificador de la categoria
     * @return Listado de parametros
     */
    @Query(value = "SELECT c FROM CategoriaParametro c " +
            "JOIN c.parametros p LEFT JOIN p.valores v where c.clave = ?1 " +
            "AND v.finVigencia IS NULL")
    CategoriaParametro findAllParametrosByClaveLastValue(String clave);
}
