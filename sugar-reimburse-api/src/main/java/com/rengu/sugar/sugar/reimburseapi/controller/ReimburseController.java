package com.rengu.sugar.sugar.reimburseapi.controller;

import com.rengu.sugar.sugar.reimburseapi.Utils.ResultUtils;
import com.rengu.sugar.sugar.reimburseapi.entity.ReimburseEntity;
import com.rengu.sugar.sugar.reimburseapi.entity.ResultEntity;
import com.rengu.sugar.sugar.reimburseapi.service.ReimburseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: XYmar
 * Date: 2018/12/25 9:56
 */
@RestController
@RequestMapping(value = "/reimburse")
public class ReimburseController {
    private final ReimburseService reimburseService;

    @Autowired
    public ReimburseController(ReimburseService reimburseService) {
        this.reimburseService = reimburseService;
    }

    // 保存一条报销记录
    @ApiOperation(value = "保存一条报销记录", notes = "保存一条报销记录")
    @PostMapping(value = "byReimburseFormId/{reimburseFormId}")
    public ResultEntity saveReimburse(@PathVariable(value = "reimburseFormId") String reimburseFormId, ReimburseEntity reimburseEntity) {
        return ResultUtils.build(reimburseService.saveReimburse(reimburseFormId, reimburseEntity));
    }

    // 根据报销单id查询所有报销记录
    @ApiOperation(value = "根据报销单id查询所有报销记录", notes = "根据报销单id查询所有报销记录")
    @GetMapping(value = "/byReimburseFormId/{reimburseFormId}")
    public ResultEntity getReimbursesByReimburseFormId(@PathVariable(value = "reimburseFormId") String reimburseFormId) {
        return ResultUtils.build(reimburseService.getReimbursesByReimburseFormId(reimburseFormId));
    }

    // 根据Id修改某条报销记录
    @ApiOperation(value = "根据Id修改某条报销记录", notes = "根据Id修改某条报销记录")
    @PutMapping(value = "/{reimburseId}")
    public ResultEntity updateReimburseById(@PathVariable(value = "reimburseId") String reimburseId, ReimburseEntity reimburseEntity) {
        return ResultUtils.build(reimburseService.updateReimburseById(reimburseId, reimburseEntity));
    }

    // 根据id删除报销记录
    @ApiOperation(value = "根据id删除报销记录", notes = "根据id删除报销记录")
    @DeleteMapping(value = "/{reimburseId}")
    public ResultEntity deleteReimburseById(@PathVariable(value = "reimburseId") String reimburseId) {
        return ResultUtils.build(reimburseService.deleteReimburseById(reimburseId));
    }
}
