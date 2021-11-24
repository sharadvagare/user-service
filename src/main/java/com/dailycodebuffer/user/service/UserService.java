package com.dailycodebuffer.user.service;

import com.dailycodebuffer.user.ValueObject.Department;
import com.dailycodebuffer.user.ValueObject.ResponseTemplate;
import com.dailycodebuffer.user.entity.User;
import com.dailycodebuffer.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserService {
    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return  userRepository.save(user);
    }

    public ResponseTemplate getUserWithDepartment(Long userId) {
        ResponseTemplate vo  = new ResponseTemplate();
        User user = userRepository.findByUserId(userId);

        Department department = restTemplate.
                getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDeartmentId()
                ,Department.class);
        vo.setDepartment(department);
        vo.setUser(user);
        return vo;
    }
}
