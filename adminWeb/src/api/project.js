import { req } from './axiosFun';

/**
 * 非遗项目信息
 **/
// 分页条件查询
export const list = (params) => { return req("post",`/project/pageQuery/${params.page}/${params.limit}`, params) };
// 添加
export const save = (params) => { return req("post", "/project/save", params) };
// 修改
export const update = (params) => { return req("post", "/project/update", params) };
// 删除
export const deleteById = (params) => { return req("delete","/project/delete/"+params) };



