package ru.zinkin.app.marvel_superheroes_card.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/private/admin/resource")
public class ResourceController {

    @Value("${img.upload.path}")
    private String path;

}
