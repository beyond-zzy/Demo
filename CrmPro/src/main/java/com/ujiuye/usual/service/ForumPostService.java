package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Forumpost;
import com.ujiuye.usual.bean.ForumpostExample;

import java.util.List;

public interface ForumPostService {


       public boolean insertInfo(Forumpost forumpost);

        public List<Forumpost> getForumpostByEid(ForumpostExample forumpostExample);

        // 获取一个帖子的所有信息
        public Forumpost getForumAndAllEval(Integer id);




}
