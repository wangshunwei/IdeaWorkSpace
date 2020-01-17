package com.junit.controller;

import com.junit.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/junitTest")
public class DateController {

    @Autowired
    private DateService dateService;

    @RequestMapping(value = "/test" ,method= RequestMethod.GET)
    public List<Map<String, Object>> getList() {
        return dateService.getList();
    }


}
