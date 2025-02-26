package k25.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import k25.bookstore.domain.Book;
import k25.bookstore.domain.BookRepository;
import k25.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//public BookController(BookRepository bookRepository) {
	//	this.bookRepository = bookRepository;
	//}
	
	//public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
	//	super();
	//	this.bookRepository = bookRepository;
	//	this.categoryRepository = categoryRepository;
	//}

	//@GetMapping("/index")
	//public String home() {
	//	return "index";
	//}
	
	
	
	@GetMapping({"/", "/booklist"})
	public String showBooklist(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		model.addAttribute("categories", categoryRepository.findAll());
		System.out.println("Kirjalista haettu");
		return "booklist";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		System.out.println("Kirja poistettu id:llä " + bookId);
		return "redirect:/booklist";
	}
	
	@GetMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("editBook", bookRepository.findById(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
		System.out.println("Avattu muokkaussivu kirjalle id:llä " + bookId);
		return "editbook";
	}
	
	@GetMapping("/addbook")
	public String addFriend(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		System.out.println("Avattu sivu, jolla voidaan lisätä uusi kirja.");
		return "addbook";
	}
	
	//Ilman validointia
	/*@PostMapping("/savebook")
	public String saveBook(Book book) {
		bookRepository.save(book);
		System.out.println("Uusi kirja tallennettu: " + book);
		return "redirect:/booklist";
	}*/
	
	@PostMapping("/savebook")
	public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("addBook", book);
			model.addAttribute("categories", categoryRepository.findAll());
			System.out.println("Kirjan muokkaus epäonnistui: " + book);
			return "addbook";
		}
		bookRepository.save(book);
		System.out.println("Muokattu kirja tallennettu: " + book);
		return "redirect:/booklist";
	}
	
	@PostMapping("/saveeditedbook")
	public String saveEditedBook(@Valid @ModelAttribute("editBook") Book book, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("editBook", book);
			model.addAttribute("categories", categoryRepository.findAll());
			System.out.println("Kirjan muokkaus epäonnistui: " + book);
			return "editbook";
		}
		bookRepository.save(book);
		System.out.println("Muokattu kirja tallennettu: " + book);
		return "redirect:/booklist";
	}
	
	//login
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}

}
