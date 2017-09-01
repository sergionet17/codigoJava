package co.gov.movilidadbogota.sipa.data.aut;

import co.gov.movilidadbogota.sipa.data.gen.Constants;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Clase que representa un perfil del sistema el cual puede
 * asociarse a un usuario
 *
 * @author lorenzo.pinango
 */
@Entity
public class Perfil {

    public static final String NOMBRE_ADMIN = "Administrador de usuarios";
    private static final Boolean ESTADO_ACTIVO = true;
    public static final Perfil ADMIN_USUARIOS = new Perfil(
            UUID.fromString("a6c8f873-70a1-4801-acbe-65e8fa1a23d2"),
            NOMBRE_ADMIN,
            Collections.singletonList(Rol.ADMIN_USUARIOS)
    );
    @Id
    private
    UUID id;

    @NotNull
    @Size(max = Constants.VALOR_MAXIMO_NOMBRE)
    private
    String nombre;

    @NotNull()
    private Boolean activo;

    @ManyToMany
    private
    List<Rol> roles;

    public Perfil() {
    }

    public Perfil(UUID id, String nombre, List<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.roles = roles;
        activo = Boolean.TRUE;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
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

    public static Perfil create(String nombre, List<Rol> rols) {
        Perfil perfil;
        perfil = new Perfil();

        perfil.setActivo(Perfil.ESTADO_ACTIVO);
        perfil.setId(UUID.randomUUID());
        perfil.setNombre(nombre);
        perfil.setRoles(rols);

        return perfil;
    }
}
