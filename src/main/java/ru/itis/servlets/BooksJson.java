package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.models.Basket;
import ru.itis.models.Book;
import ru.itis.repositories.*;
import ru.itis.services.UsersService;
import ru.itis.services.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/books.json")
public class BooksJson extends HttpServlet {
    private UsersService usersService;
    private UsersRepository usersRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("adminroot");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/book_trade");
        usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        AuthRepository authRepository = new AuthRepositoryImpl(dataSource);
        usersService = new UsersServiceImpl(usersRepository, authRepository);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        Cookie authCookie = authCookie(request.getCookies());
        if (authCookie != null) {
            if (usersService.isExistByCookie(authCookie.getValue())) {
                Basket basket = usersRepository.findByCookie(authCookie.getValue()).getBasket();
                boolean flag = true;
                StringBuilder resultBuilder = new StringBuilder("[");
                for (Book book : basket.getBooks()) {
                    if (flag) {
                        flag = false;
                    } else {
                        resultBuilder.append(",");
                    }
                    resultBuilder.append(getBookFromSite(book));
                }
                resultBuilder.append("]");
                //String json = objectMapper.writeValueAsString(result);
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().write(resultBuilder.toString());
            }
        }
    }

    private Cookie authCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    return cookie;
                }
            }
        }
        return null;
    }

    private String getBookFromSite(Book book) throws IOException {
        URL yahoo = new URL("https://www.goodreads.com/book/show/" + book.getId() + ".xml?key=VWFKgYW1DOphdMHFBQQt4Q");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String line = in.readLine();
        String inputLine = "";
        while (line != null) {
            inputLine += line;
            line = in.readLine();
        }
        in.close();
        return XMLToJSON(inputLine, book.getCount());
    }

    private String XMLToJSON(String string, int count){
        String jsonPrettyPrintString = "";
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(string).getJSONObject("GoodreadsResponse").getJSONObject("book");
            xmlJSONObj.put("count", count);
            jsonPrettyPrintString = xmlJSONObj.toString(4);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
        return jsonPrettyPrintString;
    }
}
