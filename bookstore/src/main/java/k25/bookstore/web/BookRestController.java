package k25.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	 // lisää uusi kirja
    @PostMapping("books")
    Book newBook(@RequestBody Book newBook) {
        return bookRepository.save(newBook);
    }
    
    // editoidaan
    @PutMapping("/books/{id}")
    Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
        editedBook.setId(id);
        return bookRepository.save(editedBook);
    }
    
    //deletoidaan kirja
    @DeleteMapping("/books/{id}")
    public Iterable<Book> deleteCar(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return bookRepository.findAll();
    }
    
    // etsi kirja tietyllä kielellä
    @GetMapping("/books/category/{categoryName}")
    List<Book> getBookByCategory(@PathVariable String categoryName) {
        return bookRepository.findByCategoryCategoryName(categoryName);
    }

}
