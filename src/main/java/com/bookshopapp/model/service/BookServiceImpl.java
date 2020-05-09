package com.bookshopapp.model.service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookshopapp.exceptionhandler.BookNotFoundException;
import com.bookshopapp.model.entities.Book;
import com.bookshopapp.model.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepo;
    @Override
	public List<Book> getAllBooks() {
    	return bookRepo.findAll();
    }
    @Override
	public Optional<Book> findBookById(Long id) {
    	return bookRepo.findBookById(id);
	}
    @Override
	public Optional<Book> findBybookName(String bookName) {
    	return bookRepo.findBybookName(bookName);
	}
    @Override
	public void deleteBook(Long id) {
    	bookRepo.deleteById(id);
    }
    @Override
	public Book addBook(Book book) {
    	return bookRepo.save(book);
    }
    @Override
	public Book updateBook(Long id, Book book) {
        Book bookToBeUpdated = bookRepo.findById(id).orElseThrow(BookNotFoundException::new);
        bookToBeUpdated.setBookPrice(book.getBookPrice());
		bookToBeUpdated.setPublishingYear(book.getPublishingYear());
		bookToBeUpdated.setBookName(book.getBookName());
		bookToBeUpdated.setPublisherName(book.getPublisherName());
		return bookRepo.save(bookToBeUpdated);
	}
}