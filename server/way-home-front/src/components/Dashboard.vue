<template>
  <div class="layout">
    <Layout :style="{minHeight: '100vh'}">

      <Sider ref="side1" hide-trigger collapsible :collapsed-width="90" v-model="isCollapsed">
        <Menu active-name="addressList" theme="dark" width="auto" :class="menuitemClasses">
          <MenuItem name="addressList" :to="{name: 'AddressList'}">
            <Icon type="ios-navigate"></Icon>
            钥匙管理
          </MenuItem>
          <MenuItem name="useGuide" :to="{name: 'UseGuide'}">
            <Icon type="ios-book"></Icon>
            使用说明
          </MenuItem>
          <MenuItem name="OpenApi" :to="{name: 'OpenAPI'}">
            <Icon type="ios-settings"></Icon>
            Github
          </MenuItem>
<!--          <MenuItem name="resetPassword">-->
<!--            <Icon type="ios-key-outline"/>-->
<!--            <span><router-link :to="{name: 'ResetPassword'}" tag="li">修改密码</router-link></span>-->
<!--          </MenuItem>-->
          <MenuItem name="exit" :to="{name: 'Exit'}">
            <Icon type="ios-key-outline"/>
            退出
          </MenuItem>
        </Menu>
      </Sider>

      <Layout>
        <Header :style="{padding: 0}" class="layout-header-bar">
          <Icon @click.native="collapsedSider" :class="rotateIcon" :style="{margin: '0 20px'}" type="md-menu"
                size="24"></Icon>
        </Header>
        <Content :style="{margin: '20px', background: '#fff'}">
          <router-view></router-view>
        </Content>
      </Layout>

    </Layout>
  </div>
</template>

<script>
  import router from "@/router";

  export default {
    name: "Dashboard",
    data() {
      return {
        isCollapsed: false
      }
    },
    computed: {
      rotateIcon() {
        return [
          'menu-icon',
          this.isCollapsed ? 'rotate-icon' : ''
        ];
      },
      menuitemClasses() {
        return [
          'menu-item',
          this.isCollapsed ? 'collapsed-menu' : ''
        ]
      }
    },
    methods: {
      collapsedSider() {
        this.$refs.side1.toggleCollapse();
      },
      exit() {
        localStorage.removeItem("Authorization");
        router.replace({
          path: '/'
        })
      }
    }
  }
</script>

<style scoped>
  .layout {
    border: 0px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }

  .layout-header-bar {
    background: #fff;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .1);
  }

  .menu-item span {
    display: inline-block;
    overflow: hidden;
    width: 69px;
    text-overflow: ellipsis;
    white-space: nowrap;
    vertical-align: bottom;
    transition: width .2s ease .2s;
  }

  .menu-item i {
    transform: translateX(0px);
    transition: font-size .2s ease, transform .2s ease;
    vertical-align: middle;
    font-size: 16px;
  }

  .collapsed-menu span {
    width: 0px;
    transition: width .2s ease;
  }

  .collapsed-menu i {
    transform: translateX(5px);
    transition: font-size .2s ease .2s, transform .2s ease .2s;
    vertical-align: middle;
    font-size: 22px;
  }
</style>
