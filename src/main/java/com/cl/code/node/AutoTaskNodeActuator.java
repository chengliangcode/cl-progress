package com.cl.code.node;

import com.cl.code.NodeDefinition;

/**
 * 自动任务节点
 *
 * @author chengliang
 * @date 2022/9/5 10:43
 */
public abstract class AutoTaskNodeActuator extends TaskNodeActuator {

    @Override
    public void task(NodeDefinition nodeDefinition) {

    }

    /**
     * 自动任务
     */
    public abstract void autoTask();

}
