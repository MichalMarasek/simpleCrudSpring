package com.simplecrud.simpleCrudSpring.Repository;

import com.simplecrud.simpleCrudSpring.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Book> getAll(){
        return jdbcTemplate.query("SELECT id, name, rating FROM book",
                BeanPropertyRowMapper.newInstance(Book.class));
    }

    public Book getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, name, rating FROM book WHERE " +
                "id = ?", BeanPropertyRowMapper.newInstance(Book.class), id);
    }

    public int save(List<Book> books) {
        books.forEach(book -> jdbcTemplate
                .update("INSERT INTO book(name, rating) VALUES(?, ?)",
                        book.getName(), book.getRating()
                ));

        return 1;
    }

    public int update(Book book) {
        return jdbcTemplate.update("UPDATE book SET name=?, rating=? WHERE id=?",
                book.getName(), book.getRating(), book.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
}
