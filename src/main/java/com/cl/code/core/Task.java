package com.cl.code.core;

import com.cl.code.constant.TaskTypeEnum;
import com.cl.code.property.NodeProperty;

/**
 * 任务
 *
 * @author chengliang
 * @date 2022/9/5 10:51
 */
public interface Task<V extends NodeProperty> {

    /**
     * 类型
     *
     * @return {@code TaskTypeEnum}
     */
    TaskTypeEnum type();

    /**
     * 任务
     *
     * @param nodeDefinition 节点定义
     * @param flow           流
     * @param taskId         taskId
     * @return {@code NodeResult}
     */
    NodeResult task(NodeDefinition<V> nodeDefinition, Flow flow, Long taskId);


}
