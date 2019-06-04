package com.example.kele.controller;

import com.example.kele.entity.AddressEntity;
import com.example.kele.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author X1 Carbon
 * @TIME 2019/6/4 10:47
 * kele-practise & com.example.kele.controller
 */
@RestController
@Slf4j
public class AddressController {

    @Autowired
    private AddressRepository repository;

    @GetMapping("/testaddress")
    public Object test() {

        AddressEntity addressEntity = new AddressEntity(null, "大名路", "中国", null, null);

        repository.save(addressEntity);

        log.info("==>{}", addressEntity);

        return addressEntity;
    }

}
