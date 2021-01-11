package des.springboot_ajax.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import des.springboot_ajax.entidades.Email;

@Repository
public interface EmailRepository extends PagingAndSortingRepository<Email, Long> {

}
