package com.cobra.service.datamarket.impl;

import com.cobra.domain.datamarket.TUserInfo;
import com.cobra.service.BaseServiceImpl;
import com.cobra.service.datamarket.UserInfoService;
import com.cobra.util.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserInfoServiceImpl extends BaseServiceImpl<TUserInfo> implements UserInfoService
{
    @Autowired
    @Override
    public void setBaseDao(BaseDao<TUserInfo> userInfoBaseDao)
    {
        super.setBaseDao(userInfoBaseDao);
    }

}
