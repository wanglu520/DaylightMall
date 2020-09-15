import request from '@/util/myAxios'

export function region () {
  return request({
    url: 'mall/queryRegion',
    method: 'post'
  })
}

export function queryBrand (data) {
  return request({
    url: 'mall/queryBrand',
    method: 'post',
    data
  })
}

export function deleteBrand (id) {
  return request({
    url: 'mall/deleteBrand',
    method: 'post',
    data: {
      id: id
    }
  })
}
