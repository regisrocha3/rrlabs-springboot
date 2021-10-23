package dev.rrlabs.hellorrlabs.user.api;

import dev.rrlabs.hellorrlabs.user.api.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import java.util.List;

@Tag(name = "Usuario", description = "API responsavel por realizar cadastro de usuario")
@RequestMapping("/user")
public interface UserApi {

    @Operation(summary = "Criacao de usuario", description = "API para realizar a criacao de usuario, ....")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro ao criar o usuario")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> create(@RequestBody UserResource resource);

    @Operation(summary = "Consulta de usuario por email", description = "Consulta de usuario por email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso", content = @Content(schema = @Schema(implementation = UserResource.class))),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro ao consultar o usuario")
    })
    @GetMapping("/{email}")
    ResponseEntity<UserResource> findById(@PathVariable("email") String email);

    @Operation(summary = "Consulta de usuario", description = "Consulta de usuario de acordo com os filtros aplicados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso", content = @Content(schema = @Schema(implementation = UserResource.class))),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro ao consultar o usuario")
    })
    @GetMapping
    ResponseEntity<List<UserResource>> find(UserResource filter);

    @Operation(summary = "Atualizacao de usuario", description = "API resposanvel por atualizar as informacoes de um usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro ao atualizar o usuario")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> update(@RequestBody UserResource resource);

    @Operation(summary = "Remocao de usuario", description = "API remover um determinado usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario removido com sucesso", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro ao remover o usuario")
    })
    @DeleteMapping("/{email}")
    ResponseEntity<UserResource> delete(@PathVariable("email") String email);

}
