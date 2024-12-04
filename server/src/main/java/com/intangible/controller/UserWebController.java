package com.intangible.controller;

import com.intangible.entity.*;
import com.intangible.service.*;
import com.intangible.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对用户平台提供接口
 */
@RequestMapping("userWeb")
@RestController
public class UserWebController {


    @Autowired
    private UsersService usersService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private InheritorService inheritorService;

    @Autowired
    private ForumService forumService;

    @Autowired
    private ForumCommentService forumCommentService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FProjectCollectService fProjectCollectService;

    @Autowired
    private AddressService addressService;

    /**
     * 用户平台登录接口
     */
    @PostMapping("login")
    public R login(@RequestBody Users users){
        Users userss = usersService.getUsersByUsername(users.getUsername());
        if(userss == null){
            return R.error("请输入正确的账号");
        }
        if(!userss.getPassword().equals(users.getPassword())){
            return R.error("请输入正确的密码");
        }
        String status = userss.getStatus();
        if("0".equals(status)){
            return R.error("账号已被禁用");
        }
        if(!"1".equals(userss.getRole())){
            return R.error("请登录用户账号");
        }
        return R.ok().put("row", userss);
    }


    /**
     * 用户平台注册接口
     */
    @PostMapping("register")
    public R register(@RequestBody Users users){
        users.setRole("1");
        users.setStatus("1");
        usersService.save(users);
        return R.ok();
    }

    /**
     * 首页功能查询
     *      1、查询公告信息(数组)
     *      2、查询推荐非遗项目信息
     *      3、查询推荐主题
     */
    @GetMapping("home")
    public R home(){
        // 查询公告信息
        List<String> notice = noticeService.getByType("1");

        // 查询推荐非遗项目信息
        List<FProject> project = projectService.getByRecommendState("1");

        // 查询推荐主题
        List<Theme> theme = themeService.getByRecommendState("1");

        Map<String, Object> map = new HashMap<>();
        map.put("notice",notice);
        map.put("project",project);
        map.put("theme",theme);
        return R.ok().put("data", map);
    }
    /**
     * 非遗项目详情信息
     */
    @GetMapping("getProjectById")
    public R getProjectById(Integer id){
        FProject fProject = projectService.getById(id);
        return R.ok().put("data", fProject);
    }
    /**
     * 非遗传承人详情信息
     */
    @GetMapping("getInheritorById")
    public R getInheritorById(Integer id){
        FInheritor inheritor = inheritorService.getById(id);
        return R.ok().put("data", inheritor);
    }
    /**
     * 主题详情信息
     */
    @GetMapping("getThemeById")
    public R getThemeById(Integer id){
        Theme theme = themeService.getById(id);
        return R.ok().put("data", theme);
    }

    /**
     * 论坛数据查询
     */
    @GetMapping("getForumAll")
    public R getForumAll(){
        List<Forum> list = forumService.getAll();
        return R.ok().put("data", list);
    }

    /**
     * 创建论坛
     * @param forum
     * @return
     */
    @PostMapping("sendForum")
    public R sendForum(@RequestBody Forum forum){
        forumService.save(forum);
        return R.ok();
    }
    /**
     * 论坛评论查询
     * @param id 论坛id
     * @return
     */
    @GetMapping("getForumById")
    public R getForumAll(Integer id){
        List<ForumComment> list = forumCommentService.getListByForumId(id);
        return R.ok().put("data", list);
    }

    /**
     * 论坛评论功能
     */
    @PostMapping("sendForumComment")
    public R sendForumComment(@RequestBody ForumComment forumComment){
        forumCommentService.save(forumComment);
        return R.ok();
    }

    /**
     * 查询用户地址
     */
    @GetMapping("getAddress/{userId}")
    public R getAddress(@PathVariable Integer userId){
        List<Address> addresses = addressService.selectByUserId(userId);
        return R.ok().put("list", addresses);
    }

    /**
     * 修改地址
     */
    @PostMapping("updateAddress")
    public R updateAddress(@RequestBody Address address){
        addressService.update(address);
        return R.ok();
    }


    /**
     * 新增地址
     */
    @PostMapping("addAddress")
    public R addAddress(@RequestBody Address address){
        addressService.save(address);
        return R.ok();
    }


    /**
     * 删除地址
     */
    @PostMapping("deleteAddress/{id}")
    public R deleteAddress(@PathVariable Integer id){
        addressService.delete(id);
        return R.ok();
    }

    /**
     * 非遗物品详情查询
     */
    @GetMapping("getGoodsById/{id}")
    public R getGoodsById(@PathVariable Integer id){
        Goods goods = goodsService.getById(id);
        return R.ok().put("row", goods);
    }


    /**
     * 非遗项目收藏和取消收藏
     */
    @PostMapping("getOneByProjectIdAndUserId")
    public R getOneByProjectIdAndUserId(@RequestBody FProjectCollect fProjectCollect){
        FProjectCollect fProjectCollect1 = fProjectCollectService.getOneByProjectIdAndUserId(fProjectCollect);
        if(fProjectCollect1 == null){
            fProjectCollectService.save(fProjectCollect);
            return R.ok("收藏成功");
        }else{
            fProjectCollectService.delete(fProjectCollect1.getId());
            return R.ok("取消成功");
        }
    }

    /**
     * 查询我的收藏非遗项目
     */
    @GetMapping("getProjectByUserId/{id}")
    public R getProjectByUserId(@PathVariable Integer id){
        List<FProjectCollect> list = fProjectCollectService.getProjectByUserId(id);
        return R.ok().put("data", list);
    }

    /**
     * 新增订单
     */
    @PostMapping("sendOrder")
    public R sendOrder(@RequestBody Order order){
        String orderNo = System.currentTimeMillis()+"";
        order.setOrderNo(orderNo);
        orderService.save(order);
        return R.ok().put("data", orderNo);
    }

    /**
     * 修改订单
     */
    @PostMapping("updateOrder")
    public R updateOrder(@RequestBody Order order){
        orderService.update(order);
        return R.ok();
    }

    /**
     * 查询订单详情
     */
    @GetMapping("getOrderById/{id}")
    public R getOrderById(@PathVariable String id){
        Order order = orderService.getOrderById(id);
        return R.ok().put("row", order);
    }
    /**
     * 查询我的订单
     */
    @GetMapping("getOrderByUserId/{id}")
    public R getOrderByUserId(@PathVariable Integer id){
        List<Order> list = orderService.getOrderByUserId(id);
        return R.ok().put("data", list);
    }

}
