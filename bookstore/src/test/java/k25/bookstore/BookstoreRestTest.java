package k25.bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//näitä editori ei osannut automaattisesti tarjota
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BookstoreRestTest {
	
	@Autowired
	private WebApplicationContext webAppContext;
	
	private MockMvc mockMvc;
	
	//rakentaa testausympäristön aina ennen testiä
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	//tsekkaa vastaako /books
	@Test
	public void booksStatusOk() throws Exception {
		mockMvc.perform(get("/books")).andExpect(status().isOk());
	}
	
	//tsekkaa, että sisältö on JSON-formaatissa
	@Test
	public void responseTypeApplicationJson() throws Exception {
		mockMvc.perform(get("/books"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	//automaattisen restin testaaminen
	@Test
	public void apiStatusOk() throws Exception {
		mockMvc.perform(get("/api/categories")).andExpect(status().isOk());
	}

}
