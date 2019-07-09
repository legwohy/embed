package com.cobra.manager.credit.impl;

import com.cobra.common.Response;
import com.cobra.dao.credit.CisInterfaceServiceNodeMapper;
import com.cobra.domain.datamarket.QueryInterface;
import com.cobra.manager.credit.CisInterfaceServiceNodeService;
import com.cobra.domain.datamarket.CisInterfaceServiceNodeDO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class CisInterfaceServiceNodeServiceImpl implements CisInterfaceServiceNodeService {

    @Autowired
    private CisInterfaceServiceNodeMapper cisInterfaceServiceNodeMapper;


    @Override
    public Response<PageInfo<List<CisInterfaceServiceNodeDO>>> selectList(QueryInterface queryInterface) {
        int currentPage = queryInterface.getCurrentPage() == null ? 1:queryInterface.getCurrentPage();
        int pageSize = queryInterface.getPageSize() == null ? 1: queryInterface.getPageSize();
        PageHelper.startPage(currentPage,pageSize);
        CisInterfaceServiceNodeDO cisInterfaceServiceNodeDO = new CisInterfaceServiceNodeDO();
        PageInfo<CisInterfaceServiceNodeDO> pageInfo = new PageInfo<>(cisInterfaceServiceNodeMapper.selectList(cisInterfaceServiceNodeDO));

        return new Response(pageInfo);
    }
}
