package com.cl.code.actuator;

import com.cl.code.FlowEngine;
import com.cl.code.constant.NodeResultEnum;
import com.cl.code.constant.TaskTypeEnum;
import com.cl.code.core.*;
import com.cl.code.operation.GrantedOperation;
import com.cl.code.property.NodeProperty;
import org.springframework.stereotype.Component;

import java.util.Map;
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
    public NodeResult task(NodeDefinition<V> nodeDefinition, Flow flow, Long taskId) {
        // 创建并发布任务
        pushTask(nodeDefinition, taskId);
        return NodeResultEnum.ING;
    }

    public NodeResult operationTrigger(NodeDefinition<V> nodeDefinition, Flow flow, Long taskId, GrantedOperation grantedOperation, Map<String, Object> context) {
        // 是否支持该操作
        boolean support = operationDefinition().stream().anyMatch(item -> item.getOperation().equals(grantedOperation.getOperation()));
        if (support) {
            // 查找 task
            TaskService taskService = FlowEngine.getFlowEngine().getTaskService();
            // 查找该flow和node下的task 当task完成则继续下一node
            boolean executable = taskService.executableTask(flow.getFlowId(), nodeDefinition.getNodeId(), taskId);
            if (executable) {
                OperationResult operationResult = operationTrigger(nodeDefinition, flow, grantedOperation, context);
                // 更新 task 状态
                taskService.updateTask(flow.getFlowId(), nodeDefinition.getNodeId(), taskId, operationResult.getTaskStatusEnum());
                return operationResult.getNodeResult();
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
     * @param context
     * @return {@code Long}
     */
    protected abstract OperationResult operationTrigger(NodeDefinition<V> nodeDefinition, Flow flow, GrantedOperation grantedOperation, Map<String, Object> context);

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
