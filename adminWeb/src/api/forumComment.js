// 删除
import {req} from "./axiosFun";

export const deleteForumCommentById = (params) => { return req("delete","/forumComment/delete/"+params) };


export const getListByForumId = (id) => { return req("get","/forumComment/getListByForumId?forumId="+id) };
