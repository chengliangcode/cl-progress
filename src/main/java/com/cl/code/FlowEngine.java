package com.cl.code;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cl.code.constant.FlowTypeEnum;
import com.cl.code.dao.AgtFlowDao;
import com.cl.code.node.EndNode;
import com.cl.code.node.MessageNode;
import com.cl.code.node.StartNode;
import com.cl.code.node.UserNode;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author chengliang
 * @date 2022/9/1 11:42
 */
public class FlowEngine {

    public static Flow buildFlow(String json, ApplicationContext springContext) {
        List<String> flowStrArray = JSON.parseArray(json, String.class);
        if (flowStrArray.size() == 0) {
            throw new RuntimeException("流程为空");
        }
        List<JSONObject> collect = flowStrArray.stream().map(JSON::parseObject).collect(Collectors.toList());
        Map<Long, FlowNode> map = collect.stream().map(item -> {
            FlowTypeEnum type = FlowTypeEnum.valueOf(item.getObject("type", String.class).toUpperCase());
            switch (type) {
                case START:
                    return new StartNode(item);
                case END:
                    return new EndNode(item);
                case USER:
                    return new UserNode(item);
                case MESSAGE:
                    return new MessageNode(item);
                default:
                    throw new RuntimeException("存在不支持的节点类型");
            }
        }).collect(Collectors.toMap(FlowNode::getNodeId, Function.identity(), (k1, k2) -> k1));
        if (flowStrArray.size() != map.size()) {
            throw new RuntimeException("nodeId重复");
        }
        AgtFlowDao agtFlowDao = springContext.getBean(AgtFlowDao.class);
        return new Flow(agtFlowDao, 111111111L, map);
    }

}
