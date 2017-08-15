package co.gov.movilidadbogota.sipa.trans;



import co.gov.movilidadbogota.sipa.data.gen.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Clase para gestionar la informacion de un usuario del
 * directorio activo
 *
 * @author lorenzo.pinango
 */
public class UsuarioActiveDirectory {

    /**
     *
     */
    private static final long serialVersionUID = 9081527761576640803L;

    private String uid;
    private String cn;
    private String sn;
    private String userPassword;
    private String postalAddress;
    private String telephoneNumber;
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT_PATTERN)
    private Date pwdAccountLockedTime;

    /**
     * @return the uid
     */
    public synchronized final String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public synchronized final void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the cn
     */
    public synchronized final String getCn() {
        return cn;
    }

    /**
     * @param cn
     *            the cn to set
     */
    public synchronized final void setCn(String cn) {
        this.cn = cn;
    }

    /**
     * @return the sn
     */
    public synchronized final String getSn() {
        return sn;
    }

    /**
     * @param sn
     *            the sn to set
     */
    public synchronized final void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * @return the userPassword
     */
    public synchronized final String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword
     *            the userPassword to set
     */
    public synchronized final void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * @return the postalAddress
     */
    public synchronized final String getPostalAddress() {
        return postalAddress;
    }

    /**
     * @param postalAddress
     *            the postalAddress to set
     */
    public synchronized final void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    /**
     * @return the telephoneNumber
     */
    public synchronized final String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * @param telephoneNumber
     *            the telephoneNumber to set
     */
    public synchronized final void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * @return the pwdAccountLockedTime
     */
    public synchronized final Date getPwdAccountLockedTime() {
        return pwdAccountLockedTime;
    }

    /**
     * @param pwdAccountLockedTime
     *            the telephoneNumber to set
     */
    public synchronized final void setPwdAccountLockedTime(Date pwdAccountLockedTime) {
        this.pwdAccountLockedTime = pwdAccountLockedTime;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [");
        if (uid != null) {
            builder.append("uid=");
            builder.append(uid);
            builder.append(", ");
        }
        if (cn != null) {
            builder.append("cn=");
            builder.append(cn);
            builder.append(", ");
        }
        if (sn != null) {
            builder.append("sn=");
            builder.append(sn);
            builder.append(", ");
        }
        if (userPassword != null) {
            builder.append("userPassword=");
            builder.append(userPassword);
            builder.append(", ");
        }
        if (postalAddress != null) {
            builder.append("postalAddress=");
            builder.append(postalAddress);
            builder.append(", ");
        }
        if (telephoneNumber != null) {
            builder.append("telephoneNumber=");
            builder.append(telephoneNumber);
            builder.append(", ");
        }
        if (pwdAccountLockedTime != null) {
            builder.append("pwdAccountLockedTime=");
            builder.append(pwdAccountLockedTime);
        }
        builder.append("]");
        return builder.toString();
    }

}
