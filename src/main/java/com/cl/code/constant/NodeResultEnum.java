package com.cl.code.constant;

import com.cl.code.core.NodeResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chengliang
 * @date 2022/9/13 10:43
 */
@Getter
@AllArgsConstructor
public enum NodeResultEnum implements NodeResult {

    /**
     * 完成
     */
    DONE,

    /**
     * 进行中
     */
    ING,

    /**
     * 退回
     */
    RETURN,
    ;


    @Override
    public String result() {
        return super.name();
    }

}
