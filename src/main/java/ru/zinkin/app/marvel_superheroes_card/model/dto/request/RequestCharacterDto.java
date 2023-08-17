package ru.zinkin.app.marvel_superheroes_card.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCharacterDto {

    @NotNull
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
    @NotNull
    private String name;
    private Integer height;
    private Integer weight;
    private List<String> eyes;
    private List<String> hair;
    private String universe;
    private List<String> other_aliases;
    private String education;
    private String place_of_origin;
    private List<String> known_relatives;

}
