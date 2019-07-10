package com.cobra.service.credit.impl;

import com.cobra.dao.credit.TBankCardMapper;
import com.cobra.domain.credit.TBankCard;
import com.cobra.service.BaseServiceImpl;
import com.cobra.service.credit.BankCardService;
import com.cobra.util.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankCardServiceImpl extends BaseServiceImpl<TBankCard> implements BankCardService
{
    @Autowired
    @Override
    public void setBaseDao(BaseDao<TBankCard> bankCardBaseDao)
    {
        super.setBaseDao(bankCardBaseDao);
    }

}
