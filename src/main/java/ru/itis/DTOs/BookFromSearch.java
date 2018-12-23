package ru.itis.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookFromSearch {
    private String imageUrl;
    @JsonIgnore
    private Long id;
    private String title;
    private String authorName;

    @JsonProperty("best_book")
    private void mapper(Map<String, Object> bestBook) {
        id = (((Map<String, Integer>)bestBook.get("id")).get("content")).longValue();
        imageUrl = (String)bestBook.get("image_url");
        authorName = ((Map<String, String>)bestBook.get("author")).get("name");
        title = (String)bestBook.get("title");
    }
}
