package com.ivan.sunnyblog.web.controller;

import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.services.iservice.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author: jinghaoliang
 **/

@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultInfo upload(@RequestParam("uploadFile") MultipartFile multipartFile){
        return resourceService.uploadFile(multipartFile);
    }
}
