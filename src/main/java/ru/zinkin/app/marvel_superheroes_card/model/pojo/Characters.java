package ru.zinkin.app.marvel_superheroes_card.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Table(name = "characters")
@Builder
public class Characters {
    @Id
    @NotNull
    @Column(name = "id",updatable = false,length = 200)
    private String id;
    @NotNull
    private Byte DURABILITY;
    @NotNull
    private Byte ENERGY;
    @NotNull
    private Byte FIGHTING_SKILLS;
    @NotNull
    private Byte INTELLIGENCE;
    @NotNull
    private Byte SPEED;
    @NotNull
    private Byte STRENGTH;

    @Column(name = "name",updatable = false,length = 200)
    @NotNull
    private String name;
    @Column(name = "height")
    private Integer height;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "eyes")
    @ElementCollection
    private List<String> eyes;
    @Column(name = "hair")
    @ElementCollection
    private List<String> hair;
    @Column(name = "universe")
    private String universe;
    @Column(name = "other_aliases")
    @ElementCollection
    private List<String> other_aliases;
    @Column(name = "education")
    private String education;
    @Column(name = "place_of_origin")
    private String place_of_origin;
    @Column(name = "known_relatives")
    @ElementCollection
    private List<String> known_relatives;
    @Column(name = "images")
    private String img;

    @ManyToMany(mappedBy = "characters")
    @JsonIgnore
    private List<Comics> comics;

    public Characters(String id,
                      Byte DURABILITY,
                      Byte ENERGY,
                      Byte FIGHTING_SKILLS,
                      Byte INTELLIGENCE,
                      Byte SPEED,
                      Byte STRENGTH,
                      String name,
                      String img) {
        this.id = id;
        this.DURABILITY = DURABILITY;
        this.ENERGY = ENERGY;
        this.FIGHTING_SKILLS = FIGHTING_SKILLS;
        this.INTELLIGENCE = INTELLIGENCE;
        this.SPEED = SPEED;
        this.STRENGTH = STRENGTH;
        this.name = name;
        this.img = img;
    }

    public Characters(String id,
                      Byte DURABILITY,
                      Byte ENERGY,
                      Byte FIGHTING_SKILLS,
                      Byte INTELLIGENCE,
                      Byte SPEED,
                      Byte STRENGTH,
                      String name,
                      Integer height,
                      Integer weight,
                      List<String> eyes,
                      List<String> hair,
                      String universe,
                      List<String> other_aliases,
                      String education,
                      String place_of_origin,
                      List<String> known_relatives,
                      String img) {
        this.id = id;
        this.DURABILITY = DURABILITY;
        this.ENERGY = ENERGY;
        this.FIGHTING_SKILLS = FIGHTING_SKILLS;
        this.INTELLIGENCE = INTELLIGENCE;
        this.SPEED = SPEED;
        this.STRENGTH = STRENGTH;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.eyes = eyes;
        this.hair = hair;
        this.universe = universe;
        this.other_aliases = other_aliases;
        this.education = education;
        this.place_of_origin = place_of_origin;
        this.known_relatives = known_relatives;
        this.img = img;
    }
}
