package com.ivan.sunnyblog.web.services.iservice;

import com.ivan.sunnyblog.base.models.tables.pojos.BlogUser;
import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.vo.LoginVo;
import org.springframework.stereotype.Service;

public interface IBlogUserService {

     ResultInfo login(LoginVo loginVo);


     BlogUser findUserById(Long id);
}
