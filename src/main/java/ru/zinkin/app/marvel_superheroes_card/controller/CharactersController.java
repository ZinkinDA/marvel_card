package ru.zinkin.app.marvel_superheroes_card.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Character;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;
import ru.zinkin.app.marvel_superheroes_card.service.CharacterService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/public/characters")
@RequiredArgsConstructor
@Api(value = "Работа с персонажами", tags = {"Characters"})
public class CharactersController {

    private final CharacterService characterService;

    @ApiOperation(value = "Получение персонажей с пагинацией и сортировкой по имени")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Список персонажей"),
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

    @ApiOperation(value = "Получение персонажа по Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Персонаж по id"),
            @ApiResponse(code = 404,message = "Персонаж не найден!")
    })
    @GetMapping("/{characterId}")
    public ResponseEntity<?> getCharacterById(@PathVariable("characterId") String id){
        Optional<Character> characters = characterService.findById(id);
        if(characters.isPresent()){
            return ResponseEntity.ok(characters.get());
        }
        return ResponseEntity.status(404).body("Персонаж не найден!");
    }

    @ApiOperation(value = "Получение комиксов персонажа по id с сортировкой по имени и пагинацией")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Список комиксов"),
            @ApiResponse(code = 404,message = "Персонаж не найден!")
    })
    @GetMapping("/{characterId}/comics")
    public ResponseEntity<?> getComicsByCharacterId(@PathVariable("characterId") String id,
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
