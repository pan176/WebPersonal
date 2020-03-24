import request from '@/utils/request'

export function list() {
  return request({
    url: '/role/list',
    method: 'get'
  })
}

export function deleteRole(id) {
  return request({
    url: '/role/delete',
    method: 'get',
    params: {
      id: id
    }
  })
}

export function updateRole(data) {
  return request({
    url: '/role/update',
    method: 'post',
    data
  })
}

export function addRole(data) {
  return request({
    url: '/role/add',
    method: 'post',
    data
  })
}

