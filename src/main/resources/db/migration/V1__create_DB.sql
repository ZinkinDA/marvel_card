create table characters (
                  durability smallint,
                  energy smallint,
                  fighting_skills smallint,
                  height integer,
                  intelligence smallint,
                  speed smallint,
                  strength smallint,
                  weight integer,
                  id varchar(200) not null unique ,
                  name varchar(200) not null,
                  education varchar(255),
                  images varchar(255),
                  place_of_origin varchar(255),
                  universe varchar(255),
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
            id varchar(200) not null unique ,
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