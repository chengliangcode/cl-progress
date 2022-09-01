package com.cl.code.node;

import com.cl.code.FlowNode;

import java.util.Map;

/**
 * @author chengliang
 * @date 2022/8/31 15:53
 */
public class ApprovalNode extends FlowNode {

    private static final String USER = "user";

    public void execute() {
        // 推送任务
        Map<String, Object> context = super.getContext();
        String approvalUserName = (String) context.get(USER);
        System.out.println("现在由" + approvalUserName + "审批");
    }

}
