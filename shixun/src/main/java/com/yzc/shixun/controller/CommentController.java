package com.yzc.shixun.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzc.shixun.model.RestCMInfos2;
import com.yzc.shixun.dto.CommentInfo;
import com.yzc.shixun.dto.CustInfo;
import com.yzc.shixun.dto.UserInfo;
import com.yzc.shixun.model.RestCMInfos;
import com.yzc.shixun.result.ResultEnum;
import com.yzc.shixun.result.ResultInfo;
import com.yzc.shixun.result.ReturnResult;
import com.yzc.shixun.service.CommentService;
import com.yzc.shixun.service.Custervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    Custervice custervice;

    @PostMapping("/addComment")
    @ResponseBody
    public ReturnResult addComment(String commentType,String sketch,Integer orderId)
    {
        CommentInfo commentInfo=new CommentInfo();
        commentInfo.setCommentInfo(sketch);
        commentInfo.setCommentType(commentType);
        commentInfo.setOrderId(orderId);
        commentInfo.setCommentTime(new Date());

        try {
            boolean b = commentService.addComment(commentInfo);
            return new ReturnResult(true,new ResultInfo(ResultEnum.ADD_COMMENT_SUCCESS));

        } catch (Exception e) {
            return new ReturnResult(new ResultInfo(ResultEnum.ADD_COMMENT_FAIL));
        }

    }

    @GetMapping("/custCommentInfos")
    public String showCustCommentInfos(Model model)
    {
        UserInfo userInfo= (UserInfo) httpServletRequest.getSession().getAttribute("userInfo");
        if(userInfo!=null) {
            CustInfo custInfo = custervice.selectById(userInfo.getId());

            model.addAttribute("custCommentInfos",commentService.getCustCommentInfos(custInfo.getId()));
        }
        return "custCommentInfos";
    }

    @GetMapping("/deleteComment")
    @ResponseBody
    public ReturnResult deleteComment(Integer comment_id)
    {
        try {
            commentService.deleteComment(comment_id);
            return new ReturnResult( true,new ResultInfo(ResultEnum.DELETE_COMMENT_SUCCESS));
        } catch (Exception e) {e.printStackTrace();;
            return new ReturnResult(new ResultInfo(ResultEnum.DELETE_COMMENT_FAIL));
        }
    }


    @GetMapping("/getRestCommentCust")
    @ResponseBody
    public PageInfo getRestCommentToCust(@RequestParam(value = "pn",defaultValue = "1")Integer pageNum, Integer restId)
    {
        PageHelper.startPage(pageNum,5);
        List<RestCMInfos> restCMInfos = commentService.getRestCommentInfos(restId);

        List<RestCMInfos2> restCMInfos2=new ArrayList<>();
        if(restCMInfos!=null) {
            restCMInfos2 = restCMInfos.stream().map((restCMInfo) -> {
                RestCMInfos2 restCMInfos21 = fromRestCMInfo(restCMInfo);
                return restCMInfos21;
            }).collect(Collectors.toList());
        }
        PageInfo pageInfo = new PageInfo(restCMInfos2,5);
        return  pageInfo;
    }

    private String fromDate(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    private RestCMInfos2 fromRestCMInfo(RestCMInfos r)
    {
        RestCMInfos2 restCMInfos2=new RestCMInfos2();
        restCMInfos2.setCommentType(r.getCommentType());
        restCMInfos2.setCommentInfo(r.getCommentInfo());
        restCMInfos2.setCommentTime(fromDate(r.getCommentTime()));
        restCMInfos2.setCustName(r.getCustName());
        return  restCMInfos2;
    }
}
