package com.rengu.sugar.sugar.reimburseapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * Author: XYmar
 * Date: 2018/12/20 17:59
 */
@Data
public class ReimburseEntity {
    private String id = UUID.randomUUID().toString();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime = new Date();

    private String departmentName; //部门名称
    private String type;    // 类别
    private String forUsage;   // 用途
    private double money;   // 金额
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date useTime;   // 使用日期
    private int days;    //  出差天数
    private String description;    // 备注

    private String userId;    // 用户id

}
