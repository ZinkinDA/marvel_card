package ru.zinkin.app.marvel_superheroes_card.controller;

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
import ru.zinkin.app.marvel_superheroes_card.service.CharacterService;
import ru.zinkin.app.marvel_superheroes_card.service.ComicsService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/private/admin")
@RequiredArgsConstructor
public class AdminController {

    @Value("${upload.path}")
    private String path;

    private final CharacterService characterService;
    private final ComicsService comicsService;

    @PostMapping("/comics/save")
    public ResponseEntity<?> saveComics(@RequestBody RequestComicsDto requestComicsDto) throws IOException {
        Comics comics = new Comics(
                requestComicsDto.getId(),
                requestComicsDto.getName(),
                requestComicsDto.getDescription(),
                requestComicsDto.getPublished(),
                requestComicsDto.getWriters(),
                requestComicsDto.getPencilers(),
                requestComicsDto.getCover_artist());

        try {
            if(!comicsService.saveComics(comics)){
                return ResponseEntity.status(400).body("Проверьте правильность ввода данных.");
            }
        } catch (RuntimeException e){
            return  ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Комикс с именем %s создан. \n http://localhost:8091/v1/public/comics/%s",comics.getName(),comics.getId()));
    }

    @PutMapping("/comics/edit")
    public ResponseEntity<?> editComics(@RequestBody Comics comics){
        if(comicsService.editComics(comics)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Комикс изменен");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Проверьте правильность uri или изменяемого пользователя не существует.");
    }

    @PostMapping("/comics/upload/{comicsId}")
    public ResponseEntity<String> uploadPhotoForComics(@PathVariable("comicsId") String id, @RequestPart("img")MultipartFile multipartFile) throws IOException {
        if(!comicsService.existById(id)){
            return ResponseEntity.status(404).body("Комикс не найден!");
        }
        Comics comics = comicsService.getComicsById(id).get();

        if(multipartFile != null){
            File file = new File(path);
            if(!file.exists()){
                boolean a = file.mkdir();
            }
            String filename = String.join(".",UUID.randomUUID().toString(),multipartFile.getOriginalFilename());
            multipartFile.transferTo(new File(String.join("\\",path,filename)));
            comics.setImages(filename);
        }
        comicsService.editComics(comics);
        return ResponseEntity.ok("Фотография загружена!");
    }

    @PostMapping("/character/upload/{characterId}")
    public ResponseEntity<String> uploadPhotoForCharacter(@PathVariable("characterId") String id,
                                                          @RequestPart("img") MultipartFile multipartFile) throws IOException {
        if(!characterService.existById(id)){
            return ResponseEntity.status(404).body("Персонаж не найден!");
        }
        Characters character = characterService.findById(id).get();
        if(multipartFile != null){
            File file = new File(path);
            if(!file.exists()){
                boolean a = file.mkdir();
            }
            String filename = String.join(".",UUID.randomUUID().toString(),multipartFile.getOriginalFilename());
            multipartFile.transferTo(new File(String.join("\\",path,filename)));
            character.setImg(filename);
            characterService.editCharacter(id,character);
        }
        return ResponseEntity.ok("Фотография загружена!");
    }

    @PostMapping("/character/save")
    public ResponseEntity<?> saveCharacter(@RequestBody RequestCharacterDto characterDto){
        Characters characters = Characters.builder()
                .id(characterDto.getId())
                .name(characterDto.getName())
                .weight(characterDto.getWeight())
                .height(characterDto.getHeight())
                .eyes(characterDto.getEyes())
                .hair(characterDto.getHair())
                .universe(characterDto.getUniverse())
                .other_aliases(characterDto.getOther_aliases())
                .education(characterDto.getEducation())
                .place_of_origin(characterDto.getPlace_of_origin())
                .known_relatives(characterDto.getKnown_relatives())
                .img("standart.png")
                .comics(new ArrayList<>())
                .build();

        try {
            if(characterService.save(characters)){
                return ResponseEntity.status(HttpStatus.CREATED).body((String.format("Пользователь с именем %s создан. \n http://localhost:8091/v1/public/characters/%s",characters.getName(),characters.getId())));
            } else {
                return ResponseEntity.badRequest().body("Поле ID не может быть пустым.");
            }
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Такой ID уже существует");
        }

    }

    @PutMapping("/comics/{comicsId}/add-hero")
    public ResponseEntity<?> addCharacterForComics(@PathVariable("comicsId") String comicsId,
                                                   @RequestBody String characterId){
        if(!characterService.existById(characterId)){
            return ResponseEntity.status(404).body("Персонаж не найден!");
        }
        if(!comicsService.existById(comicsId)){
            return ResponseEntity.status(404).body("Комикс не найден!");
        }
        Comics comics = comicsService.getComicsById(comicsId).get();
        List<Characters> characters = comics.getCharacters();
        characters.add(characterService.findById(characterId).get());
        comicsService.editComics(comics);
        return ResponseEntity.status(200).body("Комикс добавлен");
    }


}
