package com.cobra.manager.credit;

import com.cobra.common.Response;
import com.cobra.domain.datamarket.CisInterfaceServiceNodeDO;
import com.cobra.domain.datamarket.QueryInterface;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface CisInterfaceServiceNodeService {
    Response<PageInfo<List<CisInterfaceServiceNodeDO>>> selectList(QueryInterface nodeDO);
}
