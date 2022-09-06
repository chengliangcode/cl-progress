package com.cl.code.node;

import com.cl.code.Flow;
import com.cl.code.FlowNodeActuator;
import com.cl.code.NodeDefinition;

/**
 * @author chengliang
 * @date 2022/9/1 11:00
 */
public class StartNodeActuator extends FlowNodeActuator {


    public Long execute(NodeDefinition nodeDefinition, Flow flow) {
        // 记录发起人
        return nodeDefinition.getOutput();
    }

}
