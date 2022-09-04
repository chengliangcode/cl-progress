package com.cl.code;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cl.code.constant.FlowTypeEnum;
import com.cl.code.dao.AgtFlowRecordDao;
import com.cl.code.model.AgtFlowRecord;
import com.cl.code.node.EndNode;
import com.cl.code.node.MessageNode;
import com.cl.code.node.StartNode;
import com.cl.code.node.UserNode;
import com.cl.code.util.TwiterIdUtil;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * @author chengliang
 * @date 2022/8/31 16:00
 */
@AllArgsConstructor
public class Flow {

    public static final Long START_NODE_ID = 1L;
    public static final Long END_NODE_ID = -1L;

    private final Long flowId;

    private final Map<Long, String> flowNodeMap;

    public void execute() {
        // start
        FlowNode start = getNode(START_NODE_ID);
        Long next = executeNode(start);
        // loop
        FlowNode end = loop(getNode(next));
        // end
        executeNode(end);
    }

    private FlowNode loop(FlowNode flowNode) {
        if (flowNode.getNodeId().equals(END_NODE_ID)) {
            return flowNode;
        }
        return loop(getNode(executeNode(flowNode)));
    }

    private FlowNode getNode(Long nodeId) {
        String flowNodeJson = flowNodeMap.get(nodeId);
        JSONObject item = JSON.parseObject(flowNodeJson);
        FlowTypeEnum type = FlowTypeEnum.valueOf(item.getObject("type", String.class).toUpperCase());
        switch (type) {
            case START:
                return new StartNode(item);
            case END:
                return new EndNode(item);
            case USER:
                return new UserNode(item);
            case MESSAGE:
                return new MessageNode(item);
            default:
                throw new RuntimeException("存在不支持的节点类型");
        }
    }

    private Long executeNode(FlowNode flowNode) {
        // TODO 记录执行
        AgtFlowRecordDao agtFlowRecordDao = FlowEngine.agtFlowRecordDao;
        agtFlowRecordDao.insert(new AgtFlowRecord(TwiterIdUtil.getTwiterId(), flowId, flowNode.getNodeId(), System.currentTimeMillis()));
        return flowNode.execute(flowId, this);
    }

}
