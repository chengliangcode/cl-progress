package com.cl.code;

import lombok.Data;

import java.util.Map;

/**
 * @author chengliang
 * @date 2022/9/5 15:10
 */
@Data
public class NodeDefinition {

    /**
     * 节点id
     */
    private Long nodeId;

    /**
     * 类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 输入
     */
    private Long input;

    /**
     * 输出
     */
    private Long output;

    /**
     * 上下文
     */
    private Map<String, Object> properties;

}
