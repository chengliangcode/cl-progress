package com.cl.code.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chengliang
 * @date 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "agt_flow_task")
public class AgtFlowTask {

    private static final long serialVersionUID = 1L;

    @Id
    private Long taskId;

    @Column(name = "node_id")
    private Long nodeId;

    @Column(name = "flow_id")
    private Long flowId;

    @Column(name = "task_status")
    private String taskStatus;

    @Column(name = "task_type")
    private String taskType;


    public AgtFlowTask() {
    }

    public AgtFlowTask(Long taskId, Long nodeId, Long flowId, String taskStatus, String taskType) {
        this.taskId = taskId;
        this.nodeId = nodeId;
        this.flowId = flowId;
        this.taskStatus = taskStatus;
        this.taskType = taskType;
    }

    public static final String TASK_ID = "taskId";

    public static final String NODE_ID = "nodeId";

    public static final String FLOW_ID = "flowId";

    public static final String TASK_STATUS = "taskStatus";

    public static final String TASK_TYPE = "taskType";

}
