package com.cl.code.node;

import com.alibaba.fastjson.JSONObject;
import com.cl.code.Flow;
import com.cl.code.FlowNode;
import com.cl.code.dao.AgtFlowDao;
import com.cl.code.pojo.po.AgtFlow;

import java.util.List;
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
        // 保存流程当前进度
        AgtFlowDao agtFlowDao = flow.getAgtFlowDao();

        List<AgtFlow> agtFlows = agtFlowDao.selectAll();
        // 监听
        return super.getOut();
    }

}
