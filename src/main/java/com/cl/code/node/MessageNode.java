package com.cl.code.node;

import com.cl.code.core.NodeType;
import com.cl.code.property.NullProperty;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @author chengliang
 * @date 2022/9/7 16:21
 */
@Component
@Getter
public class MessageNode implements NodeType<NullProperty> {

    private final String type = "message";

}
