package com.ryzhkov.cafe_vote.test_data;

import com.ryzhkov.cafe_vote.TestMatcher;
import com.ryzhkov.cafe_vote.dto.DishDto;
import com.ryzhkov.cafe_vote.model.Dish;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DishTestData {

    public static final TestMatcher<DishDto> DISH_DTO_MATCHER = TestMatcher.usingEqualsComparator(DishDto.class);

    public static final int DISH_1 = 1;

    public static final Dish dish1 = new Dish(DISH_1, "dish1", LocalDate.now(), 100);
    public static final Dish dish2 = new Dish(DISH_1 + 1, "dish2", LocalDate.now(), 150);
    public static final Dish dish3 = new Dish(DISH_1 + 2, "dish3", LocalDate.now(), 200);
    public static final Dish dish4 = new Dish(DISH_1 + 3, "soup", LocalDate.of(2021, 04, 03), 100);
    public static final Dish dish5 = new Dish(DISH_1 + 4, "desert", LocalDate.of(2021, 04, 03), 125);
    public static final Dish dish6 = new Dish(DISH_1 + 5, "meat", LocalDate.of(2021, 04, 03), 150);
    public static final Dish dish7 = new Dish(DISH_1 + 6, "desert", LocalDate.of(2021, 04, 02), 130);
    public static final Dish dish8 = new Dish(DISH_1 + 7, "soup", LocalDate.of(2021, 04, 02), 50);
    public static final Dish dish9 = new Dish(DISH_1 + 8, "meat", LocalDate.of(2021, 04, 02), 200);
    public static final Dish dish10 = new Dish(
            DISH_1 + 9, "meat", LocalDate.now().minus(1, ChronoUnit.DAYS), 200);
    public static final Dish dish11 = new Dish(
            DISH_1 + 10, "soup", LocalDate.now().minus(1, ChronoUnit.DAYS), 200);
    public static final Dish dish12 = new Dish(
            DISH_1 + 11, "desert", LocalDate.now().minus(1, ChronoUnit.DAYS), 175);

    public static final List<Dish> menu1 = List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9);
    public static final List<Dish> menu2 = List.of(dish12, dish10, dish11);
    public static  List<Dish> menu_2021_04_03 = List.of(dish5, dish6, dish4);

    public static Dish getNew() {
        return new Dish(null, "newDish", LocalDate.now(), 333);
    }

    public static Dish getUpdated() {
        Dish updated = new Dish(dish1);
        updated.setDish("updateDish.com");
        updated.setDate(LocalDate.of(1900, 01, 01));
        updated.setPrice(999);
        return updated;
    }
}
