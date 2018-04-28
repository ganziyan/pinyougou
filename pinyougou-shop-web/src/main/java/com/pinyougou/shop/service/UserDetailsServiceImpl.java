package com.pinyougou.shop.service;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author gan
 * @version 1.0
 * @description com.pinyougou.shop.service UserDetailsService
 * @date 2018/4/27
 */
public class UserDetailsServiceImpl implements UserDetailsService{
    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("经过了UserDetailsServiceImpl");
        System.out.println("从页面传递过来的用户名"+username);
        //从数据库中将用户数据查出来 ----》匹配
        //1.根据传递过来的用户名从数据库中查询该用户名对应的对象 如果对象不存在 返回null
        TbSeller tbSeller = sellerService.findOne(username);
        if(tbSeller==null) {
            return null;
        }
        System.out.println("说明用户是存在的>>"+tbSeller.getName());

        if(!"1".equals(tbSeller.getStatus())){
            //2.如果有用户的对象  还需要验证这个用户是否已经被审核通过  status 1  没有通过 返回null
            return null;
        }

        System.out.println("从数据库中获取到de 密码"+tbSeller.getPassword());

        //3.匹配密码是和前台传递的是一致的。（交个spring security自己实现）
        return new User(username,tbSeller.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_SELLER"));
    }
}
