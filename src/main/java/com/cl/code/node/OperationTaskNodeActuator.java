package com.cl.code.node;

import com.cl.code.NodeDefinition;

/**
 * 操作任务节点
 *
 * @author chengliang
 * @date 2022/9/5 9:59
 */
public abstract class OperationTaskNodeActuator extends TaskNodeActuator {

    @Override
    public void task(NodeDefinition nodeDefinition) {
        // 创建并发布任务
    }
//
//    /**
//     * 操作定义
//     *
//     * @return {@code Set<O>}
//     */
//    public abstract Set<R> operationDefine();
//
//    /**
//     * 操作任务
//     *
//     * @param flowId           流id
//     * @param grantedOperation 授予操作
//     */
//    public abstract void operationTask(Long flowId, R grantedOperation);
}
