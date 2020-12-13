package des.springboot_hibernate_security.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import des.springboot_hibernate_security.entidades.Email;

@Repository
@Component("EmailDao")
public class EmailDaoImpl extends DaoGenericoImpl<Email> implements EmailDao{

}
