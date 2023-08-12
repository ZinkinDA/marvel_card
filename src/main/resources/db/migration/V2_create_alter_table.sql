alter table if exists characters_eyes
    add constraint FKkdensu1hxd4ka97m3wvr0ehmv
        foreign key (characters_id)
            references characters;

alter table if exists characters_hair
    add constraint FK1vwphu055jc5eube1y7ggwvef
        foreign key (characters_id)
            references characters;

alter table if exists characters_known_relatives
    add constraint FK4opbhjmc2pnjs84nb16sfwto9
        foreign key (characters_id)
            references characters;

alter table if exists characters_other_aliases
    add constraint FKnwxcoerbbc7ljy3ct5pg0grj3
        foreign key (characters_id)
            references characters;

alter table if exists comics_characters
    add constraint FKtj6dmwbusit97dq3hha4svykt
        foreign key (comics_id)
            references comics;

alter table if exists comics_cover_artist
    add constraint FKpfhyqnijauytq2vbjrv6idymw
        foreign key (comics_id)
            references comics;

alter table if exists comics_pencilers
    add constraint FK3isiusscap7fuh8yo8wq57bvx
        foreign key (comics_id)
            references comics;

alter table if exists comics_writers
    add constraint FKpec15atfgxah6bs706qrfyt3o
        foreign key (comics_id)
            references comics;