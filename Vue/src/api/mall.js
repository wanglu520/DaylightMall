import request from '@/util/myAxios'

export function region () {
  return request({
    url: 'mall/queryRegion',
    method: 'post'
  })
}
