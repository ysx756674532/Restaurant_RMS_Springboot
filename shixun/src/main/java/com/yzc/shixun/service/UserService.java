package com.yzc.shixun.service;



import com.yzc.shixun.dao.CustInfoMapper;
import com.yzc.shixun.dao.UserInfoMapper;
import com.yzc.shixun.dto.CustInfo;
import com.yzc.shixun.dto.UserInfo;
import com.yzc.shixun.tool.Base64Tool;
import com.yzc.shixun.tool.face.FaceAdd;
import com.yzc.shixun.tool.face.FaceSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

@Service
public class UserService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    CustInfoMapper custInfoMapper;

    @Transactional
    public boolean insertUserInfoCust(UserInfo userInfo, CustInfo custInfo)
    {
        if(userInfoMapper.insertUserInfo(userInfo)>0&&custInfoMapper.insertCustInfo(custInfo)>0)
        {
            return true;
        }
        return false;
    }

    public UserInfo selectByUP(String username,String password)
    {
        return userInfoMapper.selectByPasswrod(username,password);
    }

    public UserInfo selectByUserName(String username,String userType)
    {
        UserInfo userInfo = userInfoMapper.selectByUserName(username,userType);

        return userInfo;
    }
    public UserInfo selectByFace(String image,String userType)
    {
        String s = FaceSearch.FaceSearch(image);
        String[] results=s.split(",");
        if(results.length<10) return null;

        String score=results[9];
        if(score.compareTo("75")>0)return null;

        Integer userId=Integer.valueOf(results[7].split(":")[1].replaceAll("\"",""));

        UserInfo userInfo=userInfoMapper.selectByUserId(userId,userType);
        return userInfo;
    }
    public boolean selectByEmail(String email)
    {
        UserInfo userInfo = userInfoMapper.selectByEmail(email);
        if(userInfo==null)return false;
        else return true;
    }
    @Transactional
    public boolean saveCustInfo(CustInfo custInfo,UserInfo userInfo,String image) throws Exception {
        userInfo.setUserImage(image);
        if(!image.equals("无"))
        {
            if(Base64Tool.GenerateImage(image, userInfo.getUserName()))
            {
                userInfo.setUserImage(userInfo.getUserName()+".jpg");
            }else return false;
        }
        int i = userInfoMapper.insertUserInfo(userInfo);
        if(i<=0)return false;
        custInfo.setUserId(userInfo.getId());
        int i1 = custInfoMapper.insertCustInfo(custInfo);
        if(i1<=0)throw new Exception();

        boolean add = FaceAdd.add(userInfo.getId(), image);
        if(add==false)throw new Exception();
        return true;
    }
}