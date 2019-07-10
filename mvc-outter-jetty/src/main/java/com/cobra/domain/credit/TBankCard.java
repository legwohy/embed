package com.cobra.domain.credit;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class TBankCard {
    @Id private Integer id;

    private String cardName;

    private String cardCode;

    private Date createTime;


}