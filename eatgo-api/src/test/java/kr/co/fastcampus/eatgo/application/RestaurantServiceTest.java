package kr.co.fastcampus.eatgo.application;


import kr.co.fastcampus.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        mockMenuItemRepository();
        restaurantService = new RestaurantService(restaurantRepository,menuItemRepository);
    }



    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .menuItems(new ArrayList<MenuItem>())
                .build();
        restaurants.add(restaurant);
        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
    }

    private void mockMenuItemRepository() {

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder()
                        .name("Kimchi")
                        .build());

        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);

    }

    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertEquals(restaurant.getId(),1004L);
    }

    @Test
    public void getRestaurantsWithExisted() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertEquals(restaurant.getId(),1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertEquals(menuItem.getName(),"Kimchi");
    }

    @Test
    public void getRestaurantsWithNotExisted() {
        assertThatThrownBy(() -> {
           restaurantService.getRestaurant(404L);
        }).isInstanceOf(RestaurantNotFoundException.class);
    }

    @Test
    public void addRestaurant() {

        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Busan")
                .build();

        Restaurant saved = Restaurant.builder()
                .id(1234L)
                .name("BeRyong")
                .address("Busan")
                .build();

        Restaurant created = restaurantService.addRestaurant(restaurant);
        assertEquals(created.getId(),1234L);
    }

    @Test
    public void updateRestaurant() {

        Restaurant restaurant = Restaurant.builder()
            .id(1004L)
            .name("Bob zip")
            .address("Seoul")
            .build();

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

        Restaurant updated = restaurantService.updateRestaurant(1004L,"Sool zip", "Busan");

        assertEquals(updated.getName(),"Sool zip");
        assertEquals(updated.getAddress(),"Busan");

    }
}