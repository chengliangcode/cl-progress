package com.cl.code.actuator;

import com.cl.code.core.Flow;
import com.cl.code.core.NodeDefinition;
import com.cl.code.core.NodeType;
import com.cl.code.property.NodeProperty;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author chengliang
 * @date 2022/9/1 10:48
 */
public interface NodeActuator<T extends NodeType<V>, V extends NodeProperty> extends InitializingBean {

    /**
     * 执行
     *
     * @param nodeDefinition 节点定义
     * @param flow           流
     * @return {@code Long}
     */
    Long execute(NodeDefinition<V> nodeDefinition, Flow flow);


}
