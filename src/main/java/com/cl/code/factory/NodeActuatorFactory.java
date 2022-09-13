package com.cl.code.factory;

import com.cl.code.core.NodeActuator;
import com.cl.code.core.NodeType;
import com.cl.code.property.NodeProperty;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chengliang
 * @date 2022/9/7 10:01
 */
public class NodeActuatorFactory {

    private static final Map<String, NodeActuator<? extends NodeType<?>, ? extends NodeProperty>> NODE_ACTUATOR_MAP = new ConcurrentHashMap<>();


    public static void register(String nodeType, NodeActuator<? extends NodeType<?>, ? extends NodeProperty> nodeActuator) {
        if (!StringUtils.hasText(nodeType) || nodeActuator == null) {
            throw new RuntimeException(" node actuator register error !!");
        }

        if (NODE_ACTUATOR_MAP.containsKey(nodeType)) {
            throw new RuntimeException("node type \"" + nodeType + "\"" + "already exist");
        }

        NODE_ACTUATOR_MAP.put(nodeType, nodeActuator);
    }

    public static NodeActuator<? extends NodeType<?>, ? extends NodeProperty> getActuator(String nodeType) {
        if (!StringUtils.hasText(nodeType)) {
            throw new RuntimeException("node type not be null");
        }
        NodeActuator<? extends NodeType<?>, ? extends NodeProperty> nodeActuator = NODE_ACTUATOR_MAP.get(nodeType);
        if (nodeActuator == null) {
            throw new RuntimeException("not support this node type :" + nodeType);
        }
        return nodeActuator;
    }

}
