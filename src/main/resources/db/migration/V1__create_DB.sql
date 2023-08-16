create table characters (
            height integer,
            weight integer,
            education varchar(255),
            id varchar(200) not null,
            images varchar(255),
            name varchar(200) not null,
            place_of_origin varchar(255),
            universe varchar(200),
            primary key (id)
);
create table characters_eyes (
            characters_id varchar(200) not null,
            eyes varchar(120)
);
create table characters_hair (
            characters_id varchar(200) not null,
            hair varchar(120)
);
create table characters_known_relatives (
            characters_id varchar(200) not null,
            known_relatives varchar(150)
);
create table characters_other_aliases (
            characters_id varchar(200) not null,
            other_aliases varchar(120)
);
create table comics (
            published date not null,
            id varchar(200) not null,
            name varchar(200) not null,
            description varchar(2048) not null,
            img varchar(255),
            primary key (id)
);
create table comics_characters (
            comics_id varchar(200) not null,
            character_id varchar(255) not null
);
create table comics_cover_artist (
            comics_id varchar(200) not null,
            cover_artist varchar(255)
);
create table comics_pencilers (
            comics_id varchar(200) not null,
            pencilers varchar(255)
);
create table comics_writers (
            comics_id varchar(200) not null,
            writers varchar(255)
);