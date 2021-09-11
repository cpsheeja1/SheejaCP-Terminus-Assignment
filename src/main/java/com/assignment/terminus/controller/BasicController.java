package com.assignment.terminus.controller;

import com.assignment.terminus.modal.Users;
import com.assignment.terminus.response.APIResponse;
import com.assignment.terminus.service.IBasicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api
@RestController
@RequestMapping("/api/v1")
public class BasicController {

    @Autowired
    IBasicService iBasicService;

    @GetMapping("/user")
    public APIResponse getUser(@RequestParam int id){
        Users user = this.iBasicService.getUser(id);
        return new APIResponse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
                HttpStatus.OK.value(), "User data received successfully ", user);
    }

    @PostMapping("/user")
    public APIResponse createUser(@RequestBody Users inUser){
        Users user = null;
        try {
            user = this.iBasicService.createUser(inUser);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        if(user != null)
            return new APIResponse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
                HttpStatus.OK.value(), "User creation successful ", user);
        else
            return new APIResponse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown error");// TODO: handle in excepion handler
    }


}
