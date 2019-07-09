package com.cobra.domain.datamarket;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CisInterfaceServiceNodeDO implements Serializable {
    private static final long serialVersionUID = 9130061866231669875L;
    /**
     * 无意义自增主键
     */
    private Long umId;

    /**
     * 序列值
     */
    private String uuid;

    /**
     * 父节点id
     */
    private String parentUuid;

    /**
     * 数据源机构id
     */
    private String orgId;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 节点url
     */
    private String nodeUrl;

    /**
     * 节点code
     */
    private String nodeCode;

    /**
     * 节点属性
     */
    private String nodeAttr;

    /**
     * 节点价格
     */
    private Integer nodePrice;

    /**
     * 是否为叶子节点0为叶子1为分叉
     */
    private Integer isLeaf;

    /**
     * 是否可用：0:可用 1：禁用
     */
    private Integer isAvaiable;

    /**
     * 0：使用方，1：数据源
     */
    private Integer nodeType;

    /**
     * 过期天数(数据源,0存入即无效，Integer最大值为永久有效)
     */
    private Integer expireDay;

    /**
     * 备注或描述
     */
    private String remark;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 审核人
     */
    private String auditor;

    /**
     * 14位审核时间
     */
    private Date auditTime;

    /**
     * 审核状态：00 待审核 01 审核通过 02 审核不通过
     */
    private String auditStatus;

    /**
     * 是否有效
     */
    private Byte isValid;

    /**
     * 预留字段16
     */
    private String reserved1;

    /**
     * 预留字段2
     */
    private String reserved2;

    /**
     * 预留字段3
     */
    private String reserved3;

    /**
     * 新增时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date updatedTime;

    /**
     * 是否在页面显示:0显示，1隐藏
     */
    private Integer isHidden;

}