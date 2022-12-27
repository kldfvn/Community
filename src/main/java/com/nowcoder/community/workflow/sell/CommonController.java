package com.nowcoder.community.workflow.sell;

import com.nowcoder.community.util.HostHolder;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    HostHolder hostHolder;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    TaskService taskService;

    @Autowired
    RuntimeService runtimeService;

    public List<Task> getTask(Integer userId) {
        if (userId == null) {
            return taskService.createTaskQuery()
                    .list();
        } else {
            return taskService.createTaskQuery()
                    .taskAssignee(String.valueOf(userId))
                    .list();
        }
    }

    public List<ProcessDefinition> getProcess() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
//                .latestVersion()
                .list();
        return list;
    }
}
