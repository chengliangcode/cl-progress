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

    private Long in;

    private Long out;

    private Map<String, Object> context;

    public FlowNode(JSONObject jsonObject) {
        this.nodeId = jsonObject.getLong("nodeId");
        this.type = FlowTypeEnum.valueOf(jsonObject.getString("type").toUpperCase());
        this.name = jsonObject.getString("name");
        this.in = jsonObject.getLong("in");
        this.out = jsonObject.getLong("out");
        this.context = (Map<String, Object>) jsonObject.get("context");
    }
}
