package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RestaurantTests {

    @Test
    public void creation() {
        // Restaurant restaurant = new Restaurant(1004L,"Bob zip","Seoul");

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        assertEquals(restaurant.getId(),1004L);
        assertEquals(restaurant.getName(), "Bob zip");
        assertEquals(restaurant.getAddress(),"Seoul");
    }

    @Test
    public void information() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        assertEquals(restaurant.getInformation(), "Bob zip in Seoul");
    }
}