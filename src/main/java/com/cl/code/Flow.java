package com.cl.code;

import com.cl.code.dao.AgtFlowDao;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * @author chengliang
 * @date 2022/8/31 16:00
 */
@AllArgsConstructor
public class Flow {

    private final AgtFlowDao agtFlowDao;

    public AgtFlowDao getAgtFlowDao() {
        return agtFlowDao;
    }

    public static final Long START_NODE_ID = 1L;
    public static final Long END_NODE_ID = -1L;

    private Long flowId;

    public Long getFlowId() {
        return flowId;
    }

    private Map<Long, FlowNode> flowList;

    public void execute() {
        // start
        FlowNode start = flowList.get(START_NODE_ID);
        // loop
        FlowNode end = loop(flowList.get(start.execute(flowId, this)));
        // end
        end.execute(flowId, this);
    }

    private FlowNode loop(FlowNode flowNode) {
        if (flowNode.getNodeId().equals(END_NODE_ID)) {
            return flowNode;
        }
        return loop(flowList.get(flowNode.execute(flowId, this)));
    }

}
