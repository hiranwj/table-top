package lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.service.impl;

import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.dto.RestaurantDto;
import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.model.Restaurant;
import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.model.Source;
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

    @Override
    public ResponseEntity<StandardResponse<?>> addRestaurant(RestaurantDto restaurantDto) {
        // Check for duplicates
        boolean exists = restaurantRepository.existsByNameAndCity(restaurantDto.getName(), restaurantDto.getCity());
        if (exists) {
            StandardResponse<Object> response = new StandardResponse<>(409, "Restaurant already exists");
            return ResponseEntity.status(409).body(response);
        }

        // Map DTO to Entity
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurant.setCity(restaurantDto.getCity());
        restaurant.setCuisine(restaurantDto.getCuisine());
        restaurant.setRating(restaurantDto.getRating());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setPhone(restaurantDto.getPhone());
        restaurant.setWebsite(restaurantDto.getWebsite());
        restaurant.setSource(restaurantDto.getSource() != null ? restaurantDto.getSource() : Source.OTHER);

        // Save entity
        Restaurant saved = restaurantRepository.save(restaurant);

        StandardResponse<Restaurant> response = new StandardResponse<>(
                201,
                "Restaurant added successfully",
                saved
        );

        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<StandardResponse<?>> updateRestaurant(Long id, RestaurantDto restaurantDto) {
        // Fetch restaurant by ID
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        if (restaurant == null) {
            StandardResponse<Object> response = new StandardResponse<>(404, "Restaurant not found");
            return ResponseEntity.status(404).body(response);
        }

        // Optional: Check for duplicates (same name + city but different ID)
        boolean exists = restaurantRepository.existsByNameAndCity(restaurantDto.getName(), restaurantDto.getCity())
                && !restaurant.getId().equals(id);
        if (exists) {
            StandardResponse<Object> response = new StandardResponse<>(409, "Another restaurant with same name and city exists");
            return ResponseEntity.status(409).body(response);
        }

        // Update fields
        restaurant.setName(restaurantDto.getName());
        restaurant.setCity(restaurantDto.getCity());
        restaurant.setCuisine(restaurantDto.getCuisine());
        restaurant.setRating(restaurantDto.getRating());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setPhone(restaurantDto.getPhone());
        restaurant.setWebsite(restaurantDto.getWebsite());
        restaurant.setSource(restaurantDto.getSource() != null ? restaurantDto.getSource() : restaurant.getSource());

        // Save updated restaurant
        Restaurant updated = restaurantRepository.save(restaurant);

        StandardResponse<Restaurant> response = new StandardResponse<>(
                200,
                "Restaurant updated successfully",
                updated
        );

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<StandardResponse<?>> deleteRestaurant(Long id) {
        // Check if the restaurant exists
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        if (restaurant == null) {
            StandardResponse<Object> response = new StandardResponse<>(404, "Restaurant not found");
            return ResponseEntity.status(404).body(response);
        }

        // Delete the restaurant
        restaurantRepository.delete(restaurant);

        StandardResponse<Object> response = new StandardResponse<>(
                200,
                "Restaurant deleted successfully"
        );

        return ResponseEntity.ok(response);
    }

}
