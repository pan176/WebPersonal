import request from '@/utils/request'

export function update(data) {
  return request({
    url: '/resume/update',
    method: 'post',
    data
  })
}

export function resume() {
  return request({
    url: '/resume',
    method: 'get'
  })
}
