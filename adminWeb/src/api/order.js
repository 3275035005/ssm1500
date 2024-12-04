import { req } from './axiosFun';

/**
 * 非遗物品订单
 **/
// 分页条件查询
export const list = (params) => { return req("post",`/order/pageQuery/${params.page}/${params.limit}`, params) };
// 修改
export const update = (params) => { return req("post", "/order/update", params) };
// 删除
export const deleteById = (params) => { return req("delete","/order/delete/"+params) };


