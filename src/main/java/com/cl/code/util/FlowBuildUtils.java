package com.cl.code.util;

import com.alibaba.fastjson.JSON;
import com.cl.code.NodeActuator;
import com.cl.code.NodeDefinition;
import com.cl.code.constant.FlowTypeEnum;
import com.cl.code.node.EndNodeActuator;
import com.cl.code.node.MessageNodeActuator;
import com.cl.code.node.StartNodeActuator;
import com.cl.code.node.UserNodeActuator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chengliang
 * @date 2022/9/5 14:52
 */
@Slf4j
public class FlowBuildUtils {

    public static List<NodeDefinition> buildFlow(String json) {
        try {
            if (!StringUtils.hasText(json)) {
                throw new RuntimeException("流程为空");
            }
            List<String> flowNodeList = JSON.parseArray(json, String.class);
            if (flowNodeList.size() == 0) {
                throw new RuntimeException("流程为空");
            }
            List<NodeDefinition> nodeDefinitions = flowNodeList.stream().map(node -> JSON.parseObject(node, NodeDefinition.class)).collect(Collectors.toList());
            Set<Long> nodeIds = nodeDefinitions.stream().map(NodeDefinition::getNodeId).collect(Collectors.toSet());
            if (flowNodeList.size() != nodeIds.size()) {
                throw new RuntimeException("nodeId重复");
            }
            nodeDefinitions.forEach(item -> item.setType(item.getType().toUpperCase()));
            return nodeDefinitions;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public static NodeActuator findActuator(NodeDefinition nodeDefinition) {
        switch (FlowTypeEnum.valueOf(nodeDefinition.getType())) {
            case START:
                return new StartNodeActuator();
            case END:
                return new EndNodeActuator();
            case USER:
                return new UserNodeActuator();
            case MESSAGE:
                return new MessageNodeActuator();
            default:
                throw new RuntimeException("存在不支持的节点类型");
        }
    }

}
