package ru.zinkin.app.marvel_superheroes_card.model.dto.request;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "ID не может быть равен null")
    private String id;
    @NotNull(message = "name не может быть равен null")
    private String name;
    @NotNull(message = "description не может быть равен null")
    private String description;
    @NotNull(message = "published не может быть равен null")
    private LocalDate published;
    private List<String> writers;
    private List<String> pencilers;
    private List<String> cover_artist;
}
