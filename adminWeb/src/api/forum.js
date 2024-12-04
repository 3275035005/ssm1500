import { req } from './axiosFun';

/**
 * 论坛信息
 **/
// 分页条件查询
export const list = (params) => { return req("post",`/forum/pageQuery/${params.page}/${params.limit}`, params) };
// 添加
export const save = (params) => { return req("post", "/forum/save", params) };
// 修改
export const update = (params) => { return req("post", "/forum/update", params) };
// 删除
export const deleteById = (params) => { return req("delete","/forum/delete/"+params) };




