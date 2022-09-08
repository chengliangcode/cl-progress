package com.cl.code;

import com.cl.code.core.FlowService;
import com.cl.code.service.TaskService;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author chengliang
 * @date 2022/9/1 11:42
 */
@Component
public class FlowEngine implements ApplicationContextAware, ApplicationListener<ApplicationPreparedEvent> {
    private static ApplicationContext applicationContext;
    private static FlowEngine flowEngine;

    private FlowService flowService;
    private TaskService taskService;

    private FlowEngine() {
    }

    public FlowEngine(FlowService flowService, TaskService taskService) {
        this.flowService = flowService;
        this.taskService = taskService;
    }

    public static FlowEngine getFlowEngine() {
        return flowEngine;
    }

    public FlowService getFlowService() {
        return flowService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        FlowEngine.applicationContext = applicationContext;
        FlowService f = applicationContext.getBean(FlowService.class);
        TaskService t = applicationContext.getBean(TaskService.class);
        FlowEngine.flowEngine = new FlowEngine(f, t);
    }

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        FlowEngine.applicationContext = event.getApplicationContext();
    }
}
