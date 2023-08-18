package ru.zinkin.app.marvel_superheroes_card.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/v1/public/image/**")
                .addResourceLocations(String.format("file:///%s/",path));

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /*
    *       Оставил дефолтную конфигурацию
     */

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/v1/public/characters").setViewName("characters");
        registry.addViewController("/v1/public/characters/{characterID}").setViewName("character");
        registry.addViewController("/v1/public/characters/{characterID}/comics").setViewName("character_comics");
        registry.addViewController("/v1/public/comics").setViewName("comics");
        registry.addViewController("/v1/public/comics/{comicsId}").setViewName("comics");
        registry.addViewController("/v1/public/comics/{comicsId}/characters").setViewName("comics_characters");
        registry.addViewController("/v1/private/admin/comics/save").setViewName("admin_save_comics");
        registry.addViewController("/v1/private/admin/character/save").setViewName("admin_save_character");
        registry.addViewController("/v1/private/admin/comics/edit").setViewName("admin_edit_comics");
        registry.addViewController("/v1/private/admin/character/edit").setViewName("admin_edit_character");
        registry.addViewController("/v1/private/admin/comics/upload/{comicsId}").setViewName("admin_upload_comics_photo");
        registry.addViewController("/v1/private/admin/character/upload/{characterId}").setViewName("admin_edit_character");
        registry.addViewController("/v1/private/admin/comics/{comicsId}/add-hero").setViewName("add_hero");
        registry.addViewController("/swagger-ui.html").setViewName("add_hero");
        registry.addViewController("/v2/api-docs").setViewName("api-docs2");
    }
}
