package dev.rrlabs.hellorrlabs.user.api.mapper;

import dev.rrlabs.hellorrlabs.user.api.resource.UserResource;
import dev.rrlabs.hellorrlabs.user.domain.document.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({@Mapping(source = "email", target = "email"), @Mapping(source = "phoneNumber", target = "phoneNumber")})
    User parse(UserResource resource);

    UserResource parse(User resource);

    List<UserResource> parse(List<User> users);

}
