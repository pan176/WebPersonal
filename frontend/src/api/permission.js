import request from '@/utils/request'

export function getPermissionList(data) {
  return request({
    url: '/permission/list',
    method: 'get'
  })
}

