package com.cl.code.service;

import com.cl.code.dao.AgtFlowTaskDao;
import com.cl.code.util.FlowIdUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chengliang
 * @date 2022/9/5 14:23
 */
@Service
public class TaskService {

    @Resource
    AgtFlowTaskDao agtFlowTaskDao;

    public Long pushTask(Long flowId, Long nodeId) {
        System.out.println(1);
        return FlowIdUtils.getTwiterId();
    }

}
