package com.cl.code.actuator;

import com.cl.code.FlowEngine;
import com.cl.code.core.*;
import com.cl.code.property.NodeProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 任务节点
 *
 * @author chengliang
 * @date 2022/9/5 9:12
 */
@Component
public abstract class TaskNodeActuator<T extends NodeType<V>, V extends NodeProperty> extends FlowNodeActuator<T, V> implements Task<V> {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long execute(NodeDefinition<V> nodeDefinition, Flow flow) {
        // 创建任务
        Long taskId = FlowEngine.getFlowEngine().getTaskService().createTask(flow.getFlowId(), nodeDefinition.getNodeId(), type());
        return this.task(nodeDefinition, flow, taskId);
    }


}