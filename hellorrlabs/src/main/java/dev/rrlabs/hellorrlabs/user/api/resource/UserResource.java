package dev.rrlabs.hellorrlabs.user.api.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private String email;
    private String name;
    @JsonProperty("phone_number")
    private String phoneNumber;
}
