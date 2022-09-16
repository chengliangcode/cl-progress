package com.cl.code.core;

import com.cl.code.constant.TaskStatusEnum;
import lombok.Getter;

/**
 * @author chengliang
 * @date 2022/9/14 10:09
 */
@Getter
public class OperationResult {

    private NodeResult nodeResult;

    private TaskStatusEnum taskStatusEnum;

    private OperationResult() {

    }

    public static OperationResult build(NodeResult nodeResult, TaskStatusEnum taskStatusEnum) {
        OperationResult operationResult = new OperationResult();
        operationResult.nodeResult = nodeResult;
        operationResult.taskStatusEnum = taskStatusEnum;
        return operationResult;
    }


}
