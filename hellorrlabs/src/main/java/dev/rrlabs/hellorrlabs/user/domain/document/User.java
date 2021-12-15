package dev.rrlabs.hellorrlabs.user.domain.document;

import dev.rrlabs.hellorrlabs.user.api.resource.UserResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String email;
    private String name;
    private String phoneNumber;
    private LocalDate birthdate;

    private Map<String, UserResource> map;
}
