package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.pojo.Post;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.service.PostService;
import com.moodright.blurryworld.utils.PaginationUtil;
import com.moodright.blurryworld.utils.PostPaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author moodright
 * @date 2021/3/17
 */

@Controller
@RequestMapping("platform")
public class CreateCenterController {

    PostService postService;
    PostPaginationUtil postPaginationUtil;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
    @Autowired
    public void setPostPaginationUtil(PostPaginationUtil postPaginationUtil) {
        this.postPaginationUtil = postPaginationUtil;
        this.postPaginationUtil.getPaginationInfo().put("startIndex", 0);
        this.postPaginationUtil.getPaginationInfo().put("pageSize", 5);
    }

    @GetMapping
    public String createCenterIndex() {
        return "/user/post-management/create-center";
    }

    @GetMapping(path = {"post", "post/{pn}"})
    public String postManagementIndex(@PathVariable(name = "pn", required = false)Integer pageNumber, HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        // 封装作者编号
        postPaginationUtil.getPaginationInfo().computeIfAbsent("authorId", k -> user.getUserId());
        // 封装文章总数
        postPaginationUtil.getPaginationInfo().put("totalPostsCount", postService.queryPostsCountByAuthorId(user.getUserId()));
        // 封装总页数
        postPaginationUtil.getPaginationInfo().put("totalPagesCount", (int)Math.ceil(postPaginationUtil.getPaginationInfo().get("totalPostsCount") / (double)postPaginationUtil.getPaginationInfo().get("pageSize")));
        // 封装尾页
        postPaginationUtil.getPaginationInfo().put("lastPage", postPaginationUtil.getPaginationInfo().get("totalPagesCount"));
        // pageNumber空值初始化
        if(pageNumber == null) {
            pageNumber = 0;
        }
        // 封装上一页
        if(pageNumber == 0 || pageNumber == 1) {
            postPaginationUtil.getPaginationInfo().put("prevPage", null);
        }else {
            postPaginationUtil.getPaginationInfo().put("prevPage", pageNumber - 1);
        }
        // 封装下一页
        if(pageNumber != 0 && pageNumber < postPaginationUtil.getPaginationInfo().get("lastPage")) {
            postPaginationUtil.getPaginationInfo().put("nextPage", pageNumber + 1);
        } else if (pageNumber == 0){
            postPaginationUtil.getPaginationInfo().put("nextPage", 2);
        } else if(pageNumber.equals(postPaginationUtil.getPaginationInfo().get("lastPage"))) {
            postPaginationUtil.getPaginationInfo().put("nextPage", null);
        }
        // 封装查询信息
        if(pageNumber != 0) {
            postPaginationUtil.getPaginationInfo().put("startIndex", (pageNumber - 1) * postPaginationUtil.getPaginationInfo().get("pageSize"));
        } else {
            postPaginationUtil.getPaginationInfo().put("startIndex", 0);
        }
        List<Post> posts = postService.queryPostsByPagination(postPaginationUtil.getPaginationInfo());
        model.addAttribute("posts", posts);
        model.addAttribute("paginationInfo", postPaginationUtil.getPaginationInfo());
        return "/user/post-management/post-management";
    }


}
