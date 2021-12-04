package com.ivan.sunnyblog.web.services.iservice;

import com.ivan.sunnyblog.web.ResultInfo;
import org.springframework.web.multipart.MultipartFile;

public interface IResourceService {

    ResultInfo uploadFile(MultipartFile multipartFile);
}
