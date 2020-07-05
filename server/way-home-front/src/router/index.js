import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Index',
      component: resolve => require(['@/components/Index'], resolve)
    },
    {
      path: '/register',
      name: 'Register',
      component: resolve => require(['@/components/Register'], resolve)
    },
    {
      path: '/login',
      name: 'Login',
      component: resolve => require(['@/components/Login'], resolve)
    },
    {
      path: '/go/:userName/:keyName',
      name: 'Go',
      component: resolve => require(['@/components/Go'], resolve)
    },
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: resolve => require(['@/components/Dashboard'], resolve),
      children: [
        {
          path: '/',
          name: 'AddressList',
          component: resolve => require(['@/components/dashboards/AddressList'], resolve)
        },
        {
          path: 'resetpassword',
          name: 'ResetPassword',
          component: resolve => require(['@/components/dashboards/ResetPassword'], resolve)
        },
        {
          path: 'openapi',
          name: 'OpenAPI',
          component: resolve => require(['@/components/dashboards/OpenApi'], resolve)
        },
        {
          path: 'useGuide',
          name: 'UseGuide',
          component: resolve => require(['@/components/UseGuide'], resolve)
        }
      ]
    }
  ]
})
