package com.cl.code;

import com.cl.code.util.FlowBuildUtils;
import lombok.AllArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author chengliang
 * @date 2022/8/31 16:00
 */
@AllArgsConstructor
public class Flow {

    public static final Long START_NODE_ID = 1L;
    public static final Long END_NODE_ID = -1L;

    private final Long flowId;

    private final Map<Long, NodeDefinition> flowNodeMap;

    public Flow(Long flowId, List<NodeDefinition> flowNodeList) {
        this.flowId = flowId;
        this.flowNodeMap = flowNodeList.stream().collect(Collectors.toMap(NodeDefinition::getNodeId, Function.identity(), (k1, k2) -> k1, LinkedHashMap::new));
    }

    public void execute() {
        // start
        FlowNode start = getNode(START_NODE_ID);
        Long next = executeNode(start);
        // loop
        FlowNode end = loop(getNode(next));
        // end
        executeNode(end);
    }

    private FlowNode loop(FlowNode flowNode) {
        if (flowNode.getNodeId().equals(END_NODE_ID)) {
            return flowNode;
        }
        return loop(getNode(executeNode(flowNode)));
    }

    private FlowNode getNode(Long nodeId) {
        return FlowBuildUtils.buildNode(flowNodeMap.get(nodeId));
    }

    private Long executeNode(FlowNode flowNode) {
        // TODO 记录执行
        return flowNode.execute(flowId, this);
    }

}
