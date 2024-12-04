import axios from 'axios';
import { req } from './axiosFun';

/**
 * 用户管理
 **/
// 登录接口
export const login = (params) => { return req("post", "/user/login", params) };

// 用户管理-获取列表
export const list = (params) => { return req("post", `/user/pageQuery/${params.page}/${params.limit}`, params) };
// 用户管理-保存（添加）
export const save = (params) => { return req("post", "/user/save", params) };
// 用户管理-保存（编辑）
export const update = (params) => { return req("post", "/user/update", params) };
// 用户管理-删除
export const deleteById = (params) => { return req("delete","/user/delete/"+params) };

// 查询所有用户
export const getUserAll = () => { return req("get","/user/getAll") };

// 修改用户
export const adminUpdate = (params) => { return req("post","/user/update", params) };

// 通过id查询用户
export const getInfo = (id) => { return req("get","/user/getUserById?id="+id) };
