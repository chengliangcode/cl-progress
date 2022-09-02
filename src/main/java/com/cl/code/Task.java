package com.cl.code;

/**
 * @author chengliang
 * @date 2022/9/1 10:48
 */
public interface Task {

    /**
     * 执行
     *
     * @param flowId 流id
     * @param flow flow
     * @return {@code Long}
     */
    Long execute(Long flowId, Flow flow);

}
