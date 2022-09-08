package com.cl.code.node;

import com.cl.code.core.NodeType;
import com.cl.code.property.NullProperty;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @author chengliang
 * @date 2022/9/7 15:41
 */
@Component
@Getter
public class StartNode implements NodeType<NullProperty> {

    private final String type = "start";

}
