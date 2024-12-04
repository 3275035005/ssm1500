package com.intangible.controller;

import com.intangible.entity.FProject;
import com.intangible.service.ProjectService;
import com.intangible.utils.PageResult;
import com.intangible.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对管理平台提供 非遗项目管理接口
 */
@RequestMapping("project")
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 分页添加查询数据
     * @param current 当前页码
     * @param size 每页大小
     * @param fProject 查询数据
     * @return
     */
    @PostMapping("pageQuery/{current}/{size}")
    public R pageQuery(@PathVariable int current,
                       @PathVariable int size,
                       @RequestBody FProject fProject){
        PageResult<FProject> pageResult = projectService.pageQuery(fProject, current, size);
        return R.ok().put("list", pageResult);
    }


    /**
     * 新增数据
     * @param fProject
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody FProject fProject){
        projectService.save(fProject);
        return R.ok();
    }

    /**
     * 修改数据
     * @param fProject
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody FProject fProject){
        projectService.update(fProject);
        return R.ok();
    }


    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Integer id){
        projectService.delete(id);
        return R.ok();
    }
}
