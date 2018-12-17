package ru.itis.repositories;

import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Book;

import javax.sql.DataSource;
import java.util.List;


public class BooksRepository implements CrudRepository{

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_USER_BOOKS_BY_ID =
            "select * from user_books where user_id = ?";

    @SneakyThrows
    public BooksRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Book> bookMapper = (resultSet, i) -> Book.builder()
            .id(resultSet.getLong("book_id"))
            .count(resultSet.getInt("count"))
            .build();

    public List<Book> findUserBooks(Long userId){
        try {
            return jdbcTemplate.query(SQL_SELECT_USER_BOOKS_BY_ID, bookMapper, userId);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Object find(Long id) {
        return null;
    }

    @Override
    public void save(Object model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Object model) {

    }
}
