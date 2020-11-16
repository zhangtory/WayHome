<template>
  <div class="findAccountSendMail">
    <Row type="flex" justify="center">
      <Col>
        <div class="register" v-show="waitSend">
          <h1>WayHome - 邮箱找回密码</h1>
          <Form ref="formData" :model="formData">
            <FormItem prop="username">
              <Input clearable size="large" placeholder="请输入用户名" :maxlength="30" prefix="ios-mail-outline"
                     v-model="formData.username"/>
            </FormItem>
            <Button type="success" size="large" @click="sendMail('formData')">发送邮件</Button>
            <FormItem v-if="msg">
              <label class="warning">
                <Icon class="warning" type="ios-information-circle-outline"/>
                {{msg}}</label>
            </FormItem>
          </Form>
        </div>

        <div v-show="!waitSend">
          <h1>请点击邮箱中的链接重置密码。</h1>
        </div>
      </Col>
    </Row>
  </div>
</template>

<script>
export default {
  name: "FindAccountSendMail",
  data() {
    return {
      msg: '',
      waitSend: true,
      formData: {
        username: ''
      }
    }
  },
  methods: {
    sendMail(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.axios.post('/admin/user/find/mail', {
            username: this.formData.username
          }).then(response => {
            if (response.data['code'] === 0) {
              this.waitSend = false;
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

.findAccountSendMail {
  width: 100vw;
  height: 100vh;
  background-image: url("../../../static/stars_bg.jpg");
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
