package com.rengu.sugar.sugaruserapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * Author: XYmar
 * Date: 2018/12/19 13:56
 */
@Data
public class DepartmentEntity {
    private String id = UUID.randomUUID().toString();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime = new Date();
    private String name;
    private String leaderName;
    private String description;
    private Set<UserEntity> userEntities;
}
