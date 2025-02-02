package k25.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import k25.bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	private BookRepository repository;
	
	public BookController(BookRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/index")
	public String home() {
		return "index";
	}
	
	@GetMapping("/booklist")
	public String showBooklist(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

}
