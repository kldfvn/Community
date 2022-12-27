package com.nowcoder.community.workflow.sell;

import com.nowcoder.community.util.HostHolder;
import com.nowcoder.community.workflow.entity.SellConstant;
import com.nowcoder.community.workflow.entity.SellObject;
import com.nowcoder.community.workflow.entity.SentObject;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SellerController implements SellConstant {
    private static final Logger log = LoggerFactory.getLogger(SellerController.class);
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    TaskService taskService;

    public void publish(int userId, int managerId) {
        Map<String, Object> variable = new HashMap<>();
        variable.put(SELLER, userId);
        variable.put(MANAGER, managerId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("work", variable);
        log.info("流程启动成功 {}", processInstance.getId());
    }

    public void edit(Task task,SellObject sellObject){
        Map<String, Object> variables = taskService.getVariables(task.getId());
        variables.put(SELL_OBJECT,sellObject);
        taskService.complete(task.getId(),variables);
        log.info("产品编辑成功: {}",task);
    }

    public void lock(Task task,Integer buyer){
        Map<String, Object> variables = taskService.getVariables(task.getId());
        variables.put(BUYER,buyer);
        taskService.complete(task.getId(),variables);
        log.info("产品预定成功: {}",task);
    }

    public void sent(Task task, SentObject sentObject,boolean isSent){
        Map<String, Object> variables = taskService.getVariables(task.getId());
        variables.put(SENT_ADDEREE,sentObject);
        variables.put(SELLER_OK,isSent);
        if (isSent) log.info("发货成功");
        else log.info("发货取消");
        taskService.complete(task.getId(),variables);
    }

}
