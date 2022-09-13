package com.cl.code.core;

import com.cl.code.dao.AgtFlowHistoryDao;
import com.cl.code.model.AgtFlowHistory;
import com.cl.code.util.FlowIdUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author chengliang
 * @date 2022/9/9 15:08
 */
@Service
public class HistoryService {

    @Resource
    private AgtFlowHistoryDao agtFlowHistoryDao;

    @Transactional(rollbackFor = Exception.class)
    public void saveHistory(Long flowId, Long nodeId, String nodeResult) {
        Long historyId = FlowIdUtils.getTwiterId();
        AgtFlowHistory history = new AgtFlowHistory();
        history.setHistoryId(historyId);
        history.setFlowId(flowId);
        history.setNodeId(nodeId);
        history.setNodeResult(nodeResult);
        history.setTime(System.currentTimeMillis());
        agtFlowHistoryDao.insert(history);

    }

}
