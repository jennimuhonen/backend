package k25.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k25.bookstore.domain.AppUser;
import k25.bookstore.domain.AppUserRepository;
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
	public CommandLineRunner bookData(BookRepository bookRepository, CategoryRepository categoryRepository, AppUserRepository userRepository) {
		return (args) -> {
			
			if (categoryRepository.count()==0) {
				System.out.println("Lisätään tietokantaan kategorioita");
				categoryRepository.save(new Category("suomi"));
				categoryRepository.save(new Category("englanti"));
			}
			
			System.out.println("-- Printtaa kategoriat --");
			for (Category category : categoryRepository.findAll()) {
				System.out.println(category.toString());
			}
			
			if (bookRepository.count()==0) {
				System.out.println("Lisätään tietokantaan kirjoja");
				bookRepository.save(new Book("The Name of the Wind", "Patrick Rothfuss", "9780575081406", 2007, 10, categoryRepository.findByCategoryName("englanti").get(0)));
				bookRepository.save(new Book("Taru sormusten herrasta", "J. R. R. Tolkien", "951013208X", 1998, 20, categoryRepository.findByCategoryName("suomi").get(0)));
				bookRepository.save(new Book("Lumikko ja yhdeksän muuta", "Pasi Ilmari Jääskeläinen", "9789517965095", 2010, 10, categoryRepository.findByCategoryName("suomi").get(0)));
				bookRepository.save(new Book("Storm Front", "Jim Butcher", "9781841493985", 2009, 10, categoryRepository.findByCategoryName("englanti").get(0)));
			}							
			
			System.out.println("-- Printtaa kirjat --");
			for (Book book : bookRepository.findAll()) {
				System.out.println(book.toString());
			}
			
			if (userRepository.count()==0) {
				System.out.println("Luodaan käyttäjiä");
				AppUser user1 = new AppUser("user", "$2a$10$wCqhw9Pgc6hjxcYPj3D2Pu/q6lZTjeMSlPmJOe4rPVx6fBYvnEFVG", "USER");
				AppUser user2 = new AppUser("admin", "$2a$10$vQlQeHhi/PCMZK0hTaTJ3.NtyHk2PluBO..Vh7rx2JDHVCLlR.K5y", "ADMIN");
				userRepository.save(user1);
				userRepository.save(user2);
			}
			
		};
		
	}

}
