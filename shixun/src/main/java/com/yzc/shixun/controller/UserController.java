package com.yzc.shixun.controller;

import com.yzc.shixun.dto.CustInfo;
import com.yzc.shixun.dto.UserInfo;
import com.yzc.shixun.result.ResultEnum;
import com.yzc.shixun.result.ResultInfo;
import com.yzc.shixun.result.ReturnResult;
import com.yzc.shixun.service.UserService;
import com.yzc.shixun.tool.MailUtil;
import com.yzc.shixun.tool.face.FaceDetect;
import com.yzc.shixun.tool.face.FaceSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Random;

@Controller
public class UserController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String getIndex()
    {
        return "index";
    }

    @PostMapping("/login")
    @ResponseBody
    public ReturnResult loginByUserName(String username,String password,String userType)
    {
        UserInfo userInfo = userService.selectByUserName(username,userType);

        if(userInfo==null)return new ReturnResult(new ResultInfo(ResultEnum.LOGIN_FAIL_USERNAME));

        if(!userInfo.getPassword().equals(password))
            return new ReturnResult(new ResultInfo(ResultEnum.LOGIN_FAIL_PASSWORD));

        httpServletRequest.getSession().setAttribute("userInfo",userInfo);
        return new ReturnResult(true,new ResultInfo(ResultEnum.LOGIN_SUCCESS));
    }

    @PostMapping("/facelogin")
    @ResponseBody
    public ReturnResult loginByFace(String imgData,String userType){

        String image=imgData.split(",")[1];
        String detect = FaceDetect.detect(image);
        if(detect.equals("0"))return new ReturnResult(new ResultInfo(ResultEnum.FACE_IDENTIFY_DETECT_NOTPEOPLE));
        if(detect.compareTo("1")>0)return new ReturnResult(new ResultInfo(ResultEnum.FACE_IDENTIFY_DETECT_MANYPEOPLE));

        UserInfo userInfo = userService.selectByFace(image,userType);
        if(userInfo==null)return new ReturnResult(new ResultInfo(ResultEnum.FACE_IDENTIFY_SEARCH_FAIL));
        httpServletRequest.getSession().setAttribute("userInfo",userInfo);
        return new ReturnResult(true,new ResultInfo(ResultEnum.FACE_IDENTIFY_SEARCH_SUCCESS));
    }

    @GetMapping("/register")
    public String showRegister()
    {
        return "register";
    }

    @GetMapping("/getCode")
    @ResponseBody
    public ReturnResult getCode(String email)
    {
        boolean b = userService.selectByEmail(email);
        if(b) return new ReturnResult(new ResultInfo(ResultEnum.GET_CODE_FAIL));
        Integer code=new Random().nextInt(899999) + 100000;
        httpServletRequest.getSession().setAttribute("code",code.toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                MailUtil.send(email, code.toString());
            }
        }).start();
        return new ReturnResult(true,new ResultInfo(ResultEnum.GET_CODE_SUCCESS));
    }

    @GetMapping("/verification")
    @ResponseBody
    public ReturnResult verification(String code)
    {
        String codes= (String) httpServletRequest.getSession().getAttribute("code");
        if(code.equals(codes))
        {
            return new ReturnResult(true, new ResultInfo(ResultEnum.VERIFICATION_SUCCESS));
        }else
        {
            return  new ReturnResult(false,new ResultInfo(ResultEnum.VERIFICATION_FAIL));
        }
    }

    @GetMapping("/setCustInfo")
    public String showSetCustInfo(Model model,@RequestParam("email")String email,@RequestParam("userType")String type){
        model.addAttribute("email",email);
        model.addAttribute("userType",type);
        return "setCustInfo";
    }
    @GetMapping("/setRestInfo")
    public String showSetRestInfo(Model model,@RequestParam("email")String email,@RequestParam("userType")String type){
        model.addAttribute("email",email);
        model.addAttribute("userType",type);
        return "setCustInfo";
    }

    @PostMapping("/addImage")
    @ResponseBody
    public ReturnResult getImage(String imgData)
    {
        String image=imgData.split(",")[1];
        String detect = FaceDetect.detect(image);
        if(detect.equals("0"))return new ReturnResult(new ResultInfo(ResultEnum.FACE_IDENTIFY_DETECT_NOTPEOPLE));
        if(detect.compareTo("1")>0)return new ReturnResult(new ResultInfo(ResultEnum.FACE_IDENTIFY_DETECT_MANYPEOPLE));

        httpServletRequest.getSession().setAttribute("imageData",image);
        return new ReturnResult(true,new ResultInfo(ResultEnum.GET_IMAGE));
    }

    @PostMapping("/saveCustInfo")
    @ResponseBody
    public ReturnResult addCustInfo(CustInfo custInfo,UserInfo userInfo)
    {
        Object image=httpServletRequest.getSession().getAttribute("imageData");
        String imageString =image==null?"无":image.toString();

        try {
            userService.saveCustInfo(custInfo,userInfo,imageString);
        } catch (Exception e) {
            e.printStackTrace();;
            if(!imageString.equals("无"))
            {
                File file =new File("D:\\javaWeb\\shixun\\src\\main\\resources\\static\\img\\"+userInfo.getUserName()+".jpg");
                file.delete();
            }
            return new ReturnResult(new ResultInfo(ResultEnum.REGISTER_FAIL));
        }
        return new ReturnResult(true,new ResultInfo(ResultEnum.REGISTER_SUCCESS));
    }

}
