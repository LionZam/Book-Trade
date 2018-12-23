package ru.itis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private int numPages;
    private String description;
    private int count;
    private Long id;
    private String imageUrl;
    private String title;
    @JsonProperty("book")
    private void mapper(Map<String, Object> book){
        numPages = Integer.parseInt((String)book.get("num_pages"));
        description = (String) book.get("description");
        id = ((Integer)book.get("id")).longValue();
        imageUrl = (String) book.get("image_url");
        title = (String) book.get("title");
    }
}