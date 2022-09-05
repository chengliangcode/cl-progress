package com.cl.code.service;

import com.cl.code.Flow;
import com.cl.code.NodeDefinition;
import com.cl.code.dao.AgtFlowDao;
import com.cl.code.dao.AgtFlowNodeDao;
import com.cl.code.model.AgtFlow;
import com.cl.code.model.AgtFlowNode;
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

    public Flow buildFlow(String json) {
        List<NodeDefinition> flowNodeList = FlowBuildUtils.buildFlow(json);
        Long flowId = saveFlow(flowNodeList);
        return new Flow(flowId, flowNodeList);
    }

    @Transactional(rollbackFor = Exception.class)
    protected Long saveFlow(List<NodeDefinition> flowNodeList) {
        Long flowId = FlowIdUtils.getTwiterId();
        // 持久化flow
        agtFlowDao.insert(new AgtFlow(flowId));
        // 持久化 flowNode
        List<AgtFlowNode> agtFlowNodeList = flowNodeList.stream().map(nodeDefinition -> new AgtFlowNode(flowId, nodeDefinition)).collect(Collectors.toList());
        agtFlowNodeDao.batchInsert(agtFlowNodeList);
        return flowId;
    }

}
