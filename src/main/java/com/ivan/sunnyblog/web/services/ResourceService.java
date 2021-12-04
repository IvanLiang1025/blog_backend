package com.ivan.sunnyblog.web.services;

import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.res.S3ResourceManager;
import com.ivan.sunnyblog.web.services.iservice.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author: jinghaoliang
 **/

@Service
public class ResourceService extends BaseService implements IResourceService {

    @Autowired
    private S3ResourceManager s3ResourceManager;

    @Override
    public ResultInfo uploadFile(MultipartFile multipartFile) {

        try{
            String url = s3ResourceManager.uploadToS3(multipartFile);

            ResultInfo resultInfo = ResultInfo.getResult();
            resultInfo.setData(url);

            return resultInfo;

        }catch (Exception e){
            logger.error("uploading file exception: {}", e);

        }
        return null;
    }
}
