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

export function updateBrand (data) {
  return request({
    url: 'mall/updateBrand',
    method: 'post',
    data
  })
}

export function addBrand (data) {
  return request({
    url: 'mall/addBrand',
    method: 'post',
    data
  })
}

const uploadPath = process.env.VUE_APP_BASE_API + '/mall/uploadPhoto'
export { uploadPath }