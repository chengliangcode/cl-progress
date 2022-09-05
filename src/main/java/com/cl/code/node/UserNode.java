package com.cl.code.node;

import com.alibaba.fastjson.JSONObject;
import com.cl.code.NodeDefinition;

import java.util.Map;

/**
 * @author chengliang
 * @date 2022/8/31 15:53
 */
public class UserNode extends OperationTaskNode {

    private static final String USER = "user";

    public UserNode(NodeDefinition nodeDefinition) {
        super(nodeDefinition);
    }

    @Override
    public void operationTask(Long flowId) {
        Map<String, Object> context = super.getContext();
        // 推送任务 回调
        String approvalUserName = (String) context.get(USER);
        // 监听
    }
}
