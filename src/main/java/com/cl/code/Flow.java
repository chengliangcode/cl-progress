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
        NodeDefinition start = getNode(START_NODE_ID);
        NodeDefinition nodeDefinition = executeNode(start);

        // loop
        NodeDefinition end = loop(nodeDefinition);

        // end
        executeNode(end);

    }

    private NodeDefinition loop(NodeDefinition nodeDefinition) {
        if (END_NODE_ID.equals(nodeDefinition.getNodeId())) {
            return getNode(END_NODE_ID);
        }
        return loop(executeNode(nodeDefinition));
    }

    private NodeDefinition getNode(Long nodeId) {
        return flowNodeMap.get(nodeId);
    }

    private NodeDefinition executeNode(NodeDefinition nodeDefinition) {
        NodeActuator actuator = FlowBuildUtils.findActuator(nodeDefinition);
        // TODO 记录执行
        Long next = actuator.execute(nodeDefinition, this);
        return getNode(next);
    }

}
