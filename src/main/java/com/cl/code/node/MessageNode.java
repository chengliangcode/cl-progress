package com.cl.code.node;

import com.alibaba.fastjson.JSONObject;
import com.cl.code.Flow;
import com.cl.code.FlowNode;

/**
 * @author chengliang
 * @date 2022/9/1 11:27
 */
public class MessageNode extends FlowNode {

    public MessageNode(JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public Long execute(Long flowId, Flow flow) {
        return super.getOutput();
    }
}
