package com.cl.code.node;

import com.alibaba.fastjson.JSONObject;
import com.cl.code.Flow;
import com.cl.code.FlowNode;

/**
 * @author chengliang
 * @date 2022/9/1 11:00
 */
public class StartNode extends FlowNode {

    public StartNode(JSONObject jsonObject) {
        super(jsonObject);
    }

    public Long execute(Long flowId, Flow flow) {
        return super.getOut();
    }

}
