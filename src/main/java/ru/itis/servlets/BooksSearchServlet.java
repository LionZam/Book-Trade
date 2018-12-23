package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.GoodReads.GoodReadsRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;

// Jackson mapper for xml
// resttemplate
@WebServlet("/books/search")
public class BooksSearchServlet extends HttpServlet {

    GoodReadsRequest goodReadsRequest;
    private ObjectMapper objectMapper = new ObjectMapper();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        goodReadsRequest = new GoodReadsRequest();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        HashMap<String,String> parameters = new HashMap<>();
        parameters.put("q",request.getParameter("q"));
        String json = objectMapper.writeValueAsString(goodReadsRequest.searchBooks(parameters));
        response.getWriter().write(json);
    }

}