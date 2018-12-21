package com.rengu.sugar.sugarreimburseservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

/**
 * Author: XYmar
 * Date: 2018/12/21 10:52
 */
@Entity
@Data
public class ReimburseFormEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime = new Date();

    private String username;
    private double totalPay;
    private String description;
    private String userId;
}
