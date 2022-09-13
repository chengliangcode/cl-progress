package com.cl.code.actuator;

import com.cl.code.FlowEngine;
import com.cl.code.core.NodeActuator;
import com.cl.code.core.NodeType;
import com.cl.code.factory.NodeActuatorFactory;
import com.cl.code.property.NodeProperty;
import lombok.Data;

import java.lang.reflect.ParameterizedType;

/**
 * @author chengliang
 * @date 2022/8/31 12:00
 */
@Data
public abstract class FlowNodeActuator<T extends NodeType<V>, V extends NodeProperty> implements NodeActuator<T, V> {

    /**
     * 注册到工厂
     */
    @Override
    @SuppressWarnings("all")
    public void afterPropertiesSet() {
        Class<T> aClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        NodeType<V> bean = FlowEngine.getApplicationContext().getBean(aClass);
        NodeActuatorFactory.register(bean.getType(), this);
    }

}
