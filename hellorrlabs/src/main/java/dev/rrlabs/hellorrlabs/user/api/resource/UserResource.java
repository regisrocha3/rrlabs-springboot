package dev.rrlabs.hellorrlabs.user.api.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {

    @Email(message = "E-mail está com formato incorreto")
    @Schema(description = "Email do usuario, este campo deve ser unico")
    private String email;

    @Size(max = 8, min = 3, message = "O campo nome deve conter entre 3 e 8 caracteres")
    @NotEmpty(message = "O campo nome nao pode ser vazio")
    @NotNull(message = "O campo nome nao pode ser branco")
    @Schema(description = "Nome do usuario, este campo deve ser preenchido")
    private String name;

    @Schema(description = "Sobrenome do usuario, este campo é opcional")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @Min(message = "Valor minimo deve ser 5", value = 5)
    @Max(message = "Valor maximo deve ser 10", value = 10)
    private Integer number;

    @DecimalMin(value = "0.15", message = "Valor minimo deve ser 0.15")
    @DecimalMax(value = "1.15", message = "Valor maximo deve ser 1.15")
    private BigDecimal amount;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

}
