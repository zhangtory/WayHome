import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Register from '@/components/Register'
import Login from '@/components/Login'
import Dashboard from '@/components/Dashboard'
import AddressList from '@/components/dashboards/AddressList'
import ApplyAddress from '@/components/dashboards/ApplyAddress'
import ResetPassword from '@/components/dashboards/ResetPassword'
import OpenApi from '@/components/dashboards/OpenApi'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: Dashboard,
      children: [
        {
          path: '/',
          name: 'AddressList',
          component: AddressList
        },
        {
          path: 'applyaddress',
          name: 'ApplyAddress',
          component: ApplyAddress
        },
        {
          path: 'resetpassword',
          name: 'ResetPassword',
          component: ResetPassword
        },
        {
          path: 'openapi',
          name: 'OpenAPI',
          component: OpenApi
        }
      ]
    }
  ]
})
