package com.nowcoder.community.workflow.sell;

import com.nowcoder.community.workflow.entity.SellConstant;
import com.nowcoder.community.workflow.entity.SellObject;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@Slf4j
public class BuyerController implements SellConstant {
    @Autowired
    TaskService taskService;
    public void receipt(Task task,boolean isReceipt){
        Map<String, Object> variables = taskService.getVariables(task.getId());
        variables.put(BUYER_OK,isReceipt);
        if (isReceipt) log.info("收货成功");
        else log.info("退货成功");
        taskService.complete(task.getId(),variables);
    }
}
