package ru.zinkin.app.marvel_superheroes_card.service;

import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.zinkin.app.marvel_superheroes_card.dao.ComicsDao;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Character;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;

import javax.xml.stream.events.Comment;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComicsService {

    private final ComicsDao comicsDao;

    public Page<Comics> getComicsAll(Map<String,Object> claims){
        /*
            Реализовать пагинацию везде PageRequest.of(page,size,Sort);

         */
        int currentPage = 0;
        int elementToPage = 10;
        if(claims.containsKey("currentPage")) {
            currentPage = (int) claims.get("currentPage");
        }
        if(claims.containsKey("elementToPage")){
            elementToPage = (int) claims.get("elementToPage");
            if(elementToPage <= 0){
                elementToPage = 10;
            }
        }
        return comicsDao.findAll(PageRequest.of(currentPage,elementToPage,Sort.by("name")));
    }

    public Optional<Comics> getComicsById(String id){
        return comicsDao.findById(id);
    }

    public Page<Character> getCharacterByComicsId(String id,Map<String,Object> claims){
        int currentPage = 0;
        int elementToPage = 10;
        if(claims.containsKey("currentPage")) {
            currentPage = (int) claims.get("currentPage");
        }
        if(claims.containsKey("elementToPage")){
            elementToPage = (int) claims.get("elementToPage");
            if(elementToPage <= 0){
                elementToPage = 10;
            }
        }
        return comicsDao.getCharacterByComicsId(id,PageRequest.of(currentPage,elementToPage,Sort.by("name")));
    }

    public Boolean existById(String id){
        return comicsDao.existsById(id);
    }

    //_________________ При создании нового комикса сначало сохраняем.______________
    public boolean saveComics(Comics comics){
        if(comicsDao.existsById(comics.getId())){
            throw new RuntimeException("Такой id комикса уже существует!");
        }
        comics = comicsDao.save(comics);
        return comics.getId() != null;
    }

    // _________________________Потом редактируем___________________________________

    public boolean editComics(String id, Comics comment){
        Comics comics = null;
        if(id.equals(comment.getId()) || comicsDao.existsById(comment.getId())){
            comics = comicsDao.save(comment);
        }
        return comics != null;
    }
    public boolean editComics(Comics comment){
        Comics comics = null;
        if(comicsDao.existsById(comment.getId())){
            comics = comicsDao.save(comment);
        }
        return comics != null;
    }
}
