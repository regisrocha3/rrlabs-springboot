package dev.rrlabs.hellorrlabs.user.api.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {

    @Email(message = "E-mail está com formato incorreto")
    @Schema(description = "Email do usuario, este campo deve ser unico")
    private String email;

    @Size(max = 100, min = 3, message = "O campo nome deve conter entre 3 e 100 caracteres")
    @NotEmpty(message = "O campo nome nao pode ser vazio")
    @NotNull(message = "O campo nome nao pode ser branco")
    @Schema(description = "Nome do usuario, este campo deve ser preenchido")
    private String name;

    @Schema(description = "Sobrenome do usuario, este campo é opcional")
    @JsonProperty("phone_number")
    private String phoneNumber;

    //@JsonFormat(pattern = "dd/MM/yyyy") // NOTE: Use esta anotacao caso queira receber a data em um formato especifico
    private LocalDate birthdate;

}
