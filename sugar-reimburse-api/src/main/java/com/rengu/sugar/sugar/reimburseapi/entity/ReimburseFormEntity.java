package com.rengu.sugar.sugar.reimburseapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * Author: XYmar
 * Date: 2018/12/21 11:22
 */
@Data
public class ReimburseFormEntity {
    private String id = UUID.randomUUID().toString();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime = new Date();

    private String username;
    private double totalPay;
    private String description;
    private String userId;
    private String startTime;
    private String endTime;
    private int isCatered;
    private double benefitNumber;
}
