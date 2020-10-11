package des.springboot_jdbc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import des.springboot_jdbc.model.Book;

public interface BookDao {

	int count();

	int save(Book book);

	int update(Book book);

	int deleteById(Long id);

	List<Book> findAll();

	List<Book> findByNameAndPrice(String name, BigDecimal price);

	Optional<Book> findById(Long id);

	String getNameById(Long id);

}
