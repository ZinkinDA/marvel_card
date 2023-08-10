package ru.zinkin.app.marvel_superheroes_card.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestComicsDto {

    @NonNull
    private String id;
    @NonNull
    private String name;
    private String description;
    @NonNull
    private LocalDate published;
    private List<String> writers;
    private List<String> pencilers;
    private List<String> cover_artist;
}
