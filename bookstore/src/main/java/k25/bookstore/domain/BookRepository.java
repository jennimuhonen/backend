package k25.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findByCategoryCategoryName(String categoryName);
	
}
