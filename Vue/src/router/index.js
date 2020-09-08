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
