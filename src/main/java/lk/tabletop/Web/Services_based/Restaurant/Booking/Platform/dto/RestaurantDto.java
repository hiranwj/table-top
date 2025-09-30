package lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.model.Source;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String city;

    @NotBlank
    @Size(max = 100)
    private String cuisine;

    @NotNull
    private Double rating;

    @Size(max = 255)
    private String address;

    @Size(max = 20)
    private String phone;

    @Size(max = 255)
    private String website;

    private Source source; // ZOMATO, YELP, OPENTABLE, OTHER
}
