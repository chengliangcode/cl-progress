package com.cl.code.service;

import com.cl.code.dao.AgtFlowTaskDao;
import com.cl.code.operation.GrantedOperation;
import com.cl.code.util.FlowIdUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author chengliang
 * @date 2022/9/5 14:23
 */
@Service
public class TaskService {

    @Resource
    AgtFlowTaskDao agtFlowTaskDao;

    @Transactional(rollbackFor = Exception.class)
    public <R extends GrantedOperation> Long pushTask(Long flowId, Long nodeId, Set<R> grantedOperations) {


        // TODO 创建并保存任务
        return FlowIdUtils.getTwiterId();
    }

}
