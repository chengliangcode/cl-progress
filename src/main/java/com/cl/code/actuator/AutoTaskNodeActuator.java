package com.cl.code.actuator;

import com.cl.code.core.Flow;
import com.cl.code.FlowEngine;
import com.cl.code.core.NodeDefinition;
import com.cl.code.core.NodeType;
import com.cl.code.constant.TaskStatusEnum;
import com.cl.code.constant.TaskTypeEnum;
import com.cl.code.property.NodeProperty;
import org.springframework.stereotype.Component;

/**
 * 自动任务节点
 *
 * @author chengliang
 * @date 2022/9/5 10:43
 */
@Component
public abstract class AutoTaskNodeActuator<T extends NodeType<V>, V extends NodeProperty> extends TaskNodeActuator<T, V> {

    @Override
    public final TaskTypeEnum type() {
        return TaskTypeEnum.AUTO;
    }

    @Override
    public Long task(NodeDefinition<V> nodeDefinition, Flow flow, Long taskId) {
        this.autoTask(nodeDefinition);
        FlowEngine.getFlowEngine().getTaskService().updateTask(flow.getFlowId(), nodeDefinition.getNodeId(), taskId, TaskStatusEnum.DONE);
        return nodeDefinition.getOutput();
    }

    /**
     * 自动任务
     *
     * @param nodeDefinition 节点定义
     */
    public abstract void autoTask(NodeDefinition<V> nodeDefinition);

}
