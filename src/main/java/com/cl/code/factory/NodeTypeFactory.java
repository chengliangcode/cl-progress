package com.cl.code.factory;

import com.cl.code.core.NodeType;
import com.cl.code.property.NodeProperty;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chengliang
 * @date 2022/9/7 18:07
 */
public class NodeTypeFactory {

    private static final Map<String, NodeType<? extends NodeProperty>> NODE_TYPE_MAP = new ConcurrentHashMap<>();

    public static void register(String key, NodeType<? extends NodeProperty> nodeType) {
        if (nodeType == null || !StringUtils.hasText(nodeType.getType())) {
            throw new RuntimeException(" node actuator register error !!");
        }
        NODE_TYPE_MAP.put(key, nodeType);
    }

    public static NodeType<? extends NodeProperty> getNodeType(String nodeType) {
        if (!StringUtils.hasText(nodeType)) {
            throw new RuntimeException("node type not be null");
        }
        NodeType<? extends NodeProperty> t = NODE_TYPE_MAP.get(nodeType);
        if (t == null) {
            throw new RuntimeException("not support this node type :" + nodeType);
        }
        return t;
    }


}
