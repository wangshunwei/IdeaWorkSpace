package com.junit.service;

import com.junit.dao.TbAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DateService {


    @Autowired
    TbAddressMapper tbAddressMapper;

    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> maps = tbAddressMapper.selectList();
        return maps;
    }

}
