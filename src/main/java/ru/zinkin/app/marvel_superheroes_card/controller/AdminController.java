package ru.zinkin.app.marvel_superheroes_card.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.zinkin.app.marvel_superheroes_card.model.dto.ComicsDto;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;
import ru.zinkin.app.marvel_superheroes_card.service.CharacterService;
import ru.zinkin.app.marvel_superheroes_card.service.ComicsService;

import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;
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
    public ResponseEntity<?> saveComics(@RequestBody ComicsDto comicsDto) throws IOException {
        Comics comics = new Comics(
                comicsDto.getId(),
                comicsDto.getName(),
                comicsDto.getDescription(),
                comicsDto.getPublished(),
                comicsDto.getWriters(),
                comicsDto.getPencilers(),
                comicsDto.getCover_artist());

        try {
            if(!comicsService.saveComics(comics)){
                return ResponseEntity.status(400).body("Проверьте правильность ввода данных.");
            }
        } catch (RuntimeException e){
            return  ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Комикс с именем %s создан. \n http://localhost:8091/v1/public/comics/%s",comics.getName(),comics.getId()));
    }

    @PutMapping("/comics/{comicsId}")
    public ResponseEntity<?> editComics(@PathVariable("comicsId") String comicsId,
                                        @RequestBody Comics comics){
        if(comicsService.editComics(comicsId,comics)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Комментарий изменен");
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


}
