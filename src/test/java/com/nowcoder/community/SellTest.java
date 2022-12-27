package com.nowcoder.community;

import com.nowcoder.community.workflow.entity.SellConstant;
import com.nowcoder.community.workflow.entity.SellObject;
import com.nowcoder.community.workflow.entity.SentObject;
import com.nowcoder.community.workflow.sell.BuyerController;
import com.nowcoder.community.workflow.sell.CommonController;
import com.nowcoder.community.workflow.sell.ManagerController;
import com.nowcoder.community.workflow.sell.SellerController;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class SellTest implements SellConstant {
    @Autowired
    BuyerController buyerController;
    @Autowired
    SellerController sellerController;
    @Autowired
    CommonController commonController;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    ManagerController managerController;
    @Autowired
    TaskService taskService;
    private static final Integer userId = 123;
    private static final Integer managerId = 456;
    private static final Integer buyerId = 789;

    /**
     * 查看部署
     */
    @Test
    public void t1() {
        System.out.println("查看部署");
        List<ProcessDefinition> process = commonController.getProcess();
        for (ProcessDefinition definition : process) {
            System.out.println("definition = " + definition);
        }
    }

    /**
     * 发布任务
     */
    @Test
    public void t2() {
        System.out.println("发布任务");
        sellerController.publish(userId, managerId);
    }

    /**
     * 编辑商品
     */
    @Test
    public void t3() {
        System.out.println("编辑商品");
        List<Task> list = commonController.getTask(userId);
        for (Task task : list) {
            System.out.println("task.getTaskDefinitionKey() = " + task.getTaskDefinitionKey());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("Variables" + taskService.getVariables(task.getId()));
            SellObject sellObject=new SellObject("产品1",100,"test");
            sellerController.edit(task,sellObject);
        }
    }

    /**
     * 审核任务
     */
    @Test
    public void t4() {
        System.out.println("审核任务");
        List<Task> list = commonController.getTask(managerId);
        for (Task task : list) {
            System.out.println("task.getTaskDefinitionKey() = " + task.getTaskDefinitionKey());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("Variables" + taskService.getVariables(task.getId()));
            managerController.review(task,true);
        }
    }
    /**
     * 确定买家
     */
    @Test
    public void t5() {
        System.out.println("Seller执行");
        List<Task> list = commonController.getTask(userId);
        for (Task task : list) {
            System.out.println("task.getTaskDefinitionKey() = " + task.getTaskDefinitionKey());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("Variables" + taskService.getVariables(task.getId()));
            Map<String, Object> variables = taskService.getVariables(task.getId());
            if ((boolean)variables.get(MANAGER_PASS)){
                sellerController.lock(task,buyerId);
            }else {
                sellerController.edit(task,(SellObject)variables.get("SellObject"));
            }
        }
    }
    /**
     * 发货
     */
    @Test
    public void t6(){
        System.out.println("发货");
        List<Task> list = commonController.getTask(userId);
        for (Task task : list) {
            System.out.println("task.getTaskDefinitionKey() = " + task.getTaskDefinitionKey());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("Variables" + taskService.getVariables(task.getId()));
            sellerController.sent(task,new SentObject("北京"),false);
        }
    }

    /**
     * 收货
     */
    @Test
    public void t7(){
        System.out.println("收货");
        List<Task> list = commonController.getTask(buyerId);
        for (Task task : list) {
            System.out.println("task.getTaskDefinitionKey() = " + task.getTaskDefinitionKey());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("Variables" + taskService.getVariables(task.getId()));
            buyerController.receipt(task,false);
        }
    }
    @Test
    public void t99(){
        System.out.println("查看当前任务");
        List<Task> list = commonController.getTask(null);
        for (Task task : list) {
            System.out.println("task.getTaskDefinitionKey() = " + task.getTaskDefinitionKey());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("Variables" + taskService.getVariables(task.getId()));
        }
    }
    /**
     * 清除数据
     */
    @Test
    public void t100() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println("删除："+processDefinition.getDeploymentId());
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
        }
        List<ProcessDefinition> list2 = repositoryService.createProcessDefinitionQuery().list();
        if (list2 == null) {
            System.out.println("delete success");
        } else {
            for (ProcessDefinition processDefinition : list) {
                System.out.println(processDefinition.getId());
            }
        }
    }
}
