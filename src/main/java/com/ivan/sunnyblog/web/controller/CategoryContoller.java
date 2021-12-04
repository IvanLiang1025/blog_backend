package com.ivan.sunnyblog.web.controller;

import com.ivan.sunnyblog.base.models.tables.pojos.Category;
import com.ivan.sunnyblog.web.ResultInfo;
import com.ivan.sunnyblog.web.services.iservice.ICategoryService;
import com.ivan.sunnyblog.web.vo.CategoryVo;
import com.ivan.sunnyblog.web.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: jinghaoliang
 * Date: 2021-10-02 11:00 p.m.
 **/

@RestController
@RequestMapping("api")
public class CategoryContoller {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
    public ResultInfo getCategoryList(SearchVo searchVo){
        System.out.println(searchVo);
        ResultInfo resultInfo = categoryService.getCategoryList();
        return resultInfo;
    }

    @RequestMapping(value = "/admin/category", method = RequestMethod.POST)
    public ResultInfo editCategory(@RequestBody CategoryVo categoryVo){
        ResultInfo resultInfo = categoryService.editCategory(categoryVo);
        return resultInfo;
    }

    @RequestMapping(value = "/admin/category", method = RequestMethod.DELETE)
    public ResultInfo deleteCategory(@RequestBody CategoryVo categoryVo) {

//        System.out.println(categoryVo);


        return categoryService.deleteCategory(categoryVo.getCategoryId());
    }


    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResultInfo getCategories(){

        return categoryService.getCategories();
    }
}
