package com.assignment.terminus.service;

import com.assignment.terminus.modal.Users;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IBasicService {
    Users createUser(Users inUser);
    Users getUser(int id);
}
