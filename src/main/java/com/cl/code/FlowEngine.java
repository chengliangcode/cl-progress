package com.cl.code;

import com.cl.code.core.FlowService;
import com.cl.code.core.HistoryService;
import com.cl.code.core.TaskService;
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
    private HistoryService historyService;

    private FlowEngine() {
    }

    public FlowEngine(FlowService flowService, TaskService taskService, HistoryService historyService) {
        this.flowService = flowService;
        this.taskService = taskService;
        this.historyService = historyService;
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

    public HistoryService getHistoryService() {
        return historyService;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        FlowEngine.applicationContext = applicationContext;

        FlowService f = applicationContext.getBean(FlowService.class);
        TaskService t = applicationContext.getBean(TaskService.class);
        HistoryService h = applicationContext.getBean(HistoryService.class);

        FlowEngine.flowEngine = new FlowEngine(f, t, h);

    }

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        FlowEngine.applicationContext = event.getApplicationContext();
    }

}
