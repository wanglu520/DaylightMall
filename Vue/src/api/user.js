import request from '@/util/myAxios'

export function login (data) {
  return request({
    url: 'user/login',
    method: 'post',
    data
  })
}

export function queryMenu (data) {
  return request({
    url: 'menu/queryMenu',
    method: 'post',
    params: data
  })
}

export function userDetail (data) {
  return request({
    url: 'userAdmin/queryUserDetail',
    method: 'post',
    data
  })
}

export function updateDetail (data) {
  return request({
    url: 'userAdmin/updateDetail',
    method: 'post',
    data
  })
}
