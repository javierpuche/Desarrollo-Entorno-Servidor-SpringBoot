package des.springboot_eagerlazy.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import des.springboot_eagerlazy.entidades.Email;

@Repository
public interface EmailRepository extends PagingAndSortingRepository<Email, Long> {

}
