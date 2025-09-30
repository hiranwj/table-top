package lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponse<T> {
    private int code;
    private String message;
    private T response;
    public StandardResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
