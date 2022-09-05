package com.cl.code.node;

import com.cl.code.Flow;
import com.cl.code.FlowNode;
import com.cl.code.NodeDefinition;

/**
 * @author chengliang
 * @date 2022/9/1 11:00
 */
public class StartNode extends FlowNode {

    public StartNode(NodeDefinition nodeDefinition) {
        super(nodeDefinition);
    }

    public Long execute(Long flowId, Flow flow) {
        return super.getOutput();
    }

}
