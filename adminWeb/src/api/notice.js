import { req } from './axiosFun';

/**
 * 公告信息
 **/
// 分页条件查询
export const list = (params) => { return req("post",`/notice/pageQuery/${params.page}/${params.limit}`, params) };
// 添加
export const save = (params) => { return req("post", "/notice/save", params) };
// 修改
export const update = (params) => { return req("post", "/notice/update", params) };
// 删除
export const deleteById = (params) => { return req("delete","/notice/delete/"+params) };



