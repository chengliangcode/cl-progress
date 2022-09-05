package com.cl.code;

import com.cl.code.constant.FlowTypeEnum;
import lombok.Data;

import java.util.Map;

/**
 * @author chengliang
 * @date 2022/8/31 12:00
 */
@Data
public abstract class FlowNode implements Node {

    /**
     * 节点id
     */
    private Long nodeId;

    /**
     * 类型
     */
    private FlowTypeEnum type;

    /**
     * 名称
     */
    private String name;

    private Long input;

    private Long output;

    private Map<String, Object> context;

    public FlowNode(NodeDefinition nodeDefinition) {
        this.nodeId = nodeDefinition.getNodeId();
        this.type = FlowTypeEnum.valueOf(nodeDefinition.getType().toUpperCase());
        this.name = nodeDefinition.getName();
        this.input = nodeDefinition.getInput();
        this.output = nodeDefinition.getOutput();
        this.context = nodeDefinition.getContext();
    }

}
