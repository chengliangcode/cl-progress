package com.cl.code.actuator;

import com.cl.code.constant.NodeResultEnum;
import com.cl.code.constant.TaskStatusEnum;
import com.cl.code.core.Flow;
import com.cl.code.core.NodeDefinition;
import com.cl.code.core.OperationResult;
import com.cl.code.node.UserNode;
import com.cl.code.operation.GrantedOperation;
import com.cl.code.operation.SimpleGrantedOperation;
import com.cl.code.property.UserProperty;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author chengliang
 * @date 2022/8/31 15:53
 */
@Component
public class UserNodeActuator extends OperationTaskNodeActuator<UserNode, UserProperty> {

    @Override
    public void pushTask(NodeDefinition<UserProperty> nodeDefinition, Long taskId) {
        System.out.println("------------" + nodeDefinition.getNodeId());
        System.out.println("------------" + taskId);
        // 推送任务给用户
        // 任务和人员绑定
    }

    @Override
    public Set<GrantedOperation> operationDefinition() {
        return Collections.singleton(new SimpleGrantedOperation("agree"));
    }

    @Override
    protected OperationResult operationTrigger(NodeDefinition<UserProperty> nodeDefinition, Flow flow, GrantedOperation grantedOperation, Map<String, Object> context) {
        // 继续 退回 暂停
        // 审批 退回 转审 或签 会签
        // 一个用户的操作进来 我要根据操作来 返回现在节点的状态
        // 或签同意 -> 进入下一个节点
        // 或签拒绝 -> 退回到上一个节点
        // 终止审批 -> 审批驳回
        // 转审 -> 指定一个人 -> 推送消息进入为转审

        // 转审 是节点的状态还是任务的状态?

        return OperationResult.build(NodeResultEnum.DONE, TaskStatusEnum.ING);
    }

}
