package dev.rrlabs.hellorrlabs.user.api.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {

    @Schema(description = "Email do usuario, este campo deve ser unico")
    private String email;

    @Schema(description = "Nome do usuario, este campo deve ser preenchido")
    private String name;

    @Schema(description = "Sobrenome do usuario, este campo é opcional")
    @JsonProperty("phone_number")
    private String phoneNumber;
}
