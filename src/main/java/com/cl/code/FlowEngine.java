package com.cl.code;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cl.code.dao.AgtFlowDao;
import com.cl.code.dao.AgtFlowNodeDao;
import com.cl.code.dao.AgtFlowRecordDao;
import com.cl.code.model.AgtFlow;
import com.cl.code.model.AgtFlowNode;
import com.cl.code.util.TwiterIdUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chengliang
 * @date 2022/9/1 11:42
 */
@Component
public class FlowEngine implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    public static AgtFlowDao agtFlowDao;
    public static AgtFlowNodeDao agtFlowNodeDao;
    public static AgtFlowRecordDao agtFlowRecordDao;

    public static Flow buildFlow(String json) {
        List<String> flowStrArray = JSON.parseArray(json, String.class);
        if (flowStrArray.size() == 0) {
            throw new RuntimeException("流程为空");
        }
        List<JSONObject> collect = flowStrArray.stream().map(JSON::parseObject).collect(Collectors.toList());
//        Map<Long, FlowNode> map = collect.stream().map(item -> {
//            FlowTypeEnum type = FlowTypeEnum.valueOf(item.getObject("type", String.class).toUpperCase());
//            switch (type) {
//                case START:
//                    return new StartNode(item);
//                case END:
//                    return new EndNode(item);
//                case USER:
//                    return new UserNode(item);
//                case MESSAGE:
//                    return new MessageNode(item);
//                default:
//                    throw new RuntimeException("存在不支持的节点类型");
//            }
//        }).collect(Collectors.toMap(FlowNode::getNodeId, Function.identity(), (k1, k2) -> k1));

//        Map<Long, String> map = collect.stream().collect(Collectors.toMap(i -> i.getLong("nodeId"), JSONObject::toJSONString, (k1, k2) -> k1));
        Map<Long, String> map = collect.stream().collect(Collectors.toMap(item -> item.getLong("nodeId"), item -> item.toJSONString(), (k1, k2) -> k1));

        if (flowStrArray.size() != map.size()) {
            throw new RuntimeException("nodeId重复");
        }
        // TODO flowNode 格式校验
        // TODO 开启数据库事务
        // 生成 flowId
        Long flowId = TwiterIdUtil.getTwiterId();
        // 持久化flow
        agtFlowDao.insert(new AgtFlow(flowId, json));
        // 持久化 flowNode
        collect.forEach(jsonObject -> {
            agtFlowNodeDao.insert(new AgtFlowNode(
                    jsonObject.getLong("nodeId"),
                    flowId,
                    jsonObject.getLong("input"),
                    jsonObject.getLong("output"),
                    jsonObject.getString("type"),
                    jsonObject.getString("name"),
                    JSON.toJSONString(jsonObject.get("context"))
            ));
        });
        return new Flow(flowId, map);
    }

    public static Flow findFlow(Long flowId) {
        AgtFlow agtFlow = agtFlowDao.selectByPrimaryKey(flowId);

//        agtFlowNodeDao.se
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        FlowEngine.applicationContext = applicationContext;
        FlowEngine.agtFlowDao = applicationContext.getBean(AgtFlowDao.class);
        FlowEngine.agtFlowNodeDao = applicationContext.getBean(AgtFlowNodeDao.class);
        FlowEngine.agtFlowRecordDao = applicationContext.getBean(AgtFlowRecordDao.class);
    }


}
