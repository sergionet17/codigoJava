package co.gov.movilidadbogota.sipa.data.aut;

import co.gov.movilidadbogota.sipa.data.gen.Constants;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Clase que representa un rol del sistema, el cual se
 * puede asociar a un perfil
 *
 * @autor arturo.cruz
 */
@Entity
public class Rol {

    public static final String NOMBRE_ADMIN = "Administración de usuarios";

    public static final Rol ADMIN_USUARIOS = new Rol(
            UUID.fromString("c7fa943e-8c31-4cf1-8d55-b786a5c96144"),
            NOMBRE_ADMIN,
            "Otorga los accesos necesarios para la administración de usuarios",
            Arrays.asList(
                    new Permiso(Permiso.ADMIN_USUARIO),
                    new Permiso(Permiso.LISTAR_USUARIO),
                    new Permiso(Permiso.CREAR_USUARIO),
                    new Permiso(Permiso.MODIFICAR_USUARIO),
                    new Permiso(Permiso.ADMIN_ROL),
                    new Permiso(Permiso.LISTAR_ROL),
                    new Permiso(Permiso.CREAR_ROL),
                    new Permiso(Permiso.MODIFICAR_ROL),
                    new Permiso(Permiso.ADMIN_PERFIL),
                    new Permiso(Permiso.LISTAR_PERFIL),
                    new Permiso(Permiso.CREAR_PERFIL),
                    new Permiso(Permiso.MODIFICAR_PERFIL),
                    new Permiso(Permiso.MODIFICAR_ALARMA)
            )
    );
    @ManyToMany
    List<Permiso> permisos;
    @Id
    private
    UUID id;
    @NotNull
    @Size(max = Constants.VALOR_MAXIMO_NOMBRE)
    private String name;
    @Size(max = Constants.VALOR_MAXIMO_DESCRIPCION)
    private
    String descripcion;
    @ManyToMany
    private
    List<Perfil> perfil;
    @NotNull()
    private Boolean activo;

    public Rol() {
    }

    public Rol(UUID id) {
        this.id = id;
    }

    public Rol(UUID uuid, String name, String descripcion, List<Permiso> permisos) {
        this.id = uuid;
        this.name = name;
        this.descripcion = descripcion;
        this.permisos = permisos;
        this.activo = Boolean.TRUE;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getDescripcionEstado() {
        if (activo)
            return "Activo";
        else
            return "Inactivo";
    }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", name= " + name +
                ", descripcion= " + descripcion +
                '}';
    }
}
