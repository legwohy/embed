package com.cobra.controller;

import com.cobra.common.Response;
import com.cobra.domain.datamarket.QueryUserInfoDO;
import com.cobra.domain.datamarket.TUserInfo;
import com.cobra.service.datamarket.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController
{
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/query")
    public Response<PageInfo<List<TUserInfo>>> query(@RequestBody QueryUserInfoDO userInfoDO){
        int currentPage = userInfoDO.getCurrentPage() == null ? 1:userInfoDO.getCurrentPage();
        int pageSize = userInfoDO.getPageSize() == null ? 10: userInfoDO.getPageSize();
        PageHelper.startPage(currentPage,pageSize);
        List<TUserInfo> list = userInfoService.selectAll();


        return new Response(list);
    }
}
