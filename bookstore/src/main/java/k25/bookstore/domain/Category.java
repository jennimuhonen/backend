package k25.bookstore.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	
	private String categoryName;
	
	//Books in categories
	@OneToMany
	private List<Book> books;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public Category(Long categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	//With book-list
	public Category(Long categoryId, String categoryName, List<Book> books) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.books = books;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

}
