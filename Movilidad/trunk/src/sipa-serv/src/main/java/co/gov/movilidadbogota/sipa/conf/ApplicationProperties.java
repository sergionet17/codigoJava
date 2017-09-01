package co.gov.movilidadbogota.sipa.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by acpreda on 6/27/17.
 */
@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    private ApplicationProperties.Cmis cmis = new Cmis();
    private String method;
    private ComparendosProperties comparendos;
    private String sharepointPrefix;

    public Cmis getCmis() {
        return cmis;
    }

    public void setCmis(Cmis cmis) {
        this.cmis = cmis;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ComparendosProperties getComparendos() {
        return comparendos;
    }

    public void setComparendos(ComparendosProperties comparendos) {
        this.comparendos = comparendos;
    }

    public String getSharepointPrefix() {
        return sharepointPrefix;
    }

    public void setSharepointPrefix(String sharepointPrefix) {
        this.sharepointPrefix = sharepointPrefix;
    }

    public static class Cmis {

        private String username;
        private String password;
        private String serviceRoot;
        private String repositoryId;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getServiceRoot() {
            return serviceRoot;
        }

        public void setServiceRoot(String serviceRoot) {
            this.serviceRoot = serviceRoot;
        }

        public String getRepositoryId() {
            return repositoryId;
        }

        public void setRepositoryId(String repositoryId) {
            this.repositoryId = repositoryId;
        }
    }

    public static class ComparendosProperties {

        private ValidatorProperties validator;

        public ValidatorProperties getValidator() {
            return validator;
        }

        public void setValidator(ValidatorProperties validator) {
            this.validator = validator;
        }

        public static class ValidatorProperties {

            private Boolean fechaImposicion;

            public Boolean getFechaImposicion() {
                return fechaImposicion;
            }

            public void setFechaImposicion(Boolean fechaImposicion) {
                this.fechaImposicion = fechaImposicion;
            }
        }

    }

}
