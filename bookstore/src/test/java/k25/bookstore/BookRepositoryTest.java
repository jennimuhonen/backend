package k25.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import k25.bookstore.domain.Book;
import k25.bookstore.domain.BookRepository;
import k25.bookstore.domain.Category;
import k25.bookstore.domain.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//laskee book-taulun rivit ja tsekkaa, että määrä on suurempi kuin 0
	@Test
	public void findBooksShouldReturnAtLeastOneBook() throws Exception {
		assertThat(bookRepository.count()).isGreaterThan(0);
	}
	
	//testataan tallennusta
	@Test
	public void saveBook() throws Exception {
		Category category = new Category("kiina");
		categoryRepository.save(category);
		Book book = new Book("Hiljaiset vuoret, kirkas kuu", "toim. ja suom. Tero Tähtinen", category);
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	//tsekataan, että löytyy oikea tieto
	@Test
	public void getCorrectData() {
		Optional<Book> book = bookRepository.findById((long)1);
		assertThat(book).isPresent();
		assertThat(book.get().getAuthor()).isEqualTo("Patrick Rothfuss");
		assertThat(book.get().getTitle()).isEqualTo("The Name of the Wind");
	}
	
	//testataan toimiiko delete
	@Test
	public void deleteBook() {
		List<Book> books = bookRepository.findByCategoryCategoryName("englanti");
		assertThat(books).hasSize(2);
		Book book = books.get(0);
		bookRepository.delete(book);
		List<Book> newBooks = bookRepository.findByCategoryCategoryName("englanti");
		assertThat(newBooks).hasSize(1);
	}

}
