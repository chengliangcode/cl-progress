package com.cl.code.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chengliang
 * @date 2022-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "agt_flow")
public class AgtFlow {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    private Long flowId;

    public static final String FLOW_ID = "flowId";

    public AgtFlow() {
    }

    public AgtFlow(Long flowId) {
        this.flowId = flowId;
    }
}
