package com.nowcoder.community.workflow.sell;

import com.nowcoder.community.workflow.entity.SellConstant;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Slf4j
@Controller
public class ManagerController implements SellConstant {
    @Autowired
    TaskService taskService;
    @Autowired
    RuntimeService runtimeService;
    public void review(Task task, boolean pass){
        Map<String, Object> variables = taskService.getVariables(task.getId());
        variables.put(MANAGER_PASS,pass);
        if (pass){
            log.info("审批成功：通过 {}",task);
        }else {
            log.info("审批成功：不通过 {}",task);
        }
        taskService.complete(task.getId(),variables);
    }
}
