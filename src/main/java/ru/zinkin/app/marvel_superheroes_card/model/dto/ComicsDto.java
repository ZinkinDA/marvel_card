package ru.zinkin.app.marvel_superheroes_card.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComicsDto {

    private String id;
    private String name;
    private String description;
    private LocalDate published;
    private List<String> writers;
    private List<String> pencilers;
    private List<String> cover_artist;
}
