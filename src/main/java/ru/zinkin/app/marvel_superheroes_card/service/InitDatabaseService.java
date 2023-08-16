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

        Comics comics = comicsDao.save(new Comics(
                "immortal_hulk_2018_1",
                "Immortal Hulk (2018) #1",
                "The FIRST issue of the Immortal Hulk! Al Ewing and Joe Bennett's critically-acclaimed and best-selling Immortal Hulk series kicks off here with THE RETURN OF BRUCE BANNER! You know Bruce Banner. He's quiet, calm, never complains. He's a man who believes he can use the darkest elements of his personality to do good in the world. If someone were to shoot him in the head...all he'd do is die. But the horror lives deeper. A horror that refuses to die. When night falls something other than the man gets up again. The horror is the Immortal Hulk.",
                LocalDate.of(2018,6,6),
                "standart_comics.png",
                List.of("Al Ewing"),
                List.of("Joe Bennett"),
                List.of(),
                List.of()
        ));
        Comics comics2 =comicsDao.save(new Comics(
                "marvels_spider-man_2_2023_1",
                "Marvel's Spider-Man 2 (2023) #1",
                "In the lead up to events in Marvel's Spider-Man 2, Peter and Miles team up to face an all-new threat!",
                LocalDate.of(2023,5,1),
                "standart_comics.png",
                List.of("Christos Gage"),
                List.of("Todd Nauck", "Ig Guara"),
                List.of("Todd Nauck"),
                List.of()
        ));
        Comics comics3 = comicsDao.save(new Comics(
                "911_20th_anniversary_tribute_the_four_fives_2021_1",
                "9/11 20th Anniversary Tribute: The Four Fives (2021) #1",
                "In honor and in remembrance of 9/11/01.",
                LocalDate.of(2021,9,8),
                "standart_comics.png",
                List.of("Joe Quesada"),
                List.of("John Romita"),
                List.of(),
                List.of()
        ));
        Comics comics4= comicsDao.save(new Comics(
                "the_vitals_true_ems_stories_2021",
                "The Vitals: True EMS Stories (2021)",
                "Healthcare workers are on the frontlines during this difficult time. THE VITALS tells tales of some real heroes, the EMS teams, as they travel to high-risk environments in order to rescue those in urgent need.",
                LocalDate.of(2021,6,1),
                "standart_comics.png",
                List.of("Sean Ryan"),
                List.of("Ramón F. Bachs"," Ray-Anthony Height","J.L. Giles","Ze Carlos"),
                List.of(),
                List.of()
        ));
        Comics comics5 = comicsDao.save(new Comics(
                "spider-man_2022_11",
                "Spider-Man (2022) #11",
                "(RE)INTRODUCING…SPIDER-BOY! The battle to save the Spider-Verse may be over, but spinning out of the restored Web of Life and Destiny returns the spectacular SPIDER-BOY, Peter Parker's stupendous sidekick! Wait, that can't be right - who IS this Spider-Boy, and what is his connection to the Amazing Spider-Man?!",
                LocalDate.of(2023,4,16),
                "standart_comics.png",
                List.of("Dan Slott"),
                List.of("Luciano Vecchio"),
                List.of("Mark Bagley"),
                List.of()
        ));
        Comics comics6 = comicsDao.save(new Comics(
                "venom_2018_1",
                "Venom (2018) #1",
                "In the wake of S.H.I.E.L.D.'s collapse, an ancient and primordial evil has been awakened beneath the streets of New York, and with it, something equally evil has awakened in that most Wicked of Webslingers - VENOM! Still a Lethal Protector of the innocents in New York, this never-before-seen threat could force Venom to relinquish everything it holds dear - including Eddie Brock! Join two of the hottest creators in comics today, Donny Cates and Ryan Stegman, for a VENOM adventure a thousand years in the making!",
                LocalDate.of(2018,5,9),
                "standart_comics.png",
                List.of("Donny Cates"),
                List.of("Ryan Stegman"),
                List.of(),
                List.of()
        ));
        Comics comics7 = comicsDao.save(new Comics(
                "thor_2018_1",
                "Thor (2018) #1",
                "JASON AARON & MIKE DEL MUNDO TAKE THE PRINCE OF ASGARD IN A WHOLE NEW DIRECTION! Thor Odinson has regained his mantle – and with it, a wild new world of trouble on his mighty hands! The artifacts of Asgard have been scattered across the earth, and to reclaim them, Thor will have to face some ugly truths. Like the production cost of hundreds of new hammers! And the Thunder God is going to need every last one of them if he’s going to stop the unstoppable Juggernaut. Jason Aaron takes the Prince of Asgard in a whole new direction with YOUNG GUN artist Mike Del Mundo joining him at the helm! And don’t miss the latest chapter of the King Thor saga with acclaimed BLACK BOLT artist Christian Ward, as the Thor of the far future encounters an old friend who’s undergone some startling changes.",
                LocalDate.of(2018,8,13),
                "standart_comics.png",
                List.of("Jason Aaron"),
                List.of("Christian Ward"),
                List.of("Mike Del Mundo"),
                List.of()
        ));
    }



}
