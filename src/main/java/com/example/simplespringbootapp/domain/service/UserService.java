package com.example.simplespringbootapp.domain.service;

import com.example.simplespringbootapp.domain.mapper.UserMapper;
import com.example.simplespringbootapp.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.simplespringbootapp.domain.mapper.UserSelectMapper;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    /** Todoマッパー. */
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserSelectMapper userSelectMapper;

    public void registUser(User user){

        if(user != null){
            String uuid = UUID.randomUUID().toString();

            user.setId(uuid);
            userMapper.insert(user);
        }

    }

    public List<User> getUserList(){
        return userSelectMapper.getUserList();
    }

    public String userName(String userId){

        return null;
    }
}
