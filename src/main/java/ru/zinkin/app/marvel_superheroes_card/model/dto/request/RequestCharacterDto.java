package ru.zinkin.app.marvel_superheroes_card.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCharacterDto {

    @NotNull(message = "ID не может быть равен null")
    @NotEmpty(message = "ID не может быть пустым")
    @NotBlank(message = "ID не может быть пустым")
    private String id;
    @NotNull(message = "DURABILITY не может быть равен null")
    @Min(value = 0,message = "DURABILITY не может быть меньше 0")
    @Max(value = 10,message = "DURABILITY не может быть больше 10")
    private Byte DURABILITY;
    @NotNull(message = "ENERGY не может быть равен null")
    @Min(value = 0,message = "ENERGY не может быть меньше null")
    @Max(value = 10,message = "ENERGY не может быть больше 10")
    private Byte ENERGY;
    @NotNull(message = "FIGHTING_SKILLS не может быть равен null")
    @Min(value = 0,message = "FIGHTING_SKILLS не может быть меньше 0")
    @Max(value = 10,message = "FIGHTING_SKILLS не может быть больше 10")
    private Byte FIGHTING_SKILLS;
    @NotNull(message = "INTELLIGENCE не может быть равен null")
    @Min(value = 0,message = "INTELLIGENCE не может быть меньше 0")
    @Max(value = 10,message = "INTELLIGENCE не может быть больше 10")
    private Byte INTELLIGENCE;
    @NotNull(message = "SPEED не может быть равен null")
    @Min(value = 0,message = "SPEED не может быть меньше 0")
    @Max(value = 10,message = "SPEED не может быть больше 10")
    private Byte SPEED;

    @NotNull(message = "STRENGTH не может быть равен null")
    @Min(value = 0,message = "STRENGTH не может быть меньше 0")
    @Max(value = 10,message = "STRENGTH не может быть больше 10")
    private Byte STRENGTH;
    @NotNull(message = "name не может быть равен null")
    @NotEmpty(message = "ID не может быть пустым")
    @NotBlank(message = "ID не может быть пустым")
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
