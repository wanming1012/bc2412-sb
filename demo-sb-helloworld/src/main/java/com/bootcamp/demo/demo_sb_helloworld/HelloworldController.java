package com.bootcamp.demo.demo_sb_helloworld;

import java.time.*;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
public class HelloworldController {
    @GetMapping(value = "/greeting")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping(value = "/integer")
    public Integer getInteger() {
        return 10;
    }

    @GetMapping(value = "/integers")
    public Integer[] getIntegerArray() {
        return new Integer[] {1, 2, 3, 4, 5};
    }

    @GetMapping(value = "/strings")
    public List<String> getStrings() {
        return Arrays.asList("Hello", "World", "wan", "ming");
    }

    @GetMapping(value = "/cat")
    public Cat getCat() {
        return new Cat("Ming", 2);
    }

    @GetMapping(value = "/cats")
    public List<Cat> getCats() {
        return Arrays.asList(new Cat("Ming", 2), new Cat("Wan", 3));
    }

    @GetMapping(value = "/datetime")
    public LocalDateTime getDatetime() {
        return LocalDateTime.now();
    }

    @GetMapping(value = "/shoppingmall")
    public ShoppingMall getShoppingMall() {
        ShoppingMall.Cinema.Film film1 =
                ShoppingMall.Cinema.Film.builder().name("Avengers: Endgame")
                        .releaseDate(LocalDate.of(2019, 4, 24)).build();
        ShoppingMall.Cinema.Film film2 = ShoppingMall.Cinema.Film.builder()
                .name("Spiderman: Far From Home")
                .releaseDate(LocalDate.of(2019, 7, 3)).build();
        ShoppingMall.Cinema cinema = ShoppingMall.Cinema.builder().name("CGV")
                .openedDate(LocalDate.of(2019, 1, 1))
                .releasedFilms(Arrays.asList(film1, film2)).build();
                
        return ShoppingMall.builder().name("Mall of Indonesia").area(1000)
                .cinema(ShoppingMall.Cinema.builder().name("CGV")
                        .openedDate(LocalDate.of(2019, 1, 1))
                        .releasedFilms(Arrays.asList(ShoppingMall.Cinema.Film
                                .builder().name("Avengers: Endgame")
                                .releaseDate(LocalDate.of(2019, 4, 24)).build(),
                                ShoppingMall.Cinema.Film.builder()
                                        .name("Spiderman: Far From Home")
                                        .releaseDate(LocalDate.of(2019, 7, 3))
                                        .build()))
                        .build())
                .shopCategory(Arrays.asList("Fashion", "Food", "Electronics"))
                .build();
    }
}
