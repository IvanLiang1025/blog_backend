package com.ivan.sunnyblog.web.services.iservice;


import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.vo.CategoryVo;

public interface ICategoryService {

    ResultInfo getCategoryList();

    ResultInfo editCategory(CategoryVo categoryVo);

    ResultInfo deleteCategory(Long id);

    ResultInfo getCategories();
}
