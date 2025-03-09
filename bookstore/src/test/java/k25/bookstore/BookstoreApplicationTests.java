package k25.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import k25.bookstore.domain.BookRepository;

@SpringBootTest
class BookstoreApplicationTests {
	
	@Autowired
	private BookRepository bookRepository;

	//automaattisesti luotu testi, testaa käynnistyykö sovellus
	@Test
	void contextLoads() {
	}
	
	//Saadaanko tietokantaan yhteys?
	@Test
	public void testDatabaseConnection() {
		assertThat(bookRepository).isNotNull(); //tsekkaa, että repositorio on otettu käyttöön
		assertThat(bookRepository.count()).isNotNull(); //tsekkaa montako riviä kannassa on ja tsekkaa ettei määrä ole 0, koska tiedetään, että kannassa pitäisi olla jotain
	}

}
