package com.cl.code.node;

import com.alibaba.fastjson.JSONObject;
import com.cl.code.NodeDefinition;

/**
 * 自动任务节点
 *
 * @author chengliang
 * @date 2022/9/5 10:43
 */
public abstract class AutoTaskNode extends TaskNode {

    public AutoTaskNode(NodeDefinition nodeDefinition) {
        super(nodeDefinition);
    }

    @Override
    public void task(Long flowId) {

    }

    /**
     * 自动任务
     */
    public abstract void autoTask();

}
