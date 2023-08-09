package ru.zinkin.app.marvel_superheroes_card.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Character;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;

@EnableJpaRepositories(repositoryBaseClass = Character.class)
@Repository
public interface CharacterDao extends JpaRepository<Character,String>{

    @Query("select c.comics from Character c join c.comics where c.id = :id" )
    Page<Comics> getComicsByCharacterId(@Param("id") String id, Pageable pageable);

}
