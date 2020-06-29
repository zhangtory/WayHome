import Vue from 'vue'
import Router from 'vue-router'
import Index from "../components/Index";
import KeyList from "../components/KeyList";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/index',
    },
    {
      path: '/index',
      name: 'Index',
      component: Index,
    },
    {
      path: '/keyList',
      name: 'KeyList',
      component: KeyList,
    }
  ]
})
