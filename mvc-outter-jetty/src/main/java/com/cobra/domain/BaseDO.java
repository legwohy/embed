package com.cobra.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: 韩纳威
 * Date: 2018-12-14
 * Time: 15:26
 * Project: credit-open-service
 */

@Getter
@Setter
public class BaseDO {

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String updatedBy;


    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

}
