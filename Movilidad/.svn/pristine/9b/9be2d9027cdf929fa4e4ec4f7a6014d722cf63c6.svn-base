package co.gov.movilidadbogota.sipa.sso.conf;

import co.gov.movilidadbogota.sipa.data.aut.*;
import co.gov.movilidadbogota.sipa.data.gen.Parametro;
import co.gov.movilidadbogota.sipa.data.gen.ValorParametroRepo;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Componente para obtener la informacion del usuario
 *
 * @author lorenzo.pinango
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UsuarioRepo userRepository;

    @Autowired
    private ValorParametroRepo valorParametroRepo;

    @Autowired
    private
    HistoricoPasswordRepo historicoPasswordRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username)  {

        log.info("Se realiza autenticacion del usuario");
        log.info("Username: " + username);

        Usuario userFromDatabase = userRepository.findOneByUsername(username);

        if (userFromDatabase == null) {
            log.info("Error no se encontro el usuario " + username + " en la BD");
            throw new UserNotFoundException("El usuario " + username + " no se encontr&oacute; en la base de datos");
        } else if (!userFromDatabase.getActivo()) {
            log.info("Error el usuario " + username + "  nos eencuentra activo");
            throw new UserNotActivatedException("El usuario " + username + " no se encuentra activo");
        }

        //Se verifica si la cuenta esta caduca
        if (userFromDatabase.getFechaCaducidad().compareTo(new Date()) < 0) {
            log.info("Error el usuario " + username + " esta expirado");
            throw new UserExpiredException("El usuario " + username +
                    " no puede ingresar al sistema ya que su fecha de caducidad" +
                    " expir&oacute;");
        }

        Integer days = Integer.parseInt(valorParametroRepo.findValorVigente(Parametro.CANTIDAD_DIAS_VENCIMIENTO_PASSWORD).getValor());
        List<HistoricoPassword> historicoList = historicoPasswordRepo.findByUsuarioId(
                userFromDatabase.getId(),
                new PageRequest(0, 1,
                        new Sort(
                                new Sort.Order(
                                        Sort.Direction.DESC, "fechaRegistro"))));

        Date date = DateUtils.addDays(historicoList.get(0).getFechaRegistro(),days);
        if(new Date().after(date)){
            throw new PasswdExpiredException("El usuario '" + username +
                    "' no puede ingresar al sistema ya que la fecha de caducidad de la contrase&ntildea" +
                    " expir&oacute. Por favor cambie su Contrase&ntildea.");

        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Rol rol : userFromDatabase.getPerfil().getRoles()) {
            for (Permiso permiso : rol.getPermisos()) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permiso.getNombre());
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return new org.springframework.security.core.userdetails.User(userFromDatabase.getUsername(), userFromDatabase.getPassword(), grantedAuthorities);
    }
}
