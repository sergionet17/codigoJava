package co.gov.movilidadbogota.sipa.menu;

import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: Sergio, documentar por favor
 *
 * @author arturo.cruz
 */
public class Menu {

    public static final String ALL = "*";
    public static final String IS_AUTHENTICATED = "isAuthenticated()";

    private Menu parent;
    private String label;
    private String url;
    private String description;
    private String permissions;
    private Map<String, String> properties = new HashMap<>();

    private List<Menu> submenu;

    private Boolean agrupador;

    public Menu(String label, String url) {
        this.label = label;
        this.url = url;
        this.agrupador = false;
    }

    public Menu(String label, String url, String description) {
        this.label = label;
        this.url = url;
        this.description = description;
        this.agrupador = false;
    }

    public Menu(String label, String url, String description, String permissions) {
        this.label = label;
        this.url = url;
        this.description = description;
        this.permissions = permissions;
        this.agrupador = false;
    }

    public Menu(String label, String url, String description, String permissions, Boolean agrupador) {
        this.label = label;
        this.url = url;
        this.description = description;
        this.permissions = permissions;
        this.agrupador = agrupador;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public List<Menu> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<Menu> submenu) {
        this.submenu = submenu;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Menu addPermissions(String permissions) {
        this.permissions = permissions;
        return this;
    }

    public Menu addSubmenu(Menu menu) {
        if (submenu == null)
            submenu = new ArrayList<>();
        menu.parent = this;
        submenu.add(menu);
        return menu;
    }

    public Menu addSubmenu(Menu menu, Menu parent) {
        if (submenu == null)
            submenu = new ArrayList<>();
        menu.parent = parent;
        submenu.add(menu);
        return menu;
    }

    public Menu addSubmenu(String label, String url) {
        return addSubmenu(new Menu(label, url));
    }

    public Menu addSubmenu(String label, String url, String description) {
        return addSubmenu(new Menu(label, url, description));
    }

    public Menu addSubmenu(String label, String url, String description, String permissions) {
        return addSubmenu(new Menu(label, url, description, permissions));
    }

    public Menu addSubmenu(String label, String url, String description, String permissions, Boolean agrupador) {
        return addSubmenu(new Menu(label, url, description, permissions, agrupador));
    }

    public Menu find(String label) {
        if (submenu == null)
            return null;

        for (Menu x : submenu) {
            if (x.getLabel().equals(label))
                return x;
        }
        return null;
    }

    public Menu and() {
        if (parent == null)
            return this;
        return parent;
    }

    public Menu filterBy(Authentication authentication) {

        boolean hasPermission = (
                ALL.equals(permissions)
                        || (IS_AUTHENTICATED.equals(permissions) && authentication != null && authentication.isAuthenticated())
                        || (permissions != null && authentication != null && authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals(permissions)))
        );
        if (!hasPermission)
            return null;

        Menu m = new Menu(label, url, description, permissions, agrupador);

        if (submenu == null || submenu.size() == 0) {
            return m;
        }

        boolean hasSubs = false;
        for (Menu sub : submenu) {
            Menu s = sub.filterBy(authentication);
            if (s != null) {
                m.addSubmenu(s);
                hasSubs = true;
            }
        }

        if (hasSubs) {
            return m;
        } else {
            return null;
        }

    }

    public Boolean getAgrupador() {
        return agrupador;
    }

    public void setAgrupador(Boolean agrupador) {
        this.agrupador = agrupador;
    }
}