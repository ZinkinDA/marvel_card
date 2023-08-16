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
        Characters characters = characterDao.save(new Characters("abraxas",
                "ABRAXAS",
                null,
                null,
                List.of("Blue"),
                List.of("Black with gray streaks"),
                "Marvel Universe",
                List.of("unrevealed"),
                "No formal education",
                "Within Eternity",
                List.of("Eternity (\"Father\")"),
                "standart_character.png"));
        Characters characters2 = characterDao.save(new Characters("aberration",
                "ABERRATION",
                null,
                null,
                List.of("Green (as Philips)","Green (as Aberration)"),
                List.of("Brown (as Philips)","Green (as Aberration)"),
                "Marvel Universe",
                List.of("None"),
                "Unrevealed, but Aberration received military training",
                "Unrevealed",
                List.of("Unrevealed"),
                "main2.png"));
        Characters characters3 = characterDao.save(new Characters("spider-man-peter-parker",
                "SPIDER-MAN",
                67,
                167,
                List.of("brown", "None"),
                List.of("brown", "None"),
                "Empire State",
                List.of("Spider-Man(Peter Parker)"),
                "high school",
                "20 Ingram Street, Forest Hills NY",
                List.of("Mary Parker(Grandmother)",""),
                "main6.png"));
        Characters characters4 = characterDao.save(new Characters("spider-man-miles-morales",
                "SPIDER-MAN",
                67,
                167,
                List.of("brown"),
                List.of("brown"),
                "Empire State",
                List.of("Spider-Man(MilesMorales)"),
                "high school",
                "20 Ingram Street, Forest Hills NY",
                List.of("Mary Parker(Grandmother)"),
                "main6.png"));
        Characters characters5 = characterDao.save(new Characters("spider-man-miles-morales",
                "SPIDER-MAN",
                67,
                167,
                List.of("brown"),
                List.of("brown"),
                "Empire State",
                List.of("Spider-Man(MilesMorales)"),
                "high school",
                "20 Ingram Street, Forest Hills NY",
                List.of("Mary Parker(Grandmother)"),
                "main6.png"));
        Characters characters6 = characterDao.save(new Characters("spider-man-miles-morales",
                "VENOM",
                75,
                245,
                List.of("brown"),
                List.of("Brown (shaves head)"),
                "Marvel Universe",
                List.of("Spider-Man","formerly Scorpion"),
                "High school graduate",
                "Yonkers, New York",
                List.of("None"),
                "main6.png"));
        Characters characters7 = characterDao.save(new Characters("spider-man-miles-morales",
                "TOR Odinson",
                76,
                639,
                List.of("Blue"),
                List.of("Blond"),
                "Marvel Universe",
                List.of("God of Thunder","Goldilocks"),
                "None",
                "Asgard",
                List.of("Loki","Zevs"),
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
