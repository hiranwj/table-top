package lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.controller;

import jakarta.validation.Valid;
import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.dto.RestaurantDto;
import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.service.RestaurantService;
import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(path = "/searchRestaurant")
    public ResponseEntity<StandardResponse<?>> searchRestaurants(@RequestParam(required = false) String city,
                                                                 @RequestParam(required = false) String cuisine,
                                                                 @RequestParam(required = false) Double rating) {
        return restaurantService.searchRestaurants(city, cuisine, rating);
    }

    @GetMapping("/restaurantDetail")
    public ResponseEntity<StandardResponse<?>> getRestaurantDetails(@RequestParam Long id) {
        return restaurantService.getRestaurantDetails(id);
    }

    @PostMapping("/admin/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse<?>> addRestaurant(@RequestBody @Valid RestaurantDto restaurantDto) {
        return restaurantService.addRestaurant(restaurantDto);
    }

}
