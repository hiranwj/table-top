package lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.service.impl;

import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.model.Restaurant;
import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.repository.RestaurantRepository;
import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.service.RestaurantService;
import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public ResponseEntity<StandardResponse<?>> searchRestaurants(String city, String cuisine, Double minRating) {

        if (!StringUtils.hasText(city)) city = "";
        if (!StringUtils.hasText(cuisine)) cuisine = "";
        if (minRating == null) minRating = 0.0;

        List<Restaurant> restaurants = restaurantRepository
                .findByCityContainingIgnoreCaseAndCuisineContainingIgnoreCaseAndRatingGreaterThanEqual(
                        city, cuisine, minRating
                );

        StandardResponse<List<Restaurant>> response = new StandardResponse<>(
                200,
                "Restaurants fetched successfully",
                restaurants
        );

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<StandardResponse<?>> getRestaurantDetails(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        if (restaurant == null) {
            StandardResponse<Object> response = new StandardResponse<>(404, "Restaurant not found");
            return ResponseEntity.status(404).body(response);
        }

        StandardResponse<Restaurant> response = new StandardResponse<>(
                200,
                "Restaurant details fetched successfully",
                restaurant
        );

        return ResponseEntity.ok(response);
    }
}
