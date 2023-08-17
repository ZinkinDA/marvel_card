package ru.zinkin.app.marvel_superheroes_card.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "comics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class Comics {

    @Id
    @Column(name = "id",nullable = false,updatable = false,length = 200)
    @NotNull(message = "ID не может быть равен null")
    private String id;
    @Column(name = "name",nullable = false,updatable = false,length = 200)
    @NotNull(message = "name не может быть равен null")
    private String name;
    @Column(name = "description",nullable = false,length = 2048)
    @NotNull(message = "description не может быть равен null")
    private String description;
    @Column(name = "published",nullable = false,updatable = false)
    @NotNull(message = "published не может быть равен null")
    private LocalDate published;
    @Column(name = "img")
    @NotNull
    private String images;

    @Column(name = "writers")
    @ElementCollection
    private List<String> writers;
    @Column(name = "pencilers")
    @ElementCollection
    private List<String> pencilers;
    @Column(name = "cover_artist")
    @ElementCollection
    private List<String> cover_artist;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comics_characters",
            joinColumns = @JoinColumn(name = "comics_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    @JsonIgnore
    private List<Characters> characters;

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
