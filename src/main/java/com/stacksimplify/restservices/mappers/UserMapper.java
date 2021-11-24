package com.stacksimplify.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	
	UserMapper INSATANCE = Mappers.getMapper(UserMapper.class);
	
	//User To UserDto
	//@Mapping(source = "email", target = "emailaddress")
	UserMsDto userToUserMsDto(User u);
	
	//List<User> to List<UserMsDto>
	
	List<UserMsDto> usersToUserDtos(List<User> users);
	
}
