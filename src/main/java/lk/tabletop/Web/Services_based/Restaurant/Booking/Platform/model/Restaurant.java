package lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String cuisine;
    private Double rating;
    private String address;
    private String phone;
    private String website;

    @Enumerated(EnumType.STRING)
    private Source source; // ZOMATO, YELP, OPENTABLE, OTHER

    private LocalDateTime createdAt = LocalDateTime.now();
}
