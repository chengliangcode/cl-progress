package com.cl.code.model;

import com.alibaba.fastjson.JSON;
import com.cl.code.core.NodeDefinition;
import com.cl.code.property.NodeProperty;
import com.cl.code.util.FlowIdUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chengliang
 * @since 2022-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "agt_flow_node")
public class AgtFlowNode {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "node_id")
    private Long nodeId;

    @Column(name = "flow_id")
    private Long flowId;

    @Column(name = "input")
    private Long input;

    @Column(name = "output")
    private Long output;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "properties")
    private String properties;

    public AgtFlowNode() {
    }

    public AgtFlowNode(Long flowId, NodeDefinition<? extends NodeProperty> nodeDefinition) {
        this.id = FlowIdUtils.getTwiterId();
        this.nodeId = nodeDefinition.getNodeId();
        this.flowId = flowId;
        this.input = nodeDefinition.getInput();
        this.output = nodeDefinition.getOutput();
        this.type = nodeDefinition.getType();
        this.name = nodeDefinition.getName();
        if (nodeDefinition.getProperties() != null) {
            this.properties = JSON.toJSONString(nodeDefinition.getProperties());
        }
    }

}
