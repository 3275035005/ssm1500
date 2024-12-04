package com.intangible.controller;

import com.intangible.entity.Users;
import com.intangible.service.UsersService;
import com.intangible.utils.PageResult;
import com.intangible.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对管理平台提供 用户管理接口
 */
@RequestMapping("user")
@RestController
public class UsersController {

    @Autowired
    private UsersService userService;

    /**
     * 管理平台登录接口
     */
    @PostMapping("login")
    public R login(@RequestBody Users users){
        Users userss = userService.getUsersByUsername(users.getUsername());
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
        if(!"2".equals(userss.getRole())){
            return R.error("请登录管理员账号");
        }
        return R.ok().put("row", userss);
    }

    /**
     * 通过用户表id查询数据
     */
    @GetMapping("getUserById")
    public R getUserById(Integer id){
        Users userss = userService.getById(id);
        return R.ok().put("row", userss);
    }

    /**
     * 通过用户表id查询数据
     */
    @GetMapping("getAll")
    public R getAll(){
        List<Users> all = userService.getAll();

        return R.ok().put("row", all);
    }


    /**
     * 分页添加查询数据
     * @param current 当前页码
     * @param size 每页大小
     * @param user 查询数据
     * @return
     */
    @PostMapping("pageQuery/{current}/{size}")
    public R pageQuery(@PathVariable int current,
                       @PathVariable int size,
                       @RequestBody Users user){
        PageResult<Users> pageResult = userService.pageQuery(user, current, size);
        return R.ok().put("list", pageResult);
    }


    /**
     * 新增数据
     * @param user
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody Users user){
        userService.save(user);
        return R.ok();
    }

    /**
     * 修改数据
     * @param user
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody Users user){
        userService.update(user);
        return R.ok();
    }


    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Integer id){
        userService.delete(id);
        return R.ok();
    }
}
