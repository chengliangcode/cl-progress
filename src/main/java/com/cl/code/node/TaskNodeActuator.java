package com.cl.code.node;

import com.cl.code.Flow;
import com.cl.code.FlowNodeActuator;
import com.cl.code.NodeDefinition;
import com.cl.code.Task;

/**
 * 任务节点
 *
 * @author chengliang
 * @date 2022/9/5 9:12
 */
public abstract class TaskNodeActuator extends FlowNodeActuator implements Task {

    @Override
    public Long execute(NodeDefinition nodeDefinition, Flow flow) {
        this.task(nodeDefinition);
        return nodeDefinition.getOutput();
    }

}