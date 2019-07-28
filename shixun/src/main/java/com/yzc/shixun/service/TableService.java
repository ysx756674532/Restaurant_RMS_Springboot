package com.yzc.shixun.service;

import com.yzc.shixun.dao.TableMapper;
import com.yzc.shixun.dto.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TableService {

    @Autowired
    TableMapper tableMapper;

    public Integer getTableIdByTime(Integer restId, Date time,Integer peopleNum)
    {
        List<TableInfo> tableInfos = tableMapper.selectListByTime(restId, new Timestamp(time.getTime()));
        for(TableInfo tableInfo:tableInfos)
        {
            if(tableInfo.getTableCapa()>=peopleNum)
            {
                return tableInfo.getId();
            }
        }
        return -1;
    }

    public List<TableInfo> getList(Integer restId)
    {
        return tableMapper.selectListById(restId);
    }

    public boolean addTable(TableInfo tableInfo)
    {
        int i = tableMapper.insertTableInfo(tableInfo);
        if(i>0)return true;
        else return false;
    }
}
