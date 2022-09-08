package com.cl.code.actuator;

import com.cl.code.core.Flow;
import com.cl.code.core.NodeDefinition;
import com.cl.code.node.EndNode;
import com.cl.code.property.NullProperty;
import org.springframework.stereotype.Component;

/**
 * @author chengliang
 * @date 2022/9/1 11:00
 */
@Component
public class EndNodeActuator extends FlowNodeActuator<EndNode, NullProperty> {

    @Override
    public Long execute(NodeDefinition<NullProperty> nodeDefinition, Flow flow) {
        return null;
    }

}
