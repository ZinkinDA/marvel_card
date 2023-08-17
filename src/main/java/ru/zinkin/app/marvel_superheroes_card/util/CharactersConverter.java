package ru.zinkin.app.marvel_superheroes_card.util;

import org.springframework.stereotype.Component;
import ru.zinkin.app.marvel_superheroes_card.model.dto.request.RequestCharacterDto;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Characters;
import ru.zinkin.app.marvel_superheroes_card.util.abstracts.ConverterUtil;

import java.util.ArrayList;
@Component
public class CharactersConverter implements ConverterUtil<Characters,RequestCharacterDto> {

    @Override
    public Characters converted(RequestCharacterDto characterDto) {

        return Characters.builder()
                .id(characterDto.getId())
                .DURABILITY(characterDto.getDURABILITY())
                .ENERGY(characterDto.getENERGY())
                .FIGHTING_SKILLS(characterDto.getFIGHTING_SKILLS())
                .INTELLIGENCE(characterDto.getINTELLIGENCE())
                .STRENGTH(characterDto.getSTRENGTH())
                .SPEED(characterDto.getSPEED())
                .name(characterDto.getName())
                .weight(characterDto.getWeight())
                .height(characterDto.getHeight())
                .eyes(characterDto.getEyes())
                .hair(characterDto.getHair())
                .universe(characterDto.getUniverse())
                .other_aliases(characterDto.getOther_aliases())
                .education(characterDto.getEducation())
                .place_of_origin(characterDto.getPlace_of_origin())
                .known_relatives(characterDto.getKnown_relatives())
                .img("standart_character.png")
                .comics(new ArrayList<>())
                .build();
    }
}
