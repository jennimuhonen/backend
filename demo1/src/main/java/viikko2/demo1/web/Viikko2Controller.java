package viikko2.demo1.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import viikko2.demo1.domain.Friend;
import viikko2.demo1.domain.Student;

@Controller
public class Viikko2Controller {
	
	//2. Handling Lists
	
	public static List<Student> students = new ArrayList<>();
	
	static {
		students.add(new Student("Aku", "Ankka"));
		students.add(new Student("Iines", "Ankka"));
		students.add(new Student("Roope", "Ankka"));		
	}
	
	@GetMapping("/student")
	public String showStudents(Model model) {
		model.addAttribute("students", students);
		return "students";
	}
	
	//3+4. Friends
	
	private static List<Friend> friendList = new ArrayList<>();
	
	@GetMapping("/addfriend")
	public String addFriend(Model model) {
		model.addAttribute("friend", new Friend());
		return "newFriend";
	}
	
	@PostMapping("/savefriend")
	public String saveFriend(Friend friend) {
		System.out.println("Lisätään ArrayListiin: " + friend);
		friendList.add(friend);
		return "redirect:/friendlist";
	}
	
	@GetMapping("/friendlist")
	public String showFriends(Model model) {
		System.out.println("Kaikki ystävät :)");
		model.addAttribute("friends", friendList);
		return "friendList";
	}
	
	//Hello Thymeleaf
	
	@GetMapping("/hello")
	public String checkAge(@RequestParam String name, @RequestParam int age, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "hello";
	}
}
