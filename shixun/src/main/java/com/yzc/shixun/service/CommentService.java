package com.yzc.shixun.service;

import com.yzc.shixun.dao.CommentMapper;
import com.yzc.shixun.dao.RestInfoMapper;
import com.yzc.shixun.dto.CommentInfo;
import com.yzc.shixun.model.CustCMInfos;
import com.yzc.shixun.model.RestCMInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    OrderService orderService;

    @Autowired
    RestInfoMapper restInfoMapper;

    @Transactional
    public boolean addComment(CommentInfo commentInfo) throws Exception {
        int i = commentMapper.insertComment(commentInfo);
        if(i<=0)throw new Exception();
        boolean inComment = orderService.setState(commentInfo.getOrderId(), "已评论");
        if(!inComment)throw new Exception();
        boolean b = setRestPraiseRate(commentInfo.getOrderId());
        if(b)return true;
        else throw new Exception();
    }

    public List<CustCMInfos> getCustCommentInfos(Integer custId)
    {
        List<CustCMInfos> custCMInfos = commentMapper.selectInfosCust(custId);

        if(custCMInfos.size()==0)return null;
        return custCMInfos;
    }

    @Transactional
    public int deleteComment(Integer comment_id) throws Exception {
        int orderId = commentMapper.selectByCommentId(comment_id);
        int delete = commentMapper.delete(comment_id);
        if(delete<=0)throw new Exception();
        boolean b = setRestPraiseRate(orderId);
        if (b)return 1;
        else throw new Exception();
    }

    public List<RestCMInfos> getRestCommentInfos(Integer restId)
    {
        List<RestCMInfos> restCMInfos = commentMapper.selectInfosRest(restId);
        return restCMInfos;
    }
    public boolean setRestPraiseRate(Integer orderId)
    {
        Integer restId=orderService.getRestById(orderId);
        int total=0;
        int goodCount=0;
        String pate="0%";
        List<RestCMInfos> restCommentInfos = getRestCommentInfos(restId);
        if(restCommentInfos==null)pate="暂无评论";
        else {
            for (RestCMInfos restCMInfos : restCommentInfos) {
                if (restCMInfos.getCommentType().equals("好评")) goodCount++;
                total++;
            }
            DecimalFormat decimalFormat = new DecimalFormat(".00");
            if (goodCount != 0)
                pate = decimalFormat.format((goodCount / (float) total) * 100) + "%";
        }
        int count = restInfoMapper.UpdateP(pate, restId);
        if(count>0)return true;
        else return false;
    }
}

