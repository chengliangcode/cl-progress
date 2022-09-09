package com.cl.code.actuator;

import com.cl.code.core.Flow;
import com.cl.code.FlowEngine;
import com.cl.code.core.NodeDefinition;
import com.cl.code.core.NodeType;
import com.cl.code.constant.TaskStatusEnum;
import com.cl.code.constant.TaskTypeEnum;
import com.cl.code.operation.GrantedOperation;
import com.cl.code.property.NodeProperty;
import com.cl.code.core.TaskService;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 操作任务节点
 *
 * @author chengliang
 * @date 2022/9/5 9:59
 */
@Component
public abstract class OperationTaskNodeActuator<T extends NodeType<V>, V extends NodeProperty> extends TaskNodeActuator<T, V> {
    @Override
    public TaskTypeEnum type() {
        return TaskTypeEnum.MANUAL;
    }

    @Override
    public Long task(NodeDefinition<V> nodeDefinition, Flow flow, Long taskId) {
        // 创建并发布任务
        pushTask(nodeDefinition, taskId);
        return null;
    }

    public Long operationTrigger(NodeDefinition<V> nodeDefinition, Flow flow, Long taskId, GrantedOperation grantedOperation) {
        // 是否支持该操作
        boolean support = operationDefinition().stream().anyMatch(item -> item.getOperation().equals(grantedOperation.getOperation()));
        if (support) {
            // 查找 task
            TaskService taskService = FlowEngine.getFlowEngine().getTaskService();
            boolean executable = taskService.executableTask(flow.getFlowId(), nodeDefinition.getNodeId(), taskId);
            if (executable) {
                TaskStatusEnum taskStatus = operationTrigger(nodeDefinition, flow, grantedOperation);
                // 更新 task 状态
                taskService.updateTask(flow.getFlowId(), nodeDefinition.getNodeId(), taskId, taskStatus);
                return nodeDefinition.getOutput();
            }
        }
        throw new RuntimeException("执行任务失败");
    }

    /**
     * 操作触发
     *
     * @param nodeDefinition   节点定义
     * @param flow             流
     * @param grantedOperation 授予操作
     * @return {@code Long}
     */
    protected abstract TaskStatusEnum operationTrigger(NodeDefinition<V> nodeDefinition, Flow flow, GrantedOperation grantedOperation);

    /**
     * 操作任务
     *
     * @param nodeDefinition 节点定义
     * @param taskId         任务id
     */
    public abstract void pushTask(NodeDefinition<V> nodeDefinition, Long taskId);

    /**
     * 操作定义
     *
     * @return {@code Set<GrantedOperation>}
     */
    public abstract Set<GrantedOperation> operationDefinition();

}
