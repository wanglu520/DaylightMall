import request from '@/util/myAxios'

export function searchList (data) {
  return request({
    url: 'goods/queryGoods',
    method: 'post',
    data
  })
}
