package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;

/**
 * @author gan
 * @version 1.0
 * @description com.pinyougou.sellergoods.service
 * @date 2018/4/23
 */
public interface BrandService {

   List<TbBrand> findAll();

   //分页查询
   PageResult findPage(int pageNum, int pageSize);

   //分页条件查询
   PageResult findPage(TbBrand tbBrand,int pageNum, int pageSize);

   //增加品牌
   void add(TbBrand tbBrand);

   //更新 1findById
   TbBrand findOne(Long id);
   //更新 2update
   void update(TbBrand tbBrand);

   //删除
   void delete(Long[] ids);
}
