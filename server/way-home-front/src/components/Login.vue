<template>
  <div class="login">
    <Row type="flex" justify="center">
      <Col>
        <h1>WayHome - 登录</h1>
        <Form ref="formData" :model="formData" :rules="rule">
          <FormItem prop="username">
            <Input clearable size="large" placeholder="请输入用户名" :maxlength="20" prefix="ios-person-outline"
                   v-model="formData.username"/>
          </FormItem>
          <FormItem prop="password">
            <Input type="password" password size="large" placeholder="请输入密码" :maxlength="20" prefix="ios-lock-outline"
                   v-model="formData.password"/>
          </FormItem>
          <FormItem v-if="msg">
            <label class="warning">
              <Icon class="warning" type="ios-information-circle-outline"/>
              {{msg}}</label>
          </FormItem>
          <Button type="success" size="large" @click="login('formData')">登录</Button>
          <router-link to="/register">没有账号？马上注册</router-link>
          <span>&nbsp; | &nbsp;</span>
          <router-link to="/account/find/send">找回密码</router-link>
        </Form>
      </Col>
    </Row>
  </div>
</template>

<script>
  export default {
    name: "Login",
    data() {
      return {
        msg: '',
        formData: {
          username: '',
          password: ''
        },
        rule: {
          username: [
            {required: true, message: '请输入用户名', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {type: 'string', min: 6, message: '密码不能少于6位', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      login(form) {
        this.$refs[form].validate((valid) => {
          if (valid) {
            this.axios.post('/admin/user/login', {
              username: this.formData.username,
              password: this.formData.password
            }).then(response => {
              if (response.data['code'] === 0) {
                // 保存token，跳转到控制台
                localStorage.setItem("Authorization", response.data['data']);
                this.$router.push({name: 'AddressList'});
              } else {
                this.msg = response.data['message'];
              }
            }).catch(function (error) {
              console.log(error);
            })
          }
        });
      }
    }
  }
</script>

<style scoped>
  h1 {
    color: lightcyan;
    margin-bottom: 3vh;
  }

  .login {
    width: 100vw;
    height: 100vh;
    background-image: url("../../static/stars_bg.jpg");
    background-size: cover;
    background-position: center;

    text-align: center;

    display: flex;
    align-items: center;
    justify-content: center;
  }

  .warning {
    color: red;
  }
</style>
