package com.yzc.shixun.tool.face;



import com.yzc.shixun.tool.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FaceMatch {

    ///传进来的参数是图片的BASE64数据形式
    public static String match(String image1) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {

            //byte[] bytes1 = FileUtil.readFileByBytes(image1Path);
            byte[] bytes2 = FileUtil.readFileByBytes("./picture/1.jpg");
            //String image1 = Base64Util.encode(bytes1);
            String image2 = Base64Util.encode(bytes2);

            List<Map<String, Object>> images = new ArrayList<>();

            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NORMAL");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NORMAL");

            images.add(map1);
            images.add(map2);

            String param = GsonUtils.toJson(images);
            Auth auth = new Auth();
            String accessToken = auth.getAuth();
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。


            String results = HttpUtil.post(url, accessToken, "application/json", param);
            String result = results.split(",")[5];
            String score="0";
            if (result != null)
            {
                score=result.split(":")[2];
            }
            return score;
        } catch (Exception e) {
        }
        return "0";
    }
}
