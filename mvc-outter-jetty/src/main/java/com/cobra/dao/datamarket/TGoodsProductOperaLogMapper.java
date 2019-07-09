package com.cobra.dao.datamarket;

import com.cobra.domain.datamarket.TGoodsProductOperaLogDO;

import java.util.List;

public interface TGoodsProductOperaLogMapper
{
    int deleteByPrimaryKey(Long id);

    int insert(TGoodsProductOperaLogDO record);

    int insertSelective(TGoodsProductOperaLogDO record);

    TGoodsProductOperaLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TGoodsProductOperaLogDO record);

    int updateByPrimaryKey(TGoodsProductOperaLogDO record);

    /** 批量插入*/
    int insertProductOperaLogBatch(List<TGoodsProductOperaLogDO> TGoodsProductOperaLogDOList);

    /** 查询操作日志*/
    List<TGoodsProductOperaLogDO> selectProductOperaList(TGoodsProductOperaLogDO TGoodsProductOperaLogDO);

    int insertProductOperateLog(TGoodsProductOperaLogDO record);
}