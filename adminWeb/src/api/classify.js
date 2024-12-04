import { req } from './axiosFun';

/**
 * 分类信息
 **/
// 分页条件查询
export const list = (params) => { return req("post",`/classify/pageQuery/${params.page}/${params.limit}`, params) };
// 添加
export const save = (params) => { return req("post", "/classify/save", params) };
// 修改
export const update = (params) => { return req("post", "/classify/update", params) };
// 删除
export const deleteById = (params) => { return req("delete","/classify/delete/"+params) };

export const getClassifyAll = () => { return req("get","/classify/getClassifyAll") };


