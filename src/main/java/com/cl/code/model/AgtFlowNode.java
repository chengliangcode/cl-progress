package com.cl.code.model;

import com.alibaba.fastjson.JSON;
import com.cl.code.NodeDefinition;
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
@Table(name = "agt_flow_node")
public class AgtFlowNode {

    private static final long serialVersionUID = 1L;

    @Id
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

    @Column(name = "context")
    private String context;


    public AgtFlowNode() {
    }

    public AgtFlowNode(Long nodeId, Long flowId, Long input, Long output, String type, String name, String context) {
        this.nodeId = nodeId;
        this.flowId = flowId;
        this.input = input;
        this.output = output;
        this.type = type;
        this.name = name;
        this.context = context;
    }

    public AgtFlowNode(Long flowId, NodeDefinition nodeDefinition) {
        this.nodeId = nodeDefinition.getNodeId();
        this.flowId = flowId;
        this.input = nodeDefinition.getInput();
        this.output = nodeDefinition.getOutput();
        this.type = nodeDefinition.getType();
        this.name = nodeDefinition.getName();
        this.context = JSON.toJSONString(nodeDefinition.getContext());
    }


}
