package com.cobra.controller;

import com.cobra.common.Response;
import com.cobra.domain.datamarket.CisInterfaceServiceNodeDO;
import com.cobra.domain.datamarket.QueryInterface;
import com.cobra.domain.datamarket.QueryProductLog;
import com.cobra.domain.datamarket.TGoodsProductOperaLogDO;
import com.cobra.manager.credit.CisInterfaceServiceNodeService;
import com.cobra.manager.datamarket.ProductOperationLogMgrService;
import com.cobra.manager.datamarket.impl.ProductOperaLogServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private CisInterfaceServiceNodeService cisInterfaceServiceNodeService;

    @Autowired
    private ProductOperationLogMgrService productOperationLogMgrService;

    @PostMapping("/queryNode")
    public Response<PageInfo<List<CisInterfaceServiceNodeDO>>> queryNode(QueryInterface queryInterface){

        return cisInterfaceServiceNodeService.selectList(queryInterface);
    }

    @RequestMapping("/queryLog")
    public Response<PageInfo<List<TGoodsProductOperaLogDO>>> queryLog(QueryProductLog queryProductLog){
        return productOperationLogMgrService.queryProductOperaLogs(queryProductLog);
    }
}
