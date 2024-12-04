
import request from '@/utils/request';
// 用户登录
export function userLogin(query) {
    return request({
        url: '/userWeb/login',
        method: 'post',
        data:query
    });
}
// 通过用户id获取用户信息
export function getUserInfo(id) {
    return request({
        url: "/user/getUserById?id="+id,
        method: 'get'
    });
}
// 修改用户信息
export function updateUser(query) {
    return request({
        url: '/user/update',
        method: 'post',
        data:query
    });
}

// 用户注册
export function saveUser(query) {
    return request({
        url: '/userWeb/register',
        method: 'post',
        data:query
    });
}

// 首页数据查询
export function getHome(){
    return request({
        url: '/userWeb/home',
        method: 'get'
    });
}

// 查询非遗传承人
export function inheritorPageQuery(currentPage, pageSize, query) {
    return request({
        url: `/inheritor/pageQuery/${currentPage}/${pageSize}`,
        method: 'post',
        data:query
    });
}

// 查询非遗项目
export function projectPageQuery(currentPage, pageSize, query) {
    return request({
        url: `/project/pageQuery/${currentPage}/${pageSize}`,
        method: 'post',
        data:query
    });
}

// 热门主题
export function themePageQuery(currentPage, pageSize, query) {
    return request({
        url: `/theme/pageQuery/${currentPage}/${pageSize}`,
        method: 'post',
        data:query
    });
}

// 政策通知
export function noticePageQuery(currentPage, pageSize, query) {
    return request({
        url: `/notice/pageQuery/${currentPage}/${pageSize}`,
        method: 'post',
        data:query
    });
}

// 热门主题详情查询
export function getThemeById(id) {
    return request({
        url: "/userWeb/getThemeById?id="+id,
        method: 'get'
    });
}

// 非遗项目详情查询
export function getProjectById(id) {
    return request({
        url: "/userWeb/getProjectById?id="+id,
        method: 'get'
    });
}

// 非遗传承人详情查询
export function getInheritorById(id) {
    return request({
        url: "/userWeb/getInheritorById?id="+id,
        method: 'get'
    });
}

// 论坛数据查询
export function getForumAll() {
    return request({
        url: "/userWeb/getForumAll",
        method: 'get'
    });
}
// 查询论坛留言
export function getForumById(id) {
    return request({
        url: "/userWeb/getForumById?id="+id,
        method: 'get'
    });
}

// 提交论坛
export function sendForum(data) {
    return request({
        url: `/userWeb/sendForum`,
        method: 'post',
        data:data
    });
}

// 提交论坛评论
export function sendForumComment(data) {
    return request({
        url: `/userWeb/sendForumComment`,
        method: 'post',
        data:data
    });
}

// 查询地址
export function getAddress(userId) {
    return request({
        url: `/userWeb/getAddress/${userId}`,
        method: 'get'
    });
}


export function addAddress(data) {
    return request({
        url: `/userWeb/addAddress`,
        method: 'post',
        data: data
    });
}

export function updateAddress(data) {
    return request({
        url: `/userWeb/updateAddress`,
        method: 'post',
        data: data
    });
}

export function deleteAddress(id) {
    return request({
        url: `/userWeb/deleteAddress/`+id,
        method: 'post'
    });
}
export function getPageQuery(page, limit,data) {
    return request({
        url: `/goods/pageQuery/${page}/${limit}`,
        method: 'post',
        data
    })
}

export function getGoodsById(id) {
    return request({
        url: `/userWeb/getGoodsById/${id}`,
        method: 'get'
    })
}

export function getOneByProjectIdAndUserId(data) {
    return request({
        url: `/userWeb/getOneByProjectIdAndUserId`,
        method: 'post',
        data: data
    });
}

export function getProjectByUserId(id) {
    return request({
        url: `/userWeb/getProjectByUserId/${id}`,
        method: 'get'
    })
}

export function sendOrder(data) {
    return request({
        url: `/userWeb/sendOrder`,
        method: 'post',
        data: data
    });
}


export function updateOrder(data) {
    return request({
        url: `/userWeb/updateOrder`,
        method: 'post',
        data: data
    });
}

export function getOrderById(id) {
    return request({
        url: `/userWeb/getOrderById/${id}`,
        method: 'get'
    })
}
export function getOrderByUserId(id) {
    return request({
        url: `/userWeb/getOrderByUserId/${id}`,
        method: 'get'
    })
}

export function getClassifyAll() {
    return request({
        url: `/classify/getClassifyAll`,
        method: 'get'
    })
}

