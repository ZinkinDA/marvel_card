package ru.zinkin.app.marvel_superheroes_card.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Characters;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;
import ru.zinkin.app.marvel_superheroes_card.service.abstracts.AbstractCharacterService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/public/characters")
@RequiredArgsConstructor
@Tag(name = "Character controller",description = "Получение информации о персонажах. (GET)")
public class CharactersController {
    private final AbstractCharacterService characterService;

    @Operation(summary = "Получение персонажей",description = "Получение персонажей с пагинацией и сортировкой по имени")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Список персонажей"),
    })
    @GetMapping
    public ResponseEntity<?> getCharacters(@RequestParam(value = "currentPage",required = false) Integer currentPage,
                                           @RequestParam(value = "elementToPage",required = false) Integer elementToPage){
        Map<String,Object> claims = new HashMap<>();
        if(currentPage!=null){
            claims.put("currentPage",currentPage);
        }
        if(elementToPage!=null){
            claims.put("elementToPage",elementToPage);
        }
        return ResponseEntity.ok().body(characterService.getAll(claims));
    }

    @Operation(summary = "Получение персонажа по Id")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Персонаж по id"),
            @ApiResponse(responseCode = "404",description = "Персонаж не найден!")
    })
    @GetMapping("/{characterId}")
    public ResponseEntity<?> getCharacterById(@PathVariable("characterId") @NotNull @NotEmpty
                                                  String id){
        Optional<Characters> characters = characterService.findById(id);
        if(characters.isPresent()){
            return ResponseEntity.ok(characters.get());
        }
        return ResponseEntity.status(404).body("Персонаж не найден!");
    }

    @Operation(summary = "Получение комиксов персонажа по id",description = "Получение комиксов персонажа с сортировкой по имени и пагинацией")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Список комиксов"),
            @ApiResponse(responseCode = "404",description = "Персонаж не найден!")
    })
    @GetMapping("/{characterId}/comics")
    public ResponseEntity<?> getComicsByCharacterId(@PathVariable("characterId") @NotNull @NotEmpty String id,
                                                    @RequestParam(value = "currentPage",required = false) Integer cp,
                                                    @RequestParam(value = "elementToPage",required = false) Integer etp){
        if(!characterService.existById(id)){
            return ResponseEntity.status(404).body("Персонаж не найден!");
        }
        Map<String,Object> claims = new HashMap<>();
        if(cp != null) {
            claims.put("currentPage",cp);
        }
        if(etp != null) {
            claims.put("elementToPage",etp);
        }
        Page<Comics> comics = characterService.findComicsByCharacterId(id,claims);
        return ResponseEntity.ok(comics);
    }

}
