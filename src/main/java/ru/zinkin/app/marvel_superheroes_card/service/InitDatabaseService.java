package ru.zinkin.app.marvel_superheroes_card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zinkin.app.marvel_superheroes_card.dao.CharacterDao;
import ru.zinkin.app.marvel_superheroes_card.dao.ComicsDao;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Characters;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;

import java.time.LocalDate;
import java.util.List;

@Service
public class InitDatabaseService {

    private final ComicsDao comicsDao;
    private final CharacterDao characterDao;

    @Autowired
    public InitDatabaseService(ComicsDao comicsDao, CharacterDao characterDao) {
        this.comicsDao = comicsDao;
        this.characterDao = characterDao;
        initDatabaseMethod();
    }

    public void initDatabaseMethod(){
        Characters characters = characterDao.save(new Characters("id",
                "a_name",
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
                "b_name",
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
                "c_name",
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
                "a_comics_name",
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
                "b-o-s",
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



}
