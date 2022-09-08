package com.cl.code.actuator;

import com.cl.code.constant.TaskStatusEnum;
import com.cl.code.core.Flow;
import com.cl.code.core.NodeDefinition;
import com.cl.code.node.UserNode;
import com.cl.code.operation.GrantedOperation;
import com.cl.code.operation.SimpleGrantedOperation;
import com.cl.code.property.UserProperty;
import org.springframework.stereotype.Component;

import java.util.Collections;
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
    protected TaskStatusEnum operationTrigger(NodeDefinition<UserProperty> nodeDefinition, Flow flow, GrantedOperation grantedOperation) {
        return TaskStatusEnum.DONE;
    }

}
