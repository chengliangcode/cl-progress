package com.cl.code;

import com.cl.code.operation.SimpleGrantedOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author chengliang
 * @date 2022/8/26 15:51
 */
@SpringBootApplication
public class ProgressMain {

    public static void main(String[] args) {
        SpringApplication.run(ProgressMain.class, args);
        String json;
        try {
            InputStream is = Files.newInputStream(Paths.get("flow.json"));
            byte[] buff = new byte[1024];
            int len = 0;
            while (len != -1) {
                len = is.read(buff);
            }
            json = new String(buff);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//
//        Long flowId = FlowEngine.getFlowEngine().getFlowService().executeFlow(json);
//        System.out.println(flowId);

        FlowEngine.getFlowEngine().getFlowService().executeTask(6975346865135026176L, 3L, 6975347028683522048L, new SimpleGrantedOperation("agree"), null);

        // TODO 查询执行历史记录

        // TODO 用标识获取当前人员的任务

        // TODO node id 策略


    }

}
