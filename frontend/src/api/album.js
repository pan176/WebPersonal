import request from '@/utils/request'

export function add(data) {
  return request({
    url: '/album/add',
    method: 'post',
    data
  })
}

export function list() {
  return request({
    url: '/album/list',
    method: 'get'
  })
}

export function del(path) {
  return request({
    url: '/album/delete',
    method: 'get',
    params: { path }
  })
}
export function rand(num) {
  return request({
    url: `/album/rand/${num}`,
    method: 'get'
  })
}

export function aliyunDel(path) {
  return request({
    url: '/upload/delete',
    method: 'get',
    params: { path }
  })
}
