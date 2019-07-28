package com.yzc.shixun.service;

import com.yzc.shixun.dao.CustInfoMapper;
import com.yzc.shixun.dto.CustInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Custervice {

    @Autowired
    CustInfoMapper custInfoMapper;

    public CustInfo selectById(Integer id)
    {
        return custInfoMapper.selectByUserId(id);
    }
}
