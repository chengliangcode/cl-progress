package com.cl.code.dao;

import com.cl.code.model.AgtFlowNode;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Dao 接口
 *
 * @author chengliang
 * @since 2022-09-03
 */
@org.apache.ibatis.annotations.Mapper
public interface AgtFlowNodeDao extends Mapper<AgtFlowNode> {

    /**
     * 批量插入
     *
     * @param agtFlowNodeList agt流节点列表
     */
    void batchInsert(@Param("agtFlowNodeList") List<AgtFlowNode> agtFlowNodeList);

}
