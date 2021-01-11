package des.springboot_hibernate.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import des.springboot_hibernate.entidades.Email;

@Repository
@Component("EmailDao")
public class EmailDaoImpl extends DaoGenericoImpl<Email> implements EmailDao{

}
