package ru.zinkin.app.marvel_superheroes_card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.zinkin.app.marvel_superheroes_card.dao.CharacterDao;
import ru.zinkin.app.marvel_superheroes_card.dao.ComicsDao;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Characters;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CharacterService {

    private final CharacterDao characterDao;
    private final ComicsDao comicsDao;

    private final String resourceUrl = "http://localhost:8091/v1/public/image";

    @Autowired
    public CharacterService(CharacterDao characterDao, ComicsDao comicsDao) {
        this.characterDao = characterDao;
        this.comicsDao = comicsDao;
        initDB();
    }
    private void initDB() {
        Characters characters = characterDao.save(new Characters("id",
                "id",
                70,
                970,
                List.of("id_eyes"),
                List.of("id_hire"),
                "id_universe",
                List.of(),
                "id",
                "id",
                List.of("id_man"),
                "main.png"));
        Characters characters2 = characterDao.save(new Characters("id2",
                "id2",
                70,
                970,
                List.of("id_eyes2"),
                List.of("id_hire2"),
                "id_universe2",
                List.of(),
                "id",
                "id",
                List.of("id_man2"),
                "main2.png"));
        Characters characters3 = characterDao.save(new Characters("id3",
                "ada2",
                72,
                953,
                List.of("blue", "green"),
                List.of("yellow", "blue"),
                "marvel_universe2",
                List.of("Леха","Лёха"),
                "high school",
                "id3",
                List.of("id_man55"),
                "main6.png"));

        comicsDao.save(new Comics(
                "id",
                "id_name",
                "description ids",
                LocalDate.of(2023,8,2),
                "img3.txt",
                List.of("Писатель2"),
                List.of("Художник1"),
                List.of("Что-то там1"),
                List.of(characters, characters2)
        ));
        comicsDao.save(new Comics(
                "a-o-s",
                "au our state",
                "u stare output kids",
                LocalDate.of(2022,11,3),
                "img4.txt",
                List.of("Писатель2"),
                List.of("Художник1"),
                List.of("Что-то там2"),
                List.of(characters3)
        ));
        comicsDao.save(new Comics(
                "tt3-4s-32",
                "tees",
                "description t-e-e-s",
                LocalDate.of(2023,1,21),
                "img5.txt",
                List.of("Писатель1"),
                List.of("Художник2"),
                List.of("Что-то там3"),
                List.of(characters3, characters2)
        ));

    }


    public Page<Characters> getAll(Map<String,Object> claims){
        int currentPage = 0;
        int elementToPage = 10;
        if(claims.containsKey("currentPage")){
            currentPage = (int) claims.get("currentPage");
        }
        if(claims.containsKey("elementToPage")){
            elementToPage = (int) claims.get("elementToPage");
            if(elementToPage <= 0){
                elementToPage = 10;
            }
        }
        Page<Characters> characters = characterDao.findAll(PageRequest.of(currentPage,elementToPage,Sort.by("name")));
        for (Characters ch:characters) {
            ch.setImg(String.join("/",resourceUrl,ch.getImg()));
        }
        return characters;
    }

    public Optional<Characters> findById(String id){
        Optional<Characters> characters = characterDao.findById(id);
        if(characters.isPresent()){
            characters.get().setImg(String.join("/",resourceUrl,characters.get().getImg()));
        }
        return characters;
    }

    public Page<Comics> findComicsByCharacterId(String ch,Map<String,Object> claims){
        int currentPage = 0;
        int elementToPage = 10;
        if(claims.containsKey("currentPage")){
            currentPage = (int) claims.get("currentPage");
        }
        if(claims.containsKey("elementToPage")){
            elementToPage = (int) claims.get("elementToPage");
            if(elementToPage <= 0){
                elementToPage = 10;
            }
        }

        Page<Comics> comics = characterDao.getComicsByCharacterId(ch,PageRequest.of(currentPage,elementToPage,Sort.by("name")));
        if(!comics.isEmpty()) {
            for (Comics c:comics) {
                c.setImages(String.join("/",resourceUrl,c.getImages()));
            }
        }
        return comics;
    }

    public boolean existById(String id){
        return characterDao.existsById(id);
    }

    public boolean save(Characters character){
        if(character.getId() != null && !characterDao.existsById(character.getId())){
            character = characterDao.save(character);
            return true;
        } else if((characterDao.existsById(character.getId()))){
            throw new RuntimeException("Такой ID уже существует.");
        }
        return false;
    }

    public boolean editCharacter(String id,Characters characters){
        if(!id.equals(characters.getId())){
            return false;
        } else {
            characterDao.save(characters);
            return true;
        }
    }


}
