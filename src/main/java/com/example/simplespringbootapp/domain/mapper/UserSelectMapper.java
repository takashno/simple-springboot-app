package com.example.simplespringbootapp.domain.mapper;

import com.example.simplespringbootapp.domain.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserSelectMapper {

    List<User> getUserList();
}
