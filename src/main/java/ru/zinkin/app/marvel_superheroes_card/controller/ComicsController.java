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
import ru.zinkin.app.marvel_superheroes_card.service.ComicsService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/public/comics")
@RequiredArgsConstructor
@Api(value = "Работа с комиксами",tags = {"comics"})
public class ComicsController {

    private final ComicsService comicsService;

    @ApiOperation(value = "Получение комиксов с пагинацией и сортировкой по имени")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Список комиксов"),
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
        return ResponseEntity.ok(comicsService.getComicsAll(claims));
    }

    @ApiOperation(value = "Получение комикса по его Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Комикс по id"),
            @ApiResponse(code = 413,message = "Комиксы не найдены!")
    })
    @GetMapping("/{comicsId}")
    public ResponseEntity<?> getComicsById(@PathVariable("comicsId") String id){
        Optional<Comics> comics = comicsService.getComicsById(id);
        if(!comicsService.existById(id)){
            return ResponseEntity.status(413).body("Комикс не найдены!");
        }
        return ResponseEntity.ok(comics.get());
    }


    /*
        Добавить пагинацию и сортировку по имени комикса.
        Переделать все через map(String,Object)
     */
    @ApiOperation(value = "Получение персонажей по Id комикса с сортировкой по имени")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Список персонажей комикса"),
            @ApiResponse(code = 413,message = "Комикс не найдены!")
    })
    @GetMapping("/{comicsId}/characters")
    public ResponseEntity<?> getCharactersByComicsId(@PathVariable("comicsId") String comicsId,
                                                     @RequestParam(value = "currentPage",required = false) Integer cp,
                                                     @RequestParam(value = "elementToPage",required = false) Integer etp){
        if(!comicsService.existById(comicsId)){
            return ResponseEntity.status(413).body("Комикс не найден");
        }

        Map<String,Object> claims = new HashMap<>();
        if(cp != null) {
            claims.put("currentPage",cp);
        }
        if(etp != null){
            claims.put("elementToPage",etp);
        }

        Page<Character> page = comicsService.getCharacterByComicsId(comicsId,claims);
        return ResponseEntity.ok(page);
    }

}
