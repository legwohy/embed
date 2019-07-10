package com.cobra.service.datamarket;

import com.cobra.common.Response;
import com.cobra.domain.datamarket.QueryProductLog;
import com.cobra.domain.datamarket.TGoodsProductOperaLogDO;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface ProductOperationLogMgrService {


    Response<PageInfo<List<TGoodsProductOperaLogDO>>> queryProductOperaLogs(QueryProductLog var1);
}
