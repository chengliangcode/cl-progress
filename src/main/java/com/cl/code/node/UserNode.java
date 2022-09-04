package com.cl.code.node;

import com.alibaba.fastjson.JSONObject;
import com.cl.code.Flow;
import com.cl.code.FlowNode;

import java.util.Map;

/**
 * @author chengliang
 * @date 2022/8/31 15:53
 */
public class UserNode extends FlowNode {

    private static final String USER = "user";

    public UserNode(JSONObject jsonObject) {
        super(jsonObject);
    }

    public Long execute(Long flowId, Flow flow) {
        Map<String, Object> context = super.getContext();
        // 推送任务 回调
        String approvalUserName = (String) context.get(USER);

        // 监听
        return super.getOutput();
    }

}
