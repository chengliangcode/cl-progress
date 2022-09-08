package com.cl.code.actuator;

import com.cl.code.core.Flow;
import com.cl.code.core.NodeDefinition;
import com.cl.code.node.StartNode;
import com.cl.code.property.NullProperty;
import org.springframework.stereotype.Component;

/**
 * @author chengliang
 * @date 2022/9/1 11:00
 */
@Component
public class StartNodeActuator extends FlowNodeActuator<StartNode, NullProperty> {

    @Override
    public Long execute(NodeDefinition<NullProperty> nodeDefinition, Flow flow) {
        return nodeDefinition.getOutput();
    }

}
