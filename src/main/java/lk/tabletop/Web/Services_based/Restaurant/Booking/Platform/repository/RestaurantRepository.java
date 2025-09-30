package lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.repository;

import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // Custom search query with optional parameters
    List<Restaurant> findByCityContainingIgnoreCaseAndCuisineContainingIgnoreCaseAndRatingGreaterThanEqual(
        String city, String cuisine, Double rating
    );
}
