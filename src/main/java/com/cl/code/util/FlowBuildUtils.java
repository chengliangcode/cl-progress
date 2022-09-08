package com.cl.code.util;

import com.alibaba.fastjson.JSON;
import com.cl.code.actuator.NodeActuator;
import com.cl.code.core.NodeDefinition;
import com.cl.code.core.NodeType;
import com.cl.code.factory.NodeActuatorFactory;
import com.cl.code.factory.NodeTypeFactory;
import com.cl.code.model.AgtFlowNode;
import com.cl.code.property.NodeProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chengliang
 * @date 2022/9/5 14:52
 */
@Slf4j
public class FlowBuildUtils {

    public static List<NodeDefinition<? extends NodeProperty>> buildFlow(String json) {
        if (!StringUtils.hasText(json)) {
            throw new RuntimeException("流程为空");
        }
        List<AgtFlowNode> flowNodeList = JSON.parseArray(json, AgtFlowNode.class);
        if (flowNodeList.size() == 0) {
            throw new RuntimeException("流程为空");
        }
        return buildFlow(flowNodeList);
    }

    public static List<NodeDefinition<? extends NodeProperty>> buildFlow(List<AgtFlowNode> flowNodeList) {
        try {
            List<NodeDefinition<? extends NodeProperty>> nodeDefinitions = new ArrayList<>();
            for (AgtFlowNode node : flowNodeList) {
                NodeType<?> nodeType = NodeTypeFactory.getNodeType(node.getType());
                NodeDefinition<? extends NodeProperty> nodeDefinition = nodeType.change(node);
                nodeDefinitions.add(nodeDefinition);

            }
            Set<Long> nodeIds = nodeDefinitions.stream().map(NodeDefinition::getNodeId).collect(Collectors.toSet());
            if (flowNodeList.size() != nodeIds.size()) {
                throw new RuntimeException("nodeId重复");
            }
            return nodeDefinitions;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("流程构建失败");
        }
    }

    public static NodeActuator<? extends NodeType<?>, ? extends NodeProperty> findActuator(NodeDefinition<?> nodeDefinition) {
        return NodeActuatorFactory.getActuator(nodeDefinition.getType());
    }

}
