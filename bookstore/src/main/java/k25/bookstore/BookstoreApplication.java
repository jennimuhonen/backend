package k25.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k25.bookstore.domain.Book;
import k25.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookData(BookRepository bookRepository) {
		return (args) -> {
			bookRepository.save(new Book("The Name of the Wind", "Patrick Rothfuss", "9780575081406", 2007, 10));
			bookRepository.save(new Book("Taru sormusten herrasta", "J. R. R. Tolkien", "951013208X", 1998, 20));
			bookRepository.save(new Book("Lumikko ja yhdeksän muuta", "Pasi Ilmari Jääskeläinen", "9789517965095", 2010, 10));
			bookRepository.save(new Book("Storm Front", "Jim Butcher", "9781841493985", 2009, 10));
		};
		
	}

}
