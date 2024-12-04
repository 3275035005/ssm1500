import { req } from './axiosFun';

/**
 * 非遗物品信息
 **/
// 分页条件查询
export const list = (params) => { return req("post",`/goods/pageQuery/${params.page}/${params.limit}`, params) };
// 添加
export const save = (params) => { return req("post", "/goods/save", params) };
// 修改
export const update = (params) => { return req("post", "/goods/update", params) };
// 删除
export const deleteById = (params) => { return req("delete","/goods/delete/"+params) };


