package com.cl.code;

/**
 * @author chengliang
 * @date 2022/9/1 10:48
 */
public interface NodeActuator {

    /**
     * 执行
     *
     * @param flow           flow
     * @param nodeDefinition 节点定义
     * @return {@code Long}
     */
    Long execute(NodeDefinition nodeDefinition, Flow flow);

}
