package com.cobra.domain.datamarket;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class TUserInfo {
    @Id private Integer id;

    private String userId;

    private String userName;

    private Date createdAt;


}