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
@Table(name = "agt_flow_record")
public class AgtFlowRecord {

    private static final long serialVersionUID = 1L;

    @Id
    private Long recordId;

    @Column(name = "flow_id")
    private Long flowId;

    @Column(name = "node_id")
    private Long nodeId;

    @Column(name = "time")
    private Long time;

    public AgtFlowRecord(Long recordId, Long flowId, Long nodeId, Long time) {
        this.recordId = recordId;
        this.flowId = flowId;
        this.nodeId = nodeId;
        this.time = time;
    }

    public AgtFlowRecord() {
    }
}
