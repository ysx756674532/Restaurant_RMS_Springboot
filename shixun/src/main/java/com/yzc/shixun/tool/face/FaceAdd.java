package com.yzc.shixun.tool.face;

import com.yzc.shixun.tool.Auth;
import com.yzc.shixun.tool.GsonUtils;
import com.yzc.shixun.tool.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class FaceAdd {

    public static boolean add(Integer uid,String image)
    {
        String url="https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try{
            Map<String,String> options=new HashMap<>();
            options.put("image",image);
            options.put("image_type","BASE64");
            options.put("user_id",uid.toString());
            options.put("group_id","Face_Login");

            String param = GsonUtils.toJson(options);
            Auth auth=new Auth();
            String accessToken = auth.getAuth();

            String results = HttpUtil.post(url, accessToken, "application/json", param);
            String result=results.split(",")[5];
            if(result==null)
            {
                return false;
            }
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
