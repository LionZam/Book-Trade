package ru.itis.repositories;

import ru.itis.models.Book;
import ru.itis.models.User;

/**
 * 22.10.2018
 * UsersRepository
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersRepository extends CrudRepository<User> {
    User findByName(String name);

    User findByCookie(String cookie);

    boolean addBook(User user, Long id);

}
