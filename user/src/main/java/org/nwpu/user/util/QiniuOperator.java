package org.nwpu.user.util;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.cdn.CdnManager;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 七牛云操作类,用于向图床上传图片
 * @author lzy
 */
@Service
public class QiniuOperator {

    @Value("${qiniu.access}")
    private String access;

    @Value("${qiniu.secret}")
    private String secret;

    @Value("${qiniu.spaceName}")
    private String spaceName;

    @Value("${qiniu.domain}")
    private String domain;

    private Auth auth;

    /**
     * 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
     */
    private Configuration configuration = new Configuration(Zone.zone2());

    private UploadManager uploadManager = new UploadManager(configuration);

    private CdnManager cdnManager;

    /**
     * 提交图片
     * @param file 图片文件
     * @param fileName 文件名
     * @return 图片url
     * @throws IOException
     */
    public String uploadImage(MultipartFile file, String fileName) throws IOException {
        if(this.auth==null){
            this.auth = Auth.create(access, secret);
            this.cdnManager = new CdnManager(this.auth);
        }
        Response response;
        try{
            response= this.uploadManager.put(file.getBytes(), fileName, this.auth.uploadToken(this.spaceName));
        }catch (Exception e){
            /* 覆盖上传 */
            response= this.uploadManager.put(file.getBytes(), fileName, this.auth.uploadToken(this.spaceName,fileName));
            /* 刷新CDN缓存，七牛云免费帐号每天限额500次 */
            String[] url = new String[]{this.domain + JSONObject.parseObject(response.bodyString()).get("key")};
            this.cdnManager.refreshUrls(url);
        }
        /* 返回这张存储照片的地址 */
        return this.domain + JSONObject.parseObject(response.bodyString()).get("key");
    }
}


