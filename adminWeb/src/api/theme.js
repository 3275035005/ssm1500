import { req } from './axiosFun';

/**
 * 主题信息
 **/
// 分页条件查询
export const list = (params) => { return req("post",`/theme/pageQuery/${params.page}/${params.limit}`, params) };
// 添加
export const save = (params) => { return req("post", "/theme/save", params) };
// 修改
export const update = (params) => { return req("post", "/theme/update", params) };
// 删除
export const deleteById = (params) => { return req("delete","/theme/delete/"+params) };



