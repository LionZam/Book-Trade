package ru.itis.repositories;

import ru.itis.models.Auth;

/**
 * 25.10.2018
 * AuthRepository
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface AuthRepository extends CrudRepository<Auth> {
    Auth findByCookieValue(String cookieValue);
}
