package ru.zinkin.app.marvel_superheroes_card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.zinkin.app.marvel_superheroes_card.dao.CharacterDao;
import ru.zinkin.app.marvel_superheroes_card.dao.ComicsDao;
import ru.zinkin.app.marvel_superheroes_card.model.dto.request.RequestCharacterDto;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Characters;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;
import ru.zinkin.app.marvel_superheroes_card.service.abstracts.AbstractCharacterService;
import ru.zinkin.app.marvel_superheroes_card.util.CharactersConverter;
import ru.zinkin.app.marvel_superheroes_card.util.abstracts.ConverterUtil;

import java.util.Map;
import java.util.Optional;

@Service
public class CharacterService implements AbstractCharacterService{

    private final CharacterDao characterDao;
    private final ComicsDao comicsDao;
    @Qualifier(value = "characterConverter")
    private final ConverterUtil<Characters,RequestCharacterDto> charactersConverter;

    private final String resourceUrl = "http://localhost:8091/v1/public/image";

    @Autowired
    public CharacterService(CharacterDao characterDao, ComicsDao comicsDao, CharactersConverter charactersConverter) {
        this.characterDao = characterDao;
        this.comicsDao = comicsDao;
        this.charactersConverter = charactersConverter;
        initDB();
    }
    private void initDB() {

    }

    @Override
    public Page<Characters> getAll(Map<String, Object> claims){
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

    @Override
    public Optional<Characters> findById(String id){
        Optional<Characters> characters = characterDao.findById(id);
        if(characters.isPresent()){
            characters.get().setImg(String.join("/",resourceUrl,characters.get().getImg()));
        }
        return characters;
    }

    @Override
    public Page<Comics> findComicsByCharacterId(String ch, Map<String, Object> claims){
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

    @Override
    public boolean existById(String id){
        return characterDao.existsById(id);
    }

    @Override
    public boolean save(Characters character){
        if(character.getId() != null && !characterDao.existsById(character.getId())){
            character = characterDao.save(character);
            return true;
        } else if((characterDao.existsById(character.getId()))){
            throw new RuntimeException("Такой ID уже существует.");
        }
        return false;
    }
    @Override
    public boolean saveDto(RequestCharacterDto character){
        Characters characters = charactersConverter.converted(character);
        return save(characters);
    }

    @Override
    public boolean editCharacter(String id, Characters characters){
        if(!id.equals(characters.getId())){
            return false;
        } else {
            characterDao.save(characters);
            return true;
        }
    }
    @Override
    public boolean editCharacter(Characters comment){
        Characters characters = null;
        Characters comics = null;

        if(characterDao.existsById(comment.getId())){
            comics = findById(comment.getId()).get();
            characters = comment;
            characters.setId(comics.getId());
            characters.setName(comics.getName());
            characters.setImg(comics.getImg());
            String[] strComics = characters.getImg().split("/");
            if(strComics.length > 1){
                characters.setImg(strComics[strComics.length-1]);
            }
            characters = characterDao.save(characters);
        }
        comicsDao.flush();
        return characters != null;
    }



}
