package com.cl.code.core;

import com.alibaba.fastjson.JSONObject;
import com.cl.code.core.NodeDefinition;
import com.cl.code.factory.NodeTypeFactory;
import com.cl.code.model.AgtFlowNode;
import com.cl.code.property.NodeProperty;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author chengliang
 * @date 2022/9/7 10:10
 */
public interface NodeType<T extends NodeProperty> extends InitializingBean {

    /**
     * 支持类型
     *
     * @return {@code String}
     */
    String getType();

    /**
     * 改变
     *
     * @param node json
     * @return {@code T}
     */
    default NodeDefinition<T> change(AgtFlowNode node) {

        NodeDefinition<T> nodeDefinition = new NodeDefinition<>();
        nodeDefinition.setNodeId(node.getNodeId());
        nodeDefinition.setType(node.getType());
        nodeDefinition.setName(node.getName());
        nodeDefinition.setInput(node.getInput());
        nodeDefinition.setOutput(node.getOutput());

        String properties = node.getProperties();
        if (properties != null) {
            nodeDefinition.setProperties(parse(JSONObject.parseObject(properties)));
        }

        return nodeDefinition;

    }

    /**
     * 解析
     *
     * @param properties 上下文
     * @return {@code T}
     */
    default T parse(JSONObject properties) {
        Type type = ((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return properties.toJavaObject(type);
    }

    /**
     * 在属性设置
     *
     * @throws Exception 异常
     */
    @Override
    default void afterPropertiesSet() throws Exception {
        NodeTypeFactory.register(this.getType(), this);
    }


}
