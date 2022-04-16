package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.util.CommunityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

//-----------------------------------------------------------------------------------
//引入测试注解
@SpringBootTest
//配置类
@ContextConfiguration(classes = CommunityApplication.class)
//-----------------------------------------------------------------------------------
public class MapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelectUser()
    {
        User user= userMapper.selectById(101);
        System.out.println(user);
        user=userMapper.selectByName("liubei");
        System.out.println(user);
        user=userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }
    @Test
    public void testInsertUser()
    {
        User user=new User();
        user.setUsername("bob");
        user.setPassword("123456");
        user.setSalt("asdfaf");
        user.setEmail("123142134@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows=userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }
    @Test
    public void testUpdateUser()
    {
        System.out.println(userMapper.selectById(150));
        int rows=userMapper.updateStatus(150,1);
        System.out.println(rows);
        System.out.println("FINISH1");
        rows=userMapper.updateHeader(150,"http://www.nowcoder.com/105.png");
        System.out.println(rows);
        System.out.println("FINISH2");
        rows=userMapper.updatePassword(150,"cklcklckl");
        System.out.println(rows);
        System.out.println("FINISH3");
        System.out.println(userMapper.selectById(150));
    }
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Test
    public void testDiscussPost()
    {
        List<DiscussPost> list= discussPostMapper.selectDiscussPost(149,0,10);
        for(DiscussPost i:list)
        {
            System.out.println(i);
        }
        System.out.println(discussPostMapper.selectDiscussPostRows(0));
    }
    @Autowired
    DiscussPostService discussPostService;
    @Test
    public void testPage()
    {
        Page page=new Page();
        page.setRows(discussPostService.findDiscussPostCount(0));
        page.setPath("/index");
        System.out.println(page.getTotal());
        System.out.println(page.getTo());
        System.out.println(page.getFrom());
    }
    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Test
    public void testInsertLoginticker()
    {
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000*60));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }
    @Test
    public void testSelectAndUpdateLoginTicket()
    {
        System.out.println(loginTicketMapper.selectByTicket("abc"));
        loginTicketMapper.updateStatus("abc",1);
        System.out.println(loginTicketMapper.selectByTicket("abc"));
    }
    @Test
    public void testPassword()
    {
        User user= userMapper.selectByName("20011207");
        System.out.println(user.getPassword());
        String s="123456";
        System.out.println(CommunityUtil.MD5(s+user.getSalt()).equals(user.getPassword()));
    }
}
