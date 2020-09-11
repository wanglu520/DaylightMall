import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Test'
import Success from '@/components/HelloWorld'
import Test from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/Login'
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
    }, {
      path: '',
      component: Success,
      children: [
        {
          path: 'main',
          component: () => import('@/views/homePage/index'),
          meta: {
            title: '首页'
          }
        }
      ]
    },
    {
      path: '/userManage',
      name: 'userManage',
      component: Success,
      meta: {
        type: 'nogo',
        title: '用户管理'
      },
      children: [
        {
          path: 'user',
          component: () => import('@/views/user/user'),
          name: 'user',
          meta: {
            title: '会员管理'
          }
        }, {
          path: 'address',
          component: () => import('@/views/user/address'),
          name: 'address',
          meta: {
            title: '收货地址'
          }
        }
      ]
    },
    {
      path: '/mallManage',
      name: 'mallManage',
      component: Success,
      meta: {
        type: 'nogo',
        title: '商场管理'
      },
      children: [
        {
          path: 'region',
          component: () => import('@/views/mall/region'),
          name: 'region',
          meta: {
            title: '行政区域'
          }
        }
      ]
    },
    {
      path: '/test',
      name: 'Test',
      component: Test
    }
  ]

})
