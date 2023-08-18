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
import ru.zinkin.app.marvel_superheroes_card.service.ComicsService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/public/comics")
@RequiredArgsConstructor
@Tag(name = "Comics controller",description = "Получение информации о комиксах. (GET)")
public class ComicsController {

    private final ComicsService comicsService;
    @Operation(summary = "Получение комиксов.",description = "Получение комиксов с пагинацией и сортировкой по имени")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Список комиксов"),
    })
    @GetMapping
    public ResponseEntity<?> getComicsAll(@RequestParam(value = "currentPage",required = false) Integer page,
                                          @RequestParam(value = "elementToPage",required = false) Integer el){
        Map<String, Object> claims = new HashMap<>();
        if(page != null){
            claims.put("currentPage", page);
        }
        if(el != null) {
            claims.put("elementToPage", el);
        }
        Page<Comics> comics = comicsService.getComicsAll(claims);

        return ResponseEntity.ok(comics);
    }

    @Operation(summary = "Получение комикса по его Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Комикс по id"),
            @ApiResponse(responseCode = "404",description = "Комикс не найден!")
    })
    @GetMapping("/{comicsId}")
    public ResponseEntity<?> getComicsById(@PathVariable("comicsId") @NotNull @NotEmpty String id){
        if(!comicsService.existById(id)){
            return ResponseEntity.status(404).body("Комикс не найдены!");
        }
        Optional<Comics> comics = comicsService.getComicsById(id);
        return ResponseEntity.ok(comics.get());
    }

    @Operation(summary = "Получение персонажей.",description = "Получение персонажей по комиксу с сортировкой по имени")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Список персонажей комикса"),
            @ApiResponse(responseCode = "404",description = "Комикс не найдены!")
    })
    @GetMapping("/{comicsId}/characters")
    public ResponseEntity<?> getCharactersByComicsId(@PathVariable("comicsId") @NotNull @NotEmpty String comicsId,
                                                     @RequestParam(value = "currentPage",required = false) Integer cp,
                                                     @RequestParam(value = "elementToPage",required = false) Integer etp){
        if(!comicsService.existById(comicsId)){
            return ResponseEntity.status(404).body("Комикс не найден");
        }
        Map<String, Object> claims = new HashMap<>();
        if (cp != null) {
            claims.put("currentPage", cp);
        }
        if (etp != null) {
            claims.put("elementToPage", etp);
        }

        Page<Characters> page = comicsService.getCharacterByComicsId(comicsId, claims);
        return ResponseEntity.ok(page);
    }

}
