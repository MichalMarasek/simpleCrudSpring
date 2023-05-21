package com.simplecrud.simpleCrudSpring.Controller;

import com.simplecrud.simpleCrudSpring.Model.Book;
import com.simplecrud.simpleCrudSpring.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("")
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") int id) {
        return bookRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Book> books) {
        return bookRepository.save(books);
    }

    //by using PUT method you are replacing resource content,
    // so if you will update name but provide no value for rating, rating will be reset to 0
    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.getById(id);

        if (book != null) {
            book.setName(updatedBook.getName());
            book.setRating(updatedBook.getRating());

            bookRepository.update(book);

            return 1;
        } else {
            return -1;
        }
    }

    //PATCH method allows you to update single field of the resource
    // without resetting the value of other field eg. change name without resetting rating to 0
    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.getById(id);

        if (book != null) {
            if(updatedBook.getName() != null) book.setName(updatedBook.getName());
            if(updatedBook.getRating() > 0) book.setRating(updatedBook.getRating());

            bookRepository.update(book);

            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return bookRepository.delete(id);
    }
}
