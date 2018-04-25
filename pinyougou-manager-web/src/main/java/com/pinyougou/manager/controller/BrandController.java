package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gan
 * @version 1.0
 * @description com.pinyougou.manager.controller
 * @date 2018/4/23
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    BrandService brandService;//通过分布式服务框架dubbo,远程注入实现类对象

    @RequestMapping("/findAll")
    public List<TbBrand> findALL() {
        System.out.println("接收到请求...");
        return brandService.findAll();
    }

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return brandService.findPage(page,rows);
    }

    //分页条件查询
    @RequestMapping("/search")
    public  PageResult findPage(@RequestBody TbBrand tbBrand,int page, int rows){
        return brandService.findPage(tbBrand,page,rows);
    }

    //增加商品
    //angularjs 传递的数据是对象类型默认的content-type:applicaton/json;加上@requestBody可将前台传过来得json数据自动转换成对象.
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand tbBrand){
        try {
            System.out.println("接收到save请求... tbBrand"+tbBrand);
            brandService.add(tbBrand);
            return new Result(true,"新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"新增失败");
        }
    }

    //更新1根据id回显
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }
    //更新2
    @RequestMapping("/update")
    public Result update(@RequestBody  TbBrand tbBrand){
        try {
            brandService.update(tbBrand);
            return new Result(true,"更新成功");
        } catch (Exception e) {
            return new Result(false,"更新失败");
        }
    }

    //批量删除
    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            brandService.delete(ids);
            return new Result(true,"更新成功");
        } catch (Exception e) {
            return new Result(false,"更新失败");
        }
    }


}
