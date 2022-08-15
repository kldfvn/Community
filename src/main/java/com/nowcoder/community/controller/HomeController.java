package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nowcoder.community.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        //方法调用前，springmvc急哦自动实例化model和page，并将page注入model
//        所以在thymekeaf中可以直接访问page对象中的数据
        page.setRows(discussPostService.findDiscussPostCount(0));
        page.setPath("/index");
        List<DiscussPost> list = discussPostService.findDiscussPost(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPost = new ArrayList<>();
        if (list != null) {
            for (DiscussPost i : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", i);
                User user = userService.findUserById(i.getUserId());
                map.put("user", user);
                discussPost.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPost);
        return "/index";
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String getErrorPages() {
        return "error/500";
    }
}
