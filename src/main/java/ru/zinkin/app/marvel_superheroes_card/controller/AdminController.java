package ru.zinkin.app.marvel_superheroes_card.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.zinkin.app.marvel_superheroes_card.model.dto.request.RequestCharacterDto;
import ru.zinkin.app.marvel_superheroes_card.model.dto.request.RequestComicsDto;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Characters;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;
import ru.zinkin.app.marvel_superheroes_card.service.ComicsService;
import ru.zinkin.app.marvel_superheroes_card.service.abstracts.AbstractCharacterService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/private/admin")
@RequiredArgsConstructor
@Api(value = "Работа c данными для заполнения таблиц", tags = {"Admin","POST/PUT request"})
public class AdminController {

    private final Logger logger = Logger.getLogger("Admin");
    @Value("${upload.path}")
    private String path;
    private final AbstractCharacterService characterService;
    private final ComicsService comicsService;

    @ApiOperation(value = "Сохранение комикса",tags = {"Сохранение комикса"})
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Комикс создан."),
            @ApiResponse(code = 400 , message = "Не правильный ввод данных или персонаж уже существует.")
    })
    @PostMapping("/comics/save")
    public ResponseEntity<?> saveComics(@RequestBody RequestComicsDto requestComicsDto){
        logger.log(Level.INFO,"Вызов метода saveComics");
        try {
            if(!comicsService.saveComicsDto(requestComicsDto)){
                return ResponseEntity.status(400).body("Проверьте правильность ввода данных.");
            }
        } catch (RuntimeException e){
            return  ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Комикс с именем %s создан. \n http://localhost:8091/v1/public/comics/%s",requestComicsDto.getName(),requestComicsDto.getId()));
    }

    @ApiOperation(value = "Изменение комикса",tags = {"Изменение комикса"})
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Комикс изменен"),
            @ApiResponse(code = 400,message = "Проверьте правильность uri"),
            @ApiResponse(code = 400,message = "Изменяемого пользователя не существует.")
    })
    @PutMapping("/comics/edit")
    public ResponseEntity<?> editComics(@RequestBody Comics comics){
        logger.log(Level.INFO,"Вызов метода editComics");
        if(comicsService.editComics(comics)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Комикс изменен");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Проверьте правильность uri или изменяемого пользователя не существует.");
    }

    @ApiOperation(value = "Изменение фото для комикса",tags = {"Загрузка фото для комикса","Изменение фотографии комикса по ID"})
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Фото загружено и изменено"),
            @ApiResponse(code = 400,message = "Не верный тип файла"),
            @ApiResponse(code = 404,message = "Комикс не найден")
    })
    @PostMapping("/comics/upload/{comicsId}")
    public ResponseEntity<String> uploadPhotoForComics(@PathVariable("comicsId") String id, @RequestPart("img")MultipartFile multipartFile) throws IOException {
        logger.log(Level.INFO,"Вызов метода uploadPhotoForComics");
        if(!comicsService.existById(id)){
            return ResponseEntity.status(404).body("Комикс не найден!");
        }
        Comics comics = comicsService.getComicsById(id).get();

        if(multipartFile != null){
            if(!Objects.requireNonNull(multipartFile.getContentType()).contains("image")){
                return ResponseEntity.status(400).body("Не верный тип файла!");
            }
            File file = new File(path);
            if(!file.exists()){
                boolean a = file.mkdir();
            }
            String filename = String.join(".",UUID.randomUUID().toString(),multipartFile.getOriginalFilename());
            multipartFile.transferTo(new File(path + filename));
            comics.setImages(filename);
        }
        comicsService.editComics(comics);
        return ResponseEntity.ok("Фотография загружена!");
    }

    @ApiOperation(value = "Изменение фото для героя",tags = {"Сохранение комикса"})
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Фото загружено и изменено"),
            @ApiResponse(code = 400,message = "Не верный тип файла"),
            @ApiResponse(code = 404,message = "Комикс не найден")
    })
    @PostMapping("/character/upload/{characterId}")
    public ResponseEntity<String> uploadPhotoForCharacter(@PathVariable("characterId") String id,
                                                          @RequestPart("img") MultipartFile multipartFile) throws IOException {
        logger.log(Level.INFO,"Вызов метода uploadPhotoForCharacter");
        if(!characterService.existById(id)){
            return ResponseEntity.status(404).body("Персонаж не найден!");
        }

        Characters character = characterService.findById(id).get();
        if(multipartFile != null){
            if(!Objects.requireNonNull(multipartFile.getContentType()).contains("image/")){
                return ResponseEntity.status(401).body("Не верный тип файла!");
            }
            File dir = new File(path);
            if(!dir.exists()){
                boolean a = dir.mkdir();
            }
            String filename = String.join(".",UUID.randomUUID().toString(),multipartFile.getOriginalFilename());
            multipartFile.transferTo(new File(path + filename));
            character.setImg(filename);
            characterService.editCharacter(id,character);
        }
        return ResponseEntity.ok("Фотография загружена!");
    }

    @ApiOperation(value = "Сохранение героя",tags = {"Сохранение героя"})
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Герой сохранен"),
            @ApiResponse(code = 400,message = "Такой ID уже существует"),
            @ApiResponse(code = 400,message = "Поле ID не может быть пустым."),
    })
    @PostMapping("/character/save")
    public ResponseEntity<?> saveCharacter(@RequestBody RequestCharacterDto characterDto){
        logger.log(Level.INFO,"Вызов метода saveCharacters");
        try {
            if(characterService.saveDto(characterDto)){
                return ResponseEntity.status(HttpStatus.CREATED).body((String.format("Пользователь с именем %s создан. \n http://localhost:8091/v1/public/characters/%s",characterDto.getName(),characterDto.getId())));
            } else {
                return ResponseEntity.badRequest().body("Поле ID не может быть пустым.");
            }
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Такой ID уже существует");
        }

    }

    @ApiOperation(value = "Добавление персонажа комиксу",tags = {"Добавление комиксу героя по ID"})
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Персонаж добавлен к комиксу"),
            @ApiResponse(code = 404,message = "Персонаж не найден"),
            @ApiResponse(code = 404,message = "Комикс не найден")
    })
    @PutMapping("/comics/{comicsId}/add-hero")
    public ResponseEntity<?> addCharacterToComics(@PathVariable("comicsId") String comicsId,
                                                   @RequestBody String characterId){
        logger.log(Level.INFO,"Вызов метода addCharacterToComics");
        if(!characterService.existById(characterId)){
            return ResponseEntity.status(404).body("Персонаж не найден!");
        }
        if(!comicsService.existById(comicsId)){
            return ResponseEntity.status(404).body("Комикс не найден");
        }
        Comics comics = comicsService.getComicsById(comicsId).get();
        List<Characters> characters = comics.getCharacters();
        characters.add(characterService.findById(characterId).get());
        comicsService.editComics(comics);
        return ResponseEntity.status(200).body("Персонаж добавлен к комиксу");
    }


}
