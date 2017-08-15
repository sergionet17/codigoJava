package co.gov.movilidadbogota.sipa.data.aut;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Clase que representa un permiso del sistema, el cual se puede
 * asosciar a un rol
 *
 * @author arturo.cruz
 */
@Entity
public class Permiso {

    public static final String GENERAR_CERTIFICADO_ASISTENCIA = "GENERAR_CERTIFICADO_ASISTENCIA"; // Permite la generación del certificado de asistencia
    public static final String OBTENER_VISTA_VEHICULO = "OBTENER_VISTA_VEHICULO"; // Permite obtener la vista de un vehículo
    public static final String CONSULTA_VEHICULO = "CONSULTA_VEHICULO"; // Permite obtener la consulta de un vehículo
    public static final String OBTENER_VISTA_PERSONA = "OBTENER_VISTA_PERSONA"; // Permite obtener la vista de una persona
    public static final String CONSULTA_PERSONAS = "CONSULTA_PERSONAS"; // Permite la consulta de personas
    public static final String OBTENER_VISTA_COMPARENDO = "OBTENER_VISTA_COMPARENDO"; // Permite obtener la vista de un comparendo
    public static final String CONSULTA_COMPARENDO = "CONSULTA_COMPARENDO"; // Permite consultar los comparendos
    public static final String EXONERAR_SUBSANACION = "EXONERAR_SUBSANACION"; // Permite exonerar una subsanación
    public static final String DESAPLICAR_DESCUENTO = "DESAPLICAR_DESCUENTO"; // Permite desaplicar descuento
    public static final String APLICAR_DESCUENTO = "APLICAR_DESCUENTO"; // Permite aplicar descuento
    public static final String CONFIGURAR_TURNOS_FIRMA = "CONFIGURAR_TURNOS_FIRMA"; // Permite configurar los turnos de firma de las autoridades de transito
    public static final String PARAMETRIZAR_PERIODOS_AUDENCIAS = "PARAMETRIZAR_PERIODOS_AUDENCIAS"; // Permite parametrizar los tiempos de periodos de audiencias de continuación
    public static final String REGISTRAR_ASISTENCIA_CURSO_PEDAGOGICO = "REGISTRAR_ASISTENCIA_CURSO_PEDAGOGICO"; // Permite registrar la asistencia al curso pedagógico
    public static final String CREAR_REGISTRO_CURSO = "CREAR_REGISTRO_CURSO"; // Permite crear registro de nuevo curso pedagógico
    public static final String INSCRIBIR_CURSO_PEDAGOGICO = "INSCRIBIR_CURSO_PEDAGOGICO"; // Permite inscribirse en un curso pedagógico
    public static final String GENERAR_BOLANTE_PAGO = "GENERAR_BOLANTE_PAGO"; // Permite generar el bolante de pago
    
    public static final String ADMIN_ROL="ADMIN_ROL";//Permite administr public static final String ar los roles
    public static final String CREAR_ROL="CREAR_ROL";//Permite la creación de un rol
    public static final String LISTAR_ROL="LISTAR_ROL";//Permite listar los roles
    public static final String MODIFICAR_ROL="MODIFICAR_ROL";//Permite la modificación de un rol
    public static final String ADMIN_PERFIL="ADMIN_PERFIL";//Permite administrar los perfiles
    public static final String CREAR_PERFIL="CREAR_PERFIL";//Permite la ceación de un perfil
    public static final String LISTAR_PERFIL="LISTAR_PERFIL";//Permite listar los perfiles
    public static final String MODIFICAR_PERFIL="MODIFICAR_PERFIL";//Permite la modificación de un perfil
    public static final String ADMIN_USUARIO="ADMIN_USUARIO";//Permite administrar los usuarios del sistema
    public static final String CREAR_USUARIO="CREAR_USUARIO";//Permite la creación de un usuario
    public static final String LISTAR_USUARIO="LISTAR_USUARIO";//Permite listar los usuarios
    public static final String MODIFICAR_USUARIO="MODIFICAR_USUARIO";//Permite la modificación de un usuario
    public static final String ADMIN_DEPENDENCIA="ADMIN_DEPENDENCIA";//Permite administrar las dependencias
    public static final String CREAR_DEPENDENCIA="CREAR_DEPENDENCIA";//Permite la creación de una dependencia
    public static final String LISTAR_DEPENDENCIA="LISTAR_DEPENDENCIA";//Permite listar las dependencias
    public static final String MODIFICAR_DEPENDENCIA="MODIFICAR_DEPENDENCIA";//Permite la modificación de una dependencia
    public static final String ADMIN_PARAMETRO="ADMIN_PARAMETRO";//Permite administrar los parámetros
    public static final String LISTAR_PARAMETRO="LISTAR_PARAMETRO";//Permite listar los parámetros
    public static final String MODIFICAR_PARAMETRO="MODIFICAR_PARAMETRO";//Permite la modificación de un parámetro
    public static final String ADMIN_TABLA_REFERENCIA="ADMIN_TABLA_REFERENCIA";//Permite administrar las tablas de referencia
    public static final String LISTAR_TABLA_REFERENCIA="LISTAR_TABLA_REFERENCIA";//Permite listar las tablas de referencia
    public static final String MODIFICAR_TABLA_REFERENCIA="MODIFICAR_TABLA_REFERENCIA";//Permite la modificación de una tabla de referencia
    public static final String MODIFICAR_ALARMA ="MODIFICAR_ALARMA";//Permite la modificacion de alarmas
    
    
    @ManyToMany
    List<Rol> roles;
    @Id
    private String nombre;
    private
    String descripcion;

    public Permiso() {
    }

    public Permiso(String nombre) {
        this.nombre = nombre;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
