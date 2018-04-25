package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author gan
 * @version 1.0
 * @description com.pinyougou.sellergoods.service.impl
 * @date 2018/4/23
 */
@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        // PageHelper为MyBatis分页插件,使用方法:
        //1首先设置分页的条件,前台传过来的两个参数:当前页码与每页显示多少条数据
        PageHelper.startPage(pageNum,pageSize);
        //2紧跟着分页条件的第一个查询语句才会得出分页的结果
      // List<TbBrand> tbBrands = tbBrandMapper.selectByExample(null);   //得出的分页list还需要转换成Page类型,以便得出总数据条数
       // Page<TbBrand> page = (Page<TbBrand>) tbBrands;
        List<TbBrand> tbBrands = tbBrandMapper.selectByExample(null);
        Page page = (Page) tbBrands;
        return new PageResult(page.getTotal(),page.getResult());
    }

    //分页条件查询
    @Override
    public PageResult findPage(TbBrand tbBrand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if(tbBrand != null){
            if(tbBrand.getName() != null && tbBrand.getName().length() >0){
                //criteria.andNameLike("%"+brand.getName()+"%");
                criteria.andNameLike("%"+tbBrand.getName()+"%");
            }
            if(tbBrand.getFirstChar() != null && tbBrand.getFirstChar().length() >0){
                criteria.andFirstCharEqualTo(tbBrand.getFirstChar());
            }
        }

        List<TbBrand> tbBrands = tbBrandMapper.selectByExample(example);
        Page page = (Page) tbBrands;
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }


    @Override
    public void update(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKey(tbBrand);
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id : ids){
            tbBrandMapper.deleteByPrimaryKey(id);
        }
    }


}
