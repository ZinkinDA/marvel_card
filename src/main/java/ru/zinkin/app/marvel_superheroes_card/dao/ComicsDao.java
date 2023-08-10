package ru.zinkin.app.marvel_superheroes_card.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Characters;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;

@EnableJpaRepositories(repositoryFactoryBeanClass = ComicsDao.class)
@Repository
public interface ComicsDao extends JpaRepository<Comics,String>{
    @Query("select c.characters from Comics c join c.characters where c.id = :id order by c.name ")
    Page<Characters> getCharacterByComicsId(@Param("id") String id, Pageable pageable);

}
