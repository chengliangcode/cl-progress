package com.cl.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
        ConfigurableApplicationContext context = SpringApplication.run(ProgressMain.class, args);
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

        Flow flow = FlowEngine.buildFlow(json, context);
        flow.execute();

        // 流程 保存

    }

}
