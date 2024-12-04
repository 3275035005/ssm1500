import { req } from './axiosFun';

/**
 * 非遗传承人信息
 **/
// 分页条件查询
export const list = (params) => { return req("post",`/inheritor/pageQuery/${params.page}/${params.limit}`, params) };
// 添加
export const save = (params) => { return req("post", "/inheritor/save", params) };
// 修改
export const update = (params) => { return req("post", "/inheritor/update", params) };
// 删除
export const deleteById = (params) => { return req("delete","/inheritor/delete/"+params) };

// 查询所有
export const getAll = () => { return req("get","/inheritor/getAll") };


