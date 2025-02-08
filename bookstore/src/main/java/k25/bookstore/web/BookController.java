package k25.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
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
		System.out.println("Kirjalista haettu");
		return "booklist";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId) {
		repository.deleteById(bookId);
		System.out.println("Kirja poistettu id:llä " + bookId);
		return "redirect:/booklist";
	}
	
	@GetMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("editBook", repository.findById(bookId));
		System.out.println("Avattu muokkaussivu kirjalle id:llä " + bookId);
		return "editbook";
	}
	
	@GetMapping("/addbook")
	public String addFriend(Model model) {
		model.addAttribute("book", new Book());
		System.out.println("Avattu sivu, jolla voidaan lisätä uusi kirja.");
		return "addbook";
	}
	
	@PostMapping("/savebook")
	public String saveBook(Book book) {
		repository.save(book);
		System.out.println("Uusi kirja tallennettu: " + book);
		return "redirect:/booklist";
	}
	
	@PostMapping("/saveeditedbook")
	public String saveEditedBook(@Valid @ModelAttribute("editBook") Book book, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("editBook", book);
			System.out.println("Kirjan muokkaus epäonnistui: " + book);
			return "editbook";
		}
		repository.save(book);
		System.out.println("Muokattu kirja tallennettu: " + book);
		return "redirect:/booklist";
	}

}
