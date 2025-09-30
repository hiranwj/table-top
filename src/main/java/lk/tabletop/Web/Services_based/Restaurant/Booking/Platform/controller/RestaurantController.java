package lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.controller;

import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.service.RestaurantService;
import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse<?>> getRestaurantDetails(@PathVariable Long id) {
        return restaurantService.getRestaurantDetails(id);
    }


}
