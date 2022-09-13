package com.cl.code.core;

import com.cl.code.constant.NodeResultEnum;

/**
 * @author chengliang
 * @date 2022/9/13 10:49
 */
public interface NodeResult {

    /**
     * 结果
     *
     * @return {@code String}
     */
    default String result() {
        return NodeResultEnum.DONE.result();
    }

}
