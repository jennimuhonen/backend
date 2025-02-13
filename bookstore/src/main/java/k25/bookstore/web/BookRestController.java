package k25.bookstore.web;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import k25.bookstore.domain.Book;
import k25.bookstore.domain.BookRepository;
import k25.bookstore.domain.CategoryRepository;

@RestController
public class BookRestController {
	
	private BookRepository bookRepository;
	private CategoryRepository categoryRepository;
	
	public BookRestController(BookRepository bookRepository, CategoryRepository categoryRepository) {
		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping("/books")
	public Iterable<Book> getBooks() {
		System.out.println("Haetaan kirjat.");
		return bookRepository.findAll();
	}
	
	@GetMapping("/books/{id}")
	Optional<Book> getBookWithId(@PathVariable Long id) {
		System.out.println("Haetaan kirja id:llä " + id);
		return bookRepository.findById(id);
	}

}
