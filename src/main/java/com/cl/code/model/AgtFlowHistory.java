package com.cl.code.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chengliang
 * @since 2022-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "agt_flow_history")
public class AgtFlowHistory {

    private static final long serialVersionUID = 1L;

    @Id
    private Long historyId;

    @Column(name = "flow_id")
    private Long flowId;

    @Column(name = "node_id")
    private Long nodeId;

    @Column(name = "time")
    private Long time;

    public AgtFlowHistory(Long historyId, Long flowId, Long nodeId, Long time) {
        this.historyId = historyId;
        this.flowId = flowId;
        this.nodeId = nodeId;
        this.time = time;
    }

    public AgtFlowHistory() {
    }
}
