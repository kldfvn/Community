package com.nowcoder.community.util;

public interface CommunityConstant {
    //激活成功
    int ACTIVATION_SUCCESS = 0;
    //重复激活
    int ACTIVATION_REPEATE = 1;
    //激活失败
    int ACTIVATION_FAILURE = 2;
    //默认登录凭证超时时间
    int DEFAULT_EXPIRE_SECONDS = 3600 * 12;
    //记住状态下的登录凭证超时时间
    int REMEMBER_EXPIRE_SECONDS = 3600 * 12 * 100;

    //    帖子
    int ENTITY_TYPE_POST = 1;
    //    评论
    int ENTITY_TYPE_COMMENT = 2;
    //    用户
    int ENTITY_TYPE_USER = 3;
    //    kafka
//    评论
    String TOPIC_COMMENT = "comment";
    //    点赞
    String TOPIC_LIKE = "like";
    //    关注
    String TOPIC_FOLLOW = "follow";
    //    主题
    String TOPIC_PUBLISH = "publish";
    //    分享
    String TOPIC_SHARE = "share";
    //    系统用户Id
    int SYSTEM_USER_ID = 1;
    //    主题
    String TOPIC_DELETE = "delete";
    //    权限：普通用户
    String AUTHORITY_USER = "user";
    //    权限：管理员
    String AUTHORITY_ADMIN = "admin";
    //    权限：版主
    String AUTHORITY_MODERATOR = "moderator";
}
