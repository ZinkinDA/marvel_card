package ru.zinkin.app.marvel_superheroes_card.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Character;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "comics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comics {

    @Id
    @Column(name = "id",length = 30,nullable = false)
    @NotNull
    private String id;
    @Column(name = "name",nullable = false,length = 50,updatable = false)
    @NotNull
    private String name;
    @Column(name = "description",nullable = false)
    @NotNull
    private String description;
    @Column(name = "published",nullable = false,updatable = false)
    @NotNull
    private LocalDate published;
    @Column(name = "img")
    private String images;

    @Column(name = "writers")
    @ElementCollection
    @NotNull
    private List<String> writers;
    @Column(name = "pencilers")
    @ElementCollection
    @NotNull
    private List<String> pencilers;
    @Column(name = "cover_artist")
    @ElementCollection
    @NotNull
    private List<String> cover_artist;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comics_characters",
            joinColumns = @JoinColumn(name = "comics_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    @JsonIgnore
    private List<Character> characters;

    public Comics(String id,
                  String name,
                  String description,
                  LocalDate published,
                  List<String> writers,
                  List<String> pencilers,
                  List<String> cover_artist) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.published = published;
        this.writers = writers;
        this.pencilers = pencilers;
        this.cover_artist = cover_artist;
    }
}
