package by.itsm.user.service.mapper;

import by.itsm.authentication.dto.SignUpRequest;
import by.itsm.user.dto.UserReadDTO;
import by.itsm.user.dto.UserUpdateDTO;
import by.itsm.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

  User dtoToEntity(SignUpRequest dto);

  UserReadDTO entityToDto(User user);

  void updateEntity(UserUpdateDTO dto, @MappingTarget User user);
}
