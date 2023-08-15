package ru.zinkin.app.marvel_superheroes_card.util.abstracts;


public interface ConverterUtil<T,E> {
    abstract public T converted(E type);

}
