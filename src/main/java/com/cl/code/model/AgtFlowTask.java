package com.cl.code.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 *
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

    @Column(name = "task_satus")
    private Integer taskSatus;


    public static final String TASK_ID = "taskId";

    public static final String NODE_ID = "nodeId";

    public static final String FLOW_ID = "flowId";

    public static final String TASK_SATUS = "taskSatus";

}
