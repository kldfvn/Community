package com.nowcoder.community.service;

import com.nowcoder.community.entity.DiscussPost;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchService {
    public void saveDiscussPost(DiscussPost discussPost)
    {

    }
    public void deleteDiscussPost(int id)
    {

    }
    public Page<DiscussPost> searchDiscussPost(String key,int current,int limit)
    {
        return null;
    }
}
