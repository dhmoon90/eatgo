package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RestaurantTests {

    @Test
    public void creation() {
        Restaurant restaurant = new Restaurant(1004L,"Bob zip","");
        assertEquals(restaurant.getName(), "Bob zip");
        assertEquals(restaurant.getAddress(),"Seoul");
    }

    @Test
    public void information() {
        Restaurant restaurant = new Restaurant(1004L,"Bob zip","Seoul");

        assertEquals(restaurant.getInformation(), "Bob zip in Seoul");
    }
}