package co.gov.movilidadbogota.cmis.database;

import javax.annotation.Generated;

/**
 * Una clase con un conjunto de propiedades de conexión
 * para poder hacer set de una sola vez.
 */
public class DatabaseProperties {
    private String user;
    private String password;
    private String hostname;
    private String databaseName;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
