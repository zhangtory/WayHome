<template>
  <div class="reg">
    <Row type="flex" justify="center">
      <Col>
        <div class="register">
          <h1>WayHome - 注册</h1>
          <Form ref="formData" :model="formData" :rules="rule">
            <FormItem prop="username">
              <Input clearable size="large" placeholder="请输入用户名" maxlength="20" prefix="ios-person-outline"
                     v-model="formData.username"/>
            </FormItem>
            <FormItem prop="password">
              <Input type="password" password size="large" placeholder="请输入密码" maxlength="20" prefix="ios-lock-outline"
                     v-model="formData.password"/>
            </FormItem>
            <FormItem prop="repassword">
              <Input input type="password" password size="large" placeholder="请重复密码" maxlength="20"
                     prefix="ios-lock-outline" v-model="formData.repassword"/>
            </FormItem>
            <FormItem prop="email">
              <Input clearable size="large" placeholder="请输入邮箱地址" maxlength="30" prefix="ios-mail-outline"
                     v-model="formData.email"/>
            </FormItem>
            <FormItem prop="mobile">
              <Input clearable size="large" placeholder="请输入手机号码" maxlength="11" prefix="ios-phone-portrait"
                     v-model="formData.mobile"/>
            </FormItem>
            <FormItem v-if="msg">
              <label class="warning">
                <Icon class="warning" type="ios-information-circle-outline"/>
                {{msg}}</label>
            </FormItem>
            <Button type="success" size="large" @click="register('formData')">注册</Button>
            <router-link to="/login">已有账号？马上登录</router-link>
          </Form>
        </div>
      </Col>
    </Row>
  </div>
</template>

<script>
  export default {
    name: "Register",
    data() {
      const validateRePassword = (rule, value, callback) => {
        if (value !== this.formData.password) {
          callback(new Error('两次输入的密码不一致'));
        } else {
          callback();
        }
      };
      const validateMobile = (rule, value, callback) => {
        let reg = /^1[3456789]\d{9}$/;
        if (!reg.test(value)) {
          callback(new Error('请输入正确的手机号码'));
        }
        callback();
      };

      return {
        msg: '',
        formData: {
          username: '',
          password: '',
          repassword: '',
          email: '',
          mobile: ''
        },
        rule: {
          username: [
            {required: true, message: '请输入用户名', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {type: 'string', min: 6, message: '密码不能少于6位', trigger: 'blur'}
          ],
          repassword: [
            {required: true, message: '请重复密码', trigger: 'blur'},
            {validator: validateRePassword, trigger: 'blur'}
          ],
          email: [
            {required: true, message: '请输入正确的邮箱地', trigger: 'blur'},
            {type: 'email', message: '请输入正确的邮箱地', trigger: 'blur'}
          ],
          mobile: [
            {required: true, message: '请输入正确的手机号码', trigger: 'blur'},
            {validator: validateMobile, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      show() {
        this.$Message['success']({
          background: true,
          content: "注册成功，请登录",
          duration: 3,
          onClose: this.goLogin()
        });
      },
      goLogin() {
        this.$router.push({name: 'Login'})
      },
      register(form) {
        this.$refs[form].validate((valid) => {
          if (valid) {
            // this.axios.post('https://wayhome.zhangtory.com/api/register', {
            this.axios.post('http://127.0.0.1:8848/api/register', {
              username: this.formData.username,
              password: this.formData.password,
              repassword: this.formData.repassword,
              email: this.formData.email,
              mobile: this.formData.mobile
            }).then(response => {
              if (response.data['code'] === 0) {
                // 提示用户跳转到登录页面
                this.show();
              } else {
                this.msg = response.data['msg'];
              }
            }).catch(function (error) {
              this.msg = error;
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
    margin-bottom: 2vh;
  }

  .reg {
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
