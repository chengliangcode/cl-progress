package com.cl.code.actuator;

import com.cl.code.core.NodeDefinition;
import com.cl.code.node.MessageNode;
import com.cl.code.property.NullProperty;
import org.springframework.stereotype.Component;

/**
 * @author chengliang
 * @date 2022/9/1 11:27
 */
@Component
public class MessageNodeActuator extends AutoTaskNodeActuator<MessageNode, NullProperty> {

    @Override
    public void autoTask(NodeDefinition<NullProperty> nodeDefinition) {

    }

}
