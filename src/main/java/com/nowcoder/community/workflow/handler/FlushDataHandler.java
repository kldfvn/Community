package com.nowcoder.community.workflow.handler;

import com.nowcoder.community.workflow.entity.SellConstant;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class FlushDataHandler implements JavaDelegate, SellConstant {
    @Autowired
    TaskService taskService;
    @Autowired
    RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution execution) {
        Map<String, Object> variables = execution.getVariables();
        log.info("清除数据：{}", variables);
        variables.put(BUYER_OK,null);
        variables.put(SELLER_OK,null);
        variables.put(SENT_ADDEREE,null);
        variables.put(BUYER,null);
        execution.setVariables(variables);
        log.info("清除数据成功：{}", execution.getVariables());
    }
}
