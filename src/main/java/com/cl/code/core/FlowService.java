package com.cl.code.core;

import com.cl.code.dao.AgtFlowDao;
import com.cl.code.dao.AgtFlowNodeDao;
import com.cl.code.model.AgtFlow;
import com.cl.code.model.AgtFlowNode;
import com.cl.code.operation.GrantedOperation;
import com.cl.code.property.NodeProperty;
import com.cl.code.util.FlowBuildUtils;
import com.cl.code.util.FlowIdUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chengliang
 * @date 2022/9/5 14:40
 */
@Service
public class FlowService {

    @Resource
    private AgtFlowDao agtFlowDao;

    @Resource
    private AgtFlowNodeDao agtFlowNodeDao;

    @Transactional(rollbackFor = Exception.class)
    public Long executeFlow(String json) {
        List<NodeDefinition<? extends NodeProperty>> flowNodeList = FlowBuildUtils.buildFlow(json);
        Long flowId = saveFlow(flowNodeList);
        Flow flow = new Flow(flowId, flowNodeList);
        flow.executeTask();
        return flowId;
    }

    public void executeTask(Long flowId, Long nodeId, Long taskId, GrantedOperation operation) {
        Flow flow = findFlow(flowId);
        flow.executeTask(nodeId, taskId, operation);
    }

    private Flow findFlow(Long flowId) {
        // 找到持久化的flow
        List<NodeDefinition<? extends NodeProperty>> flowNodeList = FlowBuildUtils.buildFlow(agtFlowNodeDao.findNodeByFlowId(flowId));
        return new Flow(flowId, flowNodeList);
    }

    private Long saveFlow(List<NodeDefinition<? extends NodeProperty>> flowNodeList) {
        Long flowId = FlowIdUtils.getTwiterId();
        // 持久化flow
        agtFlowDao.insert(new AgtFlow(flowId));
        // 持久化 flowNode
        List<AgtFlowNode> agtFlowNodeList = flowNodeList.stream().map(nodeDefinition -> new AgtFlowNode(flowId, nodeDefinition)).collect(Collectors.toList());
        agtFlowNodeDao.batchInsert(agtFlowNodeList);
        return flowId;
    }


}
