package k25.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import k25.bookstore.domain.Book;
import k25.bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	private BookRepository repository;
	
	public BookController(BookRepository repository) {
		this.repository = repository;
	}
	
	//@GetMapping("/index")
	//public String home() {
	//	return "index";
	//}
	
	@GetMapping({"/", "/booklist"})
	public String showBooklist(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId) {
		repository.deleteById(bookId);
		return "redirect:/booklist";
	}
	
	@GetMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		return "editbook";
	}
	
	@GetMapping("/addbook")
	public String addFriend(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	@PostMapping("/savebook")
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:/booklist";
	}

}
