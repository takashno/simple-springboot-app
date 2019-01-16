package com.example.simplespringbootapp.domain.mapper;

import com.example.simplespringbootapp.domain.model.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoSelectMapper {

    List<Todo> getTodoList();
}
