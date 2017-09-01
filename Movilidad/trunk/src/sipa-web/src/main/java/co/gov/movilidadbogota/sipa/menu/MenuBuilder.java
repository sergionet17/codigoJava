package co.gov.movilidadbogota.sipa.menu;

import co.gov.movilidadbogota.sipa.ctrl.*;
import co.gov.movilidadbogota.sipa.data.aut.Permiso;
import org.springframework.stereotype.Component;

/**
 * Componente en el cual se mapean las diferentes opciones de menú.
 *
 * @author arturo.cruz
 */
@Component
public class MenuBuilder {

    private final Object menuLock = new Object();
    private Menu menu;

    public Menu getMenu() {
        if (menu != null) {
            return menu;
        } else {
            synchronized (menuLock) {
                if (menu != null) {
                    return menu;
                }
                menu = build();
                return menu;
            }
        }
    }

    private Menu build() {
        Menu menu = new Menu("root", "", "Todos los menús", Menu.ALL);
        Menu menuPrimary = new Menu("primary", "", "No disponible (no existe descripción gráfica)", Menu.ALL);

        menuPrimary
                .addSubmenu("Tablero de control", "/tableros", "Tablero de control del usuario donde se muestran indicadores de interés", Menu.IS_AUTHENTICATED).and()
                .addSubmenu("Bandejas", "/bandejas", "Bandejas de entrada correspondientes a elementos del usuario", Menu.IS_AUTHENTICATED)
                .addSubmenu("Tareas", "/bandejas/tareas", "Tareas pendientes por ejecutar asignadas al usuario", Menu.IS_AUTHENTICATED).and()
                .addSubmenu("Tareas de Usuario", "/tareas/consulta", "Tareas pendientes por ejecutar asignadas al usuario gestión documental", Menu.IS_AUTHENTICATED).and()
                .addSubmenu("Correspondencia", "/bandejas/correspondencia", "Documentos cuyo destinatario es el usuario", Menu.IS_AUTHENTICATED).and()
                .addSubmenu("Mensajes", "/bandejas/mensajes", "Mensajes destinados al usuario y que fueron generados por el sistema", Menu.IS_AUTHENTICATED).and()
                .and()
                .addSubmenu("Consultas", "", "", Menu.ALL, true)
                .addSubmenu("Vehículos", VehiculoController.CONTROLLER_PATH_CONSULTA, "Consulta de vehículos", Menu.ALL).and()
                .addSubmenu("Comparendos", ComparendoController.CONTROLLER_PATH_CONSULTA, "Consulta de comparendos", Menu.ALL).and()
                .addSubmenu("Personas", PersonaController.CONTROLLER_PATH_CONSULTA, "Consulta de personas", Menu.ALL);

        menu
                .addSubmenu(menuPrimary, menu);

        Menu menuSecundary = new Menu("secondary", "", "", Menu.ALL);

        Menu menuAdministracion = new Menu("Administración y seguridad", "", "", Menu.ALL, true);

        menuAdministracion
                .addSubmenu("Administrar roles", "/rol", "Diseño de los roles de usuario", Permiso.ADMIN_ROL).and()
                .addSubmenu("Administrar perfiles", "/perfil", "Diseño de perfiles de usuario", Permiso.ADMIN_PERFIL).and()
                .addSubmenu("Administrar usuarios", "/usuario", "Administración de los usuarios del sistema", Permiso.ADMIN_USUARIO).and()
                .addSubmenu("Administrar dependencias", "/dependencia", "Administración de las dependencias de la entidad", Permiso.ADMIN_DEPENDENCIA).and()
                .addSubmenu("Parámetros de proceso", ParametroController.CONTROLLER_PATH_LIST, "Administración de los parámetros del sistema en general", Permiso.ADMIN_PARAMETRO).and()
                .addSubmenu("Administrar tablas de referencia", TablaReferenciaController.CONTROLLER_PATH_LIST, "Administración de las tablas de referencia", Permiso.ADMIN_TABLA_REFERENCIA).and()
                .addSubmenu("Administrar alarmas", "/alarmas", "", Permiso.MODIFICAR_ALARMA).and();

        menuSecundary
                .addSubmenu(menuAdministracion, menuSecundary);

        Menu menuContravenciones = new Menu("Contravenciones", "", "", Menu.ALL, true);

        menuContravenciones
                .addSubmenu("Comparendos inconsistentes", "/inconsistencias/consulta", "", Menu.ALL).and()
                .addSubmenu("Registro rangos de numeración de comparendos", "/rangos", "", Menu.ALL).and()
                .addSubmenu("Registrar la evidencia de solicitud <br> de rangos de numeración", "/evidenciaAnulacion", "", Menu.ALL).and()
                .addSubmenu("Anulación rangos de numeración de comparendos", "/rangosAnulacion", "", Menu.ALL).and()
                .addSubmenu("Registro de cursos", "/cursos", "", Menu.ALL).and()
                .addSubmenu("Registrar Asistencia a curso pedagógico", "/curso_asistencia", "", Menu.ALL).and()
                .addSubmenu("Parametrizar los periodos de tiempo <br> para audiencias de continuación", "/agendamiento-audiencia", "", Menu.ALL).and()
                .addSubmenu("Asignar turnos de firma automática", TurnoFirmaController.CONTROLLER_PATH_NEW, "", Menu.ALL);
        
        menuSecundary
                .addSubmenu(menuContravenciones, menuSecundary);

        Menu menuFinanciero = new Menu("Financiero", "", "", Menu.ALL, true);

        menuFinanciero
                .addSubmenu("Pagos no aplicados", FinancieroController.PATH_PAGOS_NO_APLICADOS, "").and()
                .addSubmenu("Cargar archivo de pagos", FinancieroController.PATH_PAGOS, "").and()
                .addSubmenu("Realizar devolución", "/sipa/construccion", "");

        menuSecundary
                .addSubmenu(menuFinanciero, menuSecundary);

        Menu menuSegundaInstancia = new Menu("Segunda instancia", "", "", Menu.ALL, true);

        menuSegundaInstancia
                .addSubmenu("Recursos de apelación pendientes de fallo", "/sipa/construccion", "");

        menuSecundary
                .addSubmenu(menuSegundaInstancia, menuSecundary);

        Menu menuInfraccionTransportePublico = new Menu("Transporte Público", "", "", Menu.ALL, true);

        menuInfraccionTransportePublico
                .addSubmenu("Trámites de Investigación", TransPublicoRepartoController.CONTROLLER_PATH_CONSULTA, "", Menu.ALL).and()
                .addSubmenu("Averiguaciones", TransPublicoAveriguacionesPreliminaresController.CONTROLLER_PATH_CONSULTA, "", Menu.ALL);

        menuSecundary.addSubmenu(menuInfraccionTransportePublico, menuSecundary);


        menu.addSubmenu(menuSecundary, menu);

        return menu;
    }

}
