import request from '@/util/myAxios'

export function searchList (data) {
  return request({
    url: 'mall/queryBrand',
    method: 'post',
    data
  })
}
