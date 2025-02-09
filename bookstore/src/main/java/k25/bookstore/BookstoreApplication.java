package k25.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k25.bookstore.domain.Book;
import k25.bookstore.domain.BookRepository;
import k25.bookstore.domain.Category;
import k25.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookData(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			
			System.out.println("Lisätään tietokantaan kategorioita");
			categoryRepository.save(new Category("suomi"));
			categoryRepository.save(new Category("englanti"));
			
			System.out.println("-- Printtaa kategoriat --");
			for (Category category : categoryRepository.findAll()) {
				System.out.println(category.toString());
			}
						
			System.out.println("Lisätään tietokantaan kirjoja");
			bookRepository.save(new Book("The Name of the Wind", "Patrick Rothfuss", "9780575081406", 2007, 10, categoryRepository.findByCategoryName("englanti").get(0)));
			bookRepository.save(new Book("Taru sormusten herrasta", "J. R. R. Tolkien", "951013208X", 1998, 20, categoryRepository.findByCategoryName("suomi").get(0)));
			bookRepository.save(new Book("Lumikko ja yhdeksän muuta", "Pasi Ilmari Jääskeläinen", "9789517965095", 2010, 10, categoryRepository.findByCategoryName("suomi").get(0)));
			bookRepository.save(new Book("Storm Front", "Jim Butcher", "9781841493985", 2009, 10, categoryRepository.findByCategoryName("englanti").get(0)));
			
			System.out.println("-- Printtaa kirjat --");
			for (Book book : bookRepository.findAll()) {
				System.out.println(book.toString());
			}
			
		};
		
	}

}
