package com.cobra.dao.credit;

import com.cobra.domain.datamarket.CisInterfaceServiceNodeDO;

import java.util.List;

public interface CisInterfaceServiceNodeMapper
{
    int deleteByPrimaryKey(Long umId);

    int insert(CisInterfaceServiceNodeDO record);

    int insertSelective(CisInterfaceServiceNodeDO record);

    CisInterfaceServiceNodeDO selectByPrimaryKey(Long umId);

    int updateByPrimaryKeySelective(CisInterfaceServiceNodeDO record);

    int updateByPrimaryKey(CisInterfaceServiceNodeDO record);

    List<CisInterfaceServiceNodeDO> selectList(CisInterfaceServiceNodeDO umId);



}