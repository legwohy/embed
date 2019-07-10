package com.cobra.controller;

import com.cobra.common.Response;
import com.cobra.domain.credit.QueryBankCardDO;
import com.cobra.domain.credit.TBankCard;
import com.cobra.domain.datamarket.QueryUserInfoDO;
import com.cobra.domain.datamarket.TUserInfo;
import com.cobra.service.credit.BankCardService;
import com.cobra.service.datamarket.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bankCard")
public class BankCardController
{
    @Autowired
    private BankCardService bankCardService;

    @PostMapping("/query")
    public Response<PageInfo<List<TBankCard>>> query(@RequestBody QueryBankCardDO userInfoDO){
        int currentPage = userInfoDO.getCurrentPage() == null ? 1:userInfoDO.getCurrentPage();
        int pageSize = userInfoDO.getPageSize() == null ? 10: userInfoDO.getPageSize();
        PageHelper.startPage(currentPage,pageSize);
        List<TBankCard> list = bankCardService.selectAll();

        return new Response(list);
    }
}
