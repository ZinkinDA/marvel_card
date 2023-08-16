package ru.zinkin.app.marvel_superheroes_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.zinkin.app.marvel_superheroes_card.dao.ComicsDao;
import ru.zinkin.app.marvel_superheroes_card.model.dto.request.RequestComicsDto;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Characters;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;
import ru.zinkin.app.marvel_superheroes_card.service.abstracts.AbstractComicsService;
import ru.zinkin.app.marvel_superheroes_card.util.ComicsConverter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComicsService implements AbstractComicsService {

    private final ComicsDao comicsDao;
    private final ComicsConverter comicsConverter;

    private final String resourceUrl = "http://localhost:8091/v1/public/image";
    @Override
    public Page<Comics> getComicsAll(Map<String, Object> claims){
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
        Page<Comics> comics = comicsDao.findAll(PageRequest.of(currentPage,elementToPage,Sort.by("name")));
        for (Comics c:comics) {
            c.setImages(String.join("/",resourceUrl,c.getImages()));
        }
        return comicsDao.findAll(PageRequest.of(currentPage,elementToPage,Sort.by("name")));
    }
    @Override
    public Optional<Comics> getComicsById(String id){
        Optional<Comics> comics = comicsDao.findById(id);
        comics.ifPresent(x -> x.setImages(String.join("/", resourceUrl, x.getImages())));
        return comicsDao.findById(id);
    }
    @Override
    public Page<Characters> getCharacterByComicsId(String id, Map<String, Object> claims){
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
        Page<Characters> characters =  comicsDao.getCharacterByComicsId(id,PageRequest.of(currentPage,elementToPage,Sort.by("name")));

        for (Characters c:characters){
            c.setImg(String.join("/",resourceUrl,c.getImg()));
        }
        return characters;
    }
    @Override
    public Boolean existById(String id){
        return comicsDao.existsById(id);
    }
    @Override
    public boolean saveComics(Comics comics){
        if(comicsDao.existsById(comics.getId())){
            throw new RuntimeException("Такой id комикса уже существует!");
        }
        if(comics.getImages() == null){
            comics.setImages("standart_comics.png");
        }
        if(comics.getCharacters() == null){
            comics.setCharacters(new ArrayList<>());
        }
        comics = comicsDao.save(comics);
        comicsDao.flush();
        return comics.getId() != null;
    }
    @Override
    public boolean saveComicsDto(RequestComicsDto requestComicsDto) {
        Comics comics = comicsConverter.converted(requestComicsDto);
        return saveComics(comics);
    }
    @Override
    public boolean editComics(Comics comment){
        Comics comics = null;
        if(comicsDao.existsById(comment.getId())){
            comics = comment;
            String[] strComics = comics.getImages().split("/");
            if(strComics.length > 1){
                comics.setImages(strComics[strComics.length-1]);
            }
            comics = comicsDao.save(comics);
        }
        comicsDao.flush();
        return comics != null;
    }
}
