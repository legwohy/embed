package com.cobra.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页DO
 */
@Data
public class PageDO implements Serializable {
    private static final long serialVersionUID = -7308355946054216024L;
    /** 当前页显示的数量*/
    private Integer pageSize;
    /** 当前页*/
    private Integer currentPage;
}
