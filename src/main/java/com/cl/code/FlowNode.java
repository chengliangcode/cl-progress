package com.cl.code;

import com.alibaba.fastjson.JSONObject;
import com.cl.code.constant.FlowTypeEnum;
import lombok.Data;

import java.util.Map;

/**
 * @author chengliang
 * @date 2022/8/31 12:00
 */
@Data
public abstract class FlowNode implements Task {

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

    public FlowNode(JSONObject jsonObject) {
        this.nodeId = jsonObject.getLong("nodeId");
        this.type = FlowTypeEnum.valueOf(jsonObject.getString("type").toUpperCase());
        this.name = jsonObject.getString("name");
        this.input = jsonObject.getLong("input");
        this.output = jsonObject.getLong("output");
        this.context = (Map<String, Object>) jsonObject.get("context");
    }
}
