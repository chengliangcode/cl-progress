package com.cl.code.core;

import com.cl.code.FlowEngine;
import com.cl.code.actuator.OperationTaskNodeActuator;
import com.cl.code.constant.NodeResultEnum;
import com.cl.code.operation.GrantedOperation;
import com.cl.code.property.NodeProperty;
import com.cl.code.util.FlowBuildUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author chengliang
 * @date 2022/8/31 16:00
 */
@Slf4j
@AllArgsConstructor
public class Flow {

    public static final Long START_NODE_ID = 1L;

    public static final Long END_NODE_ID = -1L;

    private final Long flowId;

    private final Map<Long, NodeDefinition<? extends NodeProperty>> flowNodeMap;

    public Flow(Long flowId, List<NodeDefinition<? extends NodeProperty>> flowNodeList) {
        this.flowId = flowId;
        this.flowNodeMap = flowNodeList.stream().collect(Collectors.toMap(NodeDefinition::getNodeId, Function.identity(), (k1, k2) -> k1, LinkedHashMap::new));
    }

    public Long getFlowId() {
        return flowId;
    }

    void executeTask() {

        // start
        NodeDefinition<? extends NodeProperty> start = getNode(START_NODE_ID);
        if (start != null) {

            // loop
            NodeDefinition<? extends NodeProperty> next = loop(executeNode(start));

            if (next != null && END_NODE_ID.equals(next.getNodeId())) {
                // end
                NodeDefinition<? extends NodeProperty> end = getNode(END_NODE_ID);
                if (end != null) {
                    executeNode(end);
                }
            }

        }
    }

    @SuppressWarnings("all")
    void executeTask(Long nodeId, Long taskId, GrantedOperation operation, Map<String, Object> context) {
        // 是否为当前节点

        // 查找 node
        NodeDefinition nodeDefinition = getNode(nodeId);
        OperationTaskNodeActuator<? extends NodeType<?>, ? extends NodeProperty> operationTaskNodeActuator = (OperationTaskNodeActuator<? extends NodeType<?>, ? extends NodeProperty>) FlowBuildUtils.findActuator(nodeDefinition);

        // 触发操作
        Long next = operationTaskNodeActuator.operationTrigger(nodeDefinition, this, taskId, operation, context);
        FlowEngine.getFlowEngine().getHistoryService().saveHistory(flowId, nodeDefinition.getNodeId(), result);
        // 执行下次并循环
        loop(getNode(next));

    }

    private NodeDefinition<? extends NodeProperty> loop(NodeDefinition<? extends NodeProperty> nodeDefinition) {
        if (nodeDefinition == null) {
            return null;
        }
        return loop(executeNode(nodeDefinition));
    }

    private NodeDefinition<? extends NodeProperty> getNode(Long nodeId) {
        return flowNodeMap.get(nodeId);
    }

    @SuppressWarnings("all")
    private NodeDefinition<? extends NodeProperty> executeNode(NodeDefinition nodeDefinition) {
        NodeActuator<? extends NodeType<?>, ? extends NodeProperty> actuator = FlowBuildUtils.findActuator(nodeDefinition);
        // 记录历史
        NodeResult nodeResult = actuator.execute(nodeDefinition, this);
        String result = nodeResult.result();
        FlowEngine.getFlowEngine().getHistoryService().saveHistory(flowId, nodeDefinition.getNodeId(), result);
        if (NodeResultEnum.DONE.result().equals(result)) {
            return getNode(nodeDefinition.getOutput());
        } else if (NodeResultEnum.ING.result().equals(result)) {
            return null;
        } else if (NodeResultEnum.RETURN.result().equals(result)) {
            // 退回
            return null;
        }
        throw new RuntimeException("不支持该返回结果");
    }


}
