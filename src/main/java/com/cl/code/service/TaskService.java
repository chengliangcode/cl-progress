package com.cl.code.service;

import com.cl.code.constant.TaskStatusEnum;
import com.cl.code.constant.TaskTypeEnum;
import com.cl.code.dao.AgtFlowTaskDao;
import com.cl.code.model.AgtFlowTask;
import com.cl.code.util.FlowIdUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author chengliang
 * @date 2022/9/5 14:23
 */
@Service
public class TaskService {

    @Resource
    AgtFlowTaskDao agtFlowTaskDao;

    @Transactional(rollbackFor = Exception.class)
    public Long createTask(Long flowId, Long nodeId, TaskTypeEnum taskType) {
        Long taskId = FlowIdUtils.getTwiterId();
        AgtFlowTask agtFlowTask = new AgtFlowTask(taskId, nodeId, flowId, TaskStatusEnum.WAIT.name(), taskType.name());
        agtFlowTaskDao.insert(agtFlowTask);
        return taskId;
    }

    public boolean executableTask(Long flowId, Long nodeId, Long taskId) {
        Example example = common(flowId, nodeId, taskId);
        int i = agtFlowTaskDao.selectCountByExample(example);
        return i > 0;
    }

    public void updateTask(Long flowId, Long nodeId, Long taskId, TaskStatusEnum taskStatus) {
        Example example = common(flowId, nodeId, taskId);
        AgtFlowTask agtFlowTask = new AgtFlowTask();
        agtFlowTask.setTaskStatus(taskStatus.name());
        agtFlowTaskDao.updateByExampleSelective(agtFlowTask, example);
    }

    private Example common(Long flowId, Long nodeId, Long taskId) {
        Example example = new Example(AgtFlowTask.class);
        example.and().andEqualTo(AgtFlowTask.FLOW_ID, flowId).andEqualTo(AgtFlowTask.NODE_ID, nodeId).andEqualTo(AgtFlowTask.TASK_ID, taskId).andNotEqualTo(AgtFlowTask.TASK_STATUS, TaskStatusEnum.DONE.name());
        return example;
    }

}
