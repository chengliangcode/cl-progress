package com.cl.code.node;

import com.alibaba.fastjson.JSONObject;
import com.cl.code.NodeDefinition;

/**
 * 操作任务节点
 *
 * @author chengliang
 * @date 2022/9/5 9:59
 */
public abstract class OperationTaskNode extends TaskNode {

    public OperationTaskNode(NodeDefinition nodeDefinition) {
        super(nodeDefinition);
    }

    @Override
    public void task(Long flowId) {
        // 发布任务


    }

    /**
     * 操作任务
     *
     * @param flowId 流id
     */
    public abstract void operationTask(Long flowId);
}
