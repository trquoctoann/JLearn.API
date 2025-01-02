package com.homie.jlearn.modules.user.mapper;

import com.homie.jlearn.common.EntityMapper;
import com.homie.jlearn.modules.user.User;
import com.homie.jlearn.modules.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserEntity> {}
