package com.cobra.domain.datamarket;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author leigang
 * @description:日志记录
 */
@Data
@NoArgsConstructor
public class TGoodsProductOperaLogDO implements Serializable {
    private Long id;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;

    private String logId;

    private String productId;

    private String operatorObject;

    private String operatorType;

}