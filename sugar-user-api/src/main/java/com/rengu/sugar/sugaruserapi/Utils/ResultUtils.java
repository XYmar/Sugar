package com.rengu.sugar.sugaruserapi.Utils;

import com.rengu.sugar.sugaruserapi.entity.ResultEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Author: XYmar
 * Date: 2018/12/11 14:42
 */
@Slf4j
public class ResultUtils {

    public static ResultEntity<Object> build(Object data) {
        ResultEntity<Object> resultEntity = new ResultEntity<>();
        resultEntity.setData(data);
        return resultEntity;
    }
}
