package ru.zinkin.app.marvel_superheroes_card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zinkin.app.marvel_superheroes_card.dao.CharacterDao;
import ru.zinkin.app.marvel_superheroes_card.dao.ComicsDao;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Characters;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public void initDatabaseMethod() {
        {
            Characters characters = characterDao.save(new Characters("abraxas",
                    (byte) 4,
                    (byte) 5,
                    (byte) 6,
                    (byte) 7,
                    (byte) 5,
                    (byte) 9,
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
                    (byte) 4,
                    (byte) 5,
                    (byte) 6,
                    (byte) 7,
                    (byte) 5,
                    (byte) 9,
                    "ABERRATION",
                    null,
                    null,
                    List.of("Green (as Philips)", "Green (as Aberration)"),
                    List.of("Brown (as Philips)", "Green (as Aberration)"),
                    "Marvel Universe",
                    List.of("None"),
                    "Unrevealed, but Aberration received military training",
                    "Unrevealed",
                    List.of("Unrevealed"),
                    "standart_character.png"));
            Characters characters3 = characterDao.save(new Characters("spider-man-peter-parker",
                    (byte) 4,
                    (byte) 5,
                    (byte) 6,
                    (byte) 7,
                    (byte) 5,
                    (byte) 9,
                    "SPIDER-MAN",
                    67,
                    167,
                    List.of("brown", "None"),
                    List.of("brown", "None"),
                    "Empire State",
                    List.of("Spider-Man(Peter Parker)"),
                    "high school",
                    "20 Ingram Street, Forest Hills NY",
                    List.of("Mary Parker(Grandmother)", ""),
                    "standart_character.png"));
            Characters characters4 = characterDao.save(new Characters("spider-man-miles-morales",
                    (byte) 4,
                    (byte) 5,
                    (byte) 6,
                    (byte) 7,
                    (byte) 5,
                    (byte) 9,
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
                    "standart_character.png"));
            Characters characters6 = characterDao.save(new Characters("VENOM_MASTER",
                    (byte) 4,
                    (byte) 5,
                    (byte) 6,
                    (byte) 7,
                    (byte) 5,
                    (byte) 9,
                    "VENOM",
                    75,
                    245,
                    List.of("brown"),
                    List.of("Brown (shaves head)"),
                    "Marvel Universe",
                    List.of("Spider-Man", "formerly Scorpion"),
                    "High school graduate",
                    "Yonkers, New York",
                    List.of("None"),
                    "standart_character.png"));
            Characters characters7 = characterDao.save(new Characters("TOR-Odinson",
                    (byte) 4,
                    (byte) 5,
                    (byte) 6,
                    (byte) 7,
                    (byte) 5,
                    (byte) 9,
                    "TOR Odinson",
                    77,
                    640,
                    List.of("Blue"),
                    List.of("Blond"),
                    "Marvel Universe",
                    List.of("Donald M. Blake", "God of Thunder"
                            , "Son of Odin", "The Thunderer", "Lord of Asgard",
                            "Jake Olson", "Sigurd Jarlson", "Donar", "Donner",
                            "Hloriddi", "Unhappy Hrungnir’s Playmate",
                            "Veur", "Hrodr’s Foe-Man", "Longbeard’s Son",
                            " Vingthor the Hurler", "Siegfried", "Siegmund",
                            "Woe-King", "(impersonated) Hercules", "Harokin",
                            "Freya", "formerly bound to Eric Masterson"),
                    "(Thor) Tutored by scholars of Asgard, (Blake) MD",
                    "A cave in Norway",
                    List.of("Gaea (mother)", "Frigga (adoptive mother)", "Odin Borson (father, deceased)", "Sigyn (sister-in-law)", "Solveig (sister-in-law, deceased)", " Loki (adoptive brother)", "Balder", "Vidar (half-brothers)", "Vili", " Ve (paternal uncles, deceased)", "Bor Burison (paternal grandfather, deceased)", "Bestia (paternal grandmother, presumed deceased)", "Buri (Tiwaz, great- grandfather)", "Bolthorn (great-grandfather", "presumed deceased)", " Jormungand (Midgard Serpent)", "Fenris Wolf (nephew)", "Hela (niece)", "large extended family via Gaea and others"),
                    "standart_character.png"));

        }
        {
            comicsDao.save(new Comics(
                    "immortal_hulk_2018_1",
                    "Immortal Hulk (2018) #1",
                    "The FIRST issue of the Immortal Hulk! Al Ewing and Joe Bennett's critically-acclaimed and best-selling Immortal Hulk series kicks off here with THE RETURN OF BRUCE BANNER! You know Bruce Banner. He's quiet, calm, never complains. He's a man who believes he can use the darkest elements of his personality to do good in the world. If someone were to shoot him in the head...all he'd do is die. But the horror lives deeper. A horror that refuses to die. When night falls something other than the man gets up again. The horror is the Immortal Hulk.",
                    LocalDate.of(2018, 6, 6),
                    "standart_comics.png",
                    List.of("Al Ewing"),
                    List.of("Joe Bennett"),
                    new ArrayList<>(),
                    new ArrayList<>()
            ));
            comicsDao.save(new Comics(
                    "marvels_spider-man_2_2023_1",
                    "Marvel's Spider-Man 2 (2023) #1",
                    "In the lead up to events in Marvel's Spider-Man 2, Peter and Miles team up to face an all-new threat!",
                    LocalDate.of(2023, 5, 1),
                    "standart_comics.png",
                    List.of("Christos Gage"),
                    List.of("Todd Nauck", "Ig Guara"),
                    List.of("Todd Nauck"),
                    new ArrayList<>()
            ));
            comicsDao.save(new Comics(
                    "911_20th_anniversary_tribute_the_four_fives_2021_1",
                    "9/11 20th Anniversary Tribute: The Four Fives (2021) #1",
                    "In honor and in remembrance of 9/11/01.",
                    LocalDate.of(2021, 9, 8),
                    "standart_comics.png",
                    List.of("Joe Quesada"),
                    List.of("John Romita"),
                    new ArrayList<>(),
                    new ArrayList<>()
            ));
            comicsDao.save(new Comics(
                    "the_vitals_true_ems_stories_2021",
                    "The Vitals: True EMS Stories (2021)",
                    "Healthcare workers are on the frontlines during this difficult time. THE VITALS tells tales of some real heroes, the EMS teams, as they travel to high-risk environments in order to rescue those in urgent need.",
                    LocalDate.of(2021, 6, 1),
                    "standart_comics.png",
                    List.of("Sean Ryan"),
                    List.of("Ramón F. Bachs", " Ray-Anthony Height", "J.L. Giles", "Ze Carlos"),
                    new ArrayList<>(),
                    new ArrayList<>()
            ));
            comicsDao.save(new Comics(
                    "109961",
                    "Spider-Man (2022) #11",
                    "(RE)INTRODUCING…SPIDER-BOY! The battle to save the Spider-Verse may be over, but spinning out of the restored Web of Life and Destiny returns the spectacular SPIDER-BOY, Peter Parker's stupendous sidekick! Wait, that can't be right - who IS this Spider-Boy, and what is his connection to the Amazing Spider-Man?!",
                    LocalDate.of(2023, 4, 16),
                    "standart_comics.png",
                    List.of("Dan Slott"),
                    List.of("Luciano Vecchio"),
                    List.of("Mark Bagley"),
                    new ArrayList<>()
            ));
            comicsDao.save(new Comics(
                    "venom_2018_1",
                    "Venom (2018) #1",
                    "In the wake of S.H.I.E.L.D.'s collapse, an ancient and primordial evil has been awakened beneath the streets of New York, and with it, something equally evil has awakened in that most Wicked of Webslingers - VENOM! Still a Lethal Protector of the innocents in New York, this never-before-seen threat could force Venom to relinquish everything it holds dear - including Eddie Brock! Join two of the hottest creators in comics today, Donny Cates and Ryan Stegman, for a VENOM adventure a thousand years in the making!",
                    LocalDate.of(2018, 5, 9),
                    "standart_comics.png",
                    List.of("Donny Cates"),
                    List.of("Ryan Stegman"),
                    new ArrayList<>(),
                    new ArrayList<>()
            ));
            comicsDao.save(new Comics(
                    "thor_2018_1",
                    "Thor (2018) #1",
                    "JASON AARON & MIKE DEL MUNDO TAKE THE PRINCE OF ASGARD IN A WHOLE NEW DIRECTION! Thor Odinson has regained his mantle – and with it, a wild new world of trouble on his mighty hands! The artifacts of Asgard have been scattered across the earth, and to reclaim them, Thor will have to face some ugly truths. Like the production cost of hundreds of new hammers! And the Thunder God is going to need every last one of them if he’s going to stop the unstoppable Juggernaut. Jason Aaron takes the Prince of Asgard in a whole new direction with YOUNG GUN artist Mike Del Mundo joining him at the helm! And don’t miss the latest chapter of the King Thor saga with acclaimed BLACK BOLT artist Christian Ward, as the Thor of the far future encounters an old friend who’s undergone some startling changes.",
                    LocalDate.of(2018, 8, 13),
                    "standart_comics.png",
                    List.of("Jason Aaron"),
                    List.of("Christian Ward"),
                    List.of("Mike Del Mundo"),
                    new ArrayList<>()
            ));
        }
    }



}
