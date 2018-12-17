package ru.itis.repositories;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Basket;

import javax.sql.DataSource;
import java.util.Calendar;


public class BasketRepository {
    private JdbcTemplate jdbcTemplate;
    private BooksRepository booksRepository;

    //language=SQL
    private static final String SQL_SELECT_USER_BASKET =
            "select * from basket WHERE user_id = ?;";

    private static final String SQL_INSERT =
            "insert into basket(user_id, last_change) values (?, ?)";

    private RowMapper<Basket> basketRowMapper = (resultSet, i) -> Basket.builder()
            .id(resultSet.getLong("user_id"))
            .books(booksRepository.findUserBooks(resultSet.getLong("user_id")))
            .build();

    @SneakyThrows
    public BasketRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        booksRepository = new BooksRepository(dataSource);
    }

    public Basket findUserBasket(Long id){
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BASKET, basketRowMapper, id);
    }

    public void newBasket(Long id){
        java.sql.Date currentTimestamp = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        jdbcTemplate.update(SQL_INSERT, id, currentTimestamp);
    }
}
