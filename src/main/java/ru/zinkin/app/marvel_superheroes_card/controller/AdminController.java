package ru.zinkin.app.marvel_superheroes_card.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

@Validated
@RestController
@RequestMapping("/v1/private/admin")
@RequiredArgsConstructor
@Tag(name = "Admin controller",description = "Работа с персонажами,комиксами. (POST/PUT)")
public class AdminController {

    private final Logger logger = Logger.getLogger("Admin");
    @Value("${upload.path}")
    private String path;
    private final AbstractCharacterService characterService;
    private final ComicsService comicsService;

    @Operation(summary = "Сохранение комикса",tags = {"Сохранение комикса","Комикс"},description = "Метод сохранения комикса.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Комикс создан"),
            @ApiResponse(responseCode = "400" ,description = "Не правильный ввод данных или персонаж уже существует."),
            @ApiResponse(responseCode = "400" , description = "Проверьте правильность ввода данных.")
    })
    @PostMapping("/comics/save")
    public ResponseEntity<?> saveComics(@RequestBody @NotNull @Valid RequestComicsDto requestComicsDto){
        logger.log(Level.INFO,"Вызов метода saveComics");
        if(comicsService.existById(requestComicsDto.getId())){
            return ResponseEntity.badRequest().body("Комикс с таким id уже существует.");
        }
        if (!comicsService.saveComicsDto(requestComicsDto)) {
            return ResponseEntity.status(400).body("Проверьте правильность ввода данных.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Комикс с именем %s создан. \n http://localhost:8091/v1/public/comics/%s",requestComicsDto.getName(),requestComicsDto.getId()));
    }
    @Operation(summary = "Изменение комикса",tags = {"Изменение комикса","Комикс"},description = "Метод изменения комикса.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Комикс изменен"),
            @ApiResponse(responseCode = "400",description = "Проверьте правильность ввода данных"),
            @ApiResponse(responseCode = "400",description = "Изменяемого пользователя не существует.")
    })
    @PutMapping("/comics/edit")
    public ResponseEntity<?> editComics(@RequestBody @Valid Comics comics){
        logger.log(Level.INFO,"Вызов метода editComics");
        if (comicsService.editComics(comics)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Комикс изменен");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Изменяемого пользователя не существует.");
    }

    @Operation(summary = "Изменение фото для комикса",tags = {"Загрузка фотографии","Комикс","Изменение комикса"},description = "Метод изменения фотографии комикса.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Фото загружено и изменено"),
            @ApiResponse(responseCode = "401",description = "Не верный тип файла"),
            @ApiResponse(responseCode = "404",description = "Комикс не найден")
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
                return ResponseEntity.status(401).body("Не верный тип файла!");
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

    @Operation(summary = "Изменение фото для героя",tags = {"Изменение персонажа","Загрузка фотографии","Персонаж"},description="Метод для загрузки и изменения фотографии по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Фото загружено и изменено"),
            @ApiResponse(responseCode = "401",description = "Не верный тип файла"),
            @ApiResponse(responseCode = "404",description = "Комикс не найден")
    })
    @PostMapping("/character/upload/{characterId}")
    public ResponseEntity<String> uploadPhotoForCharacter(@PathVariable("characterId") String id,
                                                          @RequestPart("img") @NotNull MultipartFile multipartFile) throws IOException {
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

    @Operation(summary = "Сохранение героя",tags = {"Сохранение персонажа","Персонаж"},description = "Метод для сохранения персонажа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Герой сохранен"),
            @ApiResponse(responseCode = "400",description = "Такой ID уже существует"),
            @ApiResponse(responseCode = "400",description = "Проверьте правильность ввода данных")
    })
    @PostMapping("/character/save")
    public ResponseEntity<?> saveCharacter(@RequestBody @NotNull @Valid RequestCharacterDto characterDto){
        logger.log(Level.INFO,"Вызов метода saveCharacters");
        if(characterService.existById(characterDto.getId())){
            return ResponseEntity.badRequest().body("Персонаж с таким id уже существует.");
        }
        if(characterService.saveDto(characterDto)){
            return ResponseEntity.status(HttpStatus.CREATED).body((String.format("Пользователь с именем %s создан. \n http://localhost:8091/v1/public/characters/%s",characterDto.getName(),characterDto.getId())));
        } else {
            return ResponseEntity.badRequest().body("Поле ID не может быть пустым.");
        }
    }

    @Operation(summary = "Добавление персонажа комиксу",tags = {"Персонаж","Комикс","Изменение персонажа","Изменение комикса"},description = "Метод для добавления персонажа к комиксу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Персонаж добавлен к комиксу"),
            @ApiResponse(responseCode = "404",description = "Персонаж не найден"),
            @ApiResponse(responseCode = "404",description = "Комикс не найден")
    })
    @PutMapping("/comics/{comicsId}/add-hero")
    public ResponseEntity<?> addCharacterToComics(@PathVariable("comicsId") @NotNull @NotEmpty String comicsId,
                                                   @RequestBody @NotNull @NotEmpty String characterId){
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

    @Operation(summary = "Изменение персонажа",tags = {"Изменение персонажа"},description = "Метод для изменения персонажа.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Персонаж изменен"),
            @ApiResponse(responseCode = "400",description = "Проверьте правильность ввода данных"),
            @ApiResponse(responseCode = "400",description = "Изменяемого пользователя не существует.")
    })
    @PutMapping("/character/edit")
    public ResponseEntity<?> editCharacter(@RequestBody @Valid Characters comics){
        logger.log(Level.INFO,"Вызов метода editCharacter");
        if(characterService.editCharacter(comics)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Персонаж изменен");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Проверьте правильность uri или изменяемого пользователя не существует.");
    }


}
