package com.assignment.terminus.service;

import com.assignment.terminus.modal.Users;
import com.assignment.terminus.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BasicService implements IBasicService{

    @Autowired
    StringRedisTemplate primaryRedisTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public Users createUser(Users inUser)  {
        Users user = null;
        try {
            user = userRepository.save(inUser);
            // redis
            JsonNode jsonNode = mapper.valueToTree(user);
            String jsonStr = mapper.writeValueAsString(jsonNode);
            primaryRedisTemplate.opsForValue().set(String.valueOf(user.getId()), jsonStr);
        }catch (JsonProcessingException js){
            js.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public Users getUser(int id) {
        Users user = null;
        try {
            String userStr = primaryRedisTemplate.opsForValue().get(String.valueOf(id));
            System.out.println("userStr "+userStr);
            if (userStr != null) {
                user = mapper.readValue(userStr, Users.class);
            } else {
                Optional<Users> userOp = userRepository.findById(id);
                if (userOp.isPresent())
                    user = userOp.get();
            }
        }catch (JsonProcessingException js){
            js.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return user;
    }
}
