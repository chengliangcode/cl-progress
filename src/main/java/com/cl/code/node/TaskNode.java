package com.cl.code.node;

import com.alibaba.fastjson.JSONObject;
import com.cl.code.Flow;
import com.cl.code.FlowNode;
import com.cl.code.NodeDefinition;
import com.cl.code.Task;

/**
 * 任务节点
 *
 * @author chengliang
 * @date 2022/9/5 9:12
 */
public abstract class TaskNode extends FlowNode implements Task {

    public TaskNode(NodeDefinition nodeDefinition) {
        super(nodeDefinition);
    }

    @Override
    public Long execute(Long flowId, Flow flow) {
        this.task(flowId);
        return getOutput();
    }

}