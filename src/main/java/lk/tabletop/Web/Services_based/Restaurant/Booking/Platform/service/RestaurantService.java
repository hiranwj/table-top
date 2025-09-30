package lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.service;

import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.util.StandardResponse;
import org.springframework.http.ResponseEntity;

public interface RestaurantService {
    ResponseEntity<StandardResponse<?>> searchRestaurants(String city, String cuisine, Double minRating);

    ResponseEntity<StandardResponse<?>> getRestaurantDetails(Long id);

}
