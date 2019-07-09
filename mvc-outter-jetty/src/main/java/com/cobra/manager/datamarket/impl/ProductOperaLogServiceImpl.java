package com.cobra.manager.datamarket.impl;

import com.cobra.common.Response;
import com.cobra.domain.datamarket.CisInterfaceServiceNodeDO;
import com.cobra.domain.datamarket.QueryProductLog;
import com.cobra.manager.datamarket.ProductOperationLogMgrService;
import com.cobra.dao.datamarket.TGoodsProductOperaLogMapper;
import com.cobra.domain.datamarket.TGoodsProductOperaLogDO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ProductOperaLogServiceImpl implements ProductOperationLogMgrService {
    @Autowired
    private TGoodsProductOperaLogMapper productOperaLogMapper;



    @Override
    public Response<PageInfo<List<TGoodsProductOperaLogDO>>> queryProductOperaLogs(QueryProductLog productOperaLogDTO)
    {
        int currentPage = productOperaLogDTO.getCurrentPage() == null ? 1:productOperaLogDTO.getCurrentPage();
        int pageSize = productOperaLogDTO.getPageSize() == null ? 1: productOperaLogDTO.getPageSize();
        PageHelper.startPage(currentPage,pageSize);

        TGoodsProductOperaLogDO operaLogDO = new TGoodsProductOperaLogDO();
        PageInfo<TGoodsProductOperaLogDO> pageInfo = new PageInfo<>(productOperaLogMapper.selectProductOperaList(operaLogDO));


        return new Response(pageInfo);
    }

}
