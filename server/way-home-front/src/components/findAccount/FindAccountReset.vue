<template>
  <div class="findAccountReset">
    <Row type="flex" justify="center">
      <Col>
        <Form ref="formData" :model="formData" :rules="rule" v-show="showResetForm">
          <FormItem prop="password">
            <Input type="password" password size="large" placeholder="请输入新密码" :maxlength="20" prefix="ios-lock-outline"
                   v-model="formData.password"/>
          </FormItem>
          <FormItem prop="repassword">
            <Input type="password" password size="large" placeholder="请重复新密码" :maxlength="20" prefix="ios-lock-outline"
                   v-model="formData.repassword"/>
          </FormItem>
          <FormItem v-if="msg">
            <label class="warning">
              <Icon class="warning" type="ios-information-circle-outline"/>
              {{msg}}</label>
          </FormItem>
          <Button type="success" size="large" @click="reset('formData')">重置密码</Button>
        </Form>

        <div v-show="showFail">
          <h1>重置链接已失效，请重新发起申请。</h1>
        </div>
      </Col>
    </Row>
  </div>
</template>

<script>
export default {
  // 进入页面时提取url中的secret并设置给this.secret，然后调用接口验证是否可用，可用再显示重置表单
  name: "FindAccountReset",
  mounted: function () {
    this.secret = this.$route.params.secret;
    // 验证secret
    this.axios.get('/user/find/' + this.secret).then(response => {
      if (response.data['code'] === 0) {
        this.showResetForm = true;
      } else {
        this.showFail = true;
      }
    }).catch(function (error) {
      console.log(error);
      this.showFail = true;
    })
  },
  data() {
    const validateRePassword = (rule, value, callback) => {
      if (value !== this.formData.password) {
        callback(new Error('两次输入的密码不一致'));
      } else {
        callback();
      }
    };

    return {
      showResetForm: false,
      showFail: false,
      secret: '',
      msg: '',
      formData: {
        password: '',
        repassword: ''
      },
      rule: {
        password: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
          {type: 'string', min: 6, message: '密码不能少于6位', trigger: 'blur'}
        ],
        repassword: [
          {required: true, message: '请重复新密码', trigger: 'blur'},
          {validator: validateRePassword, trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    reset(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.axios.post('/user/find/' + this.secret, {
            password: this.formData.password,
          }).then(response => {
            if (response.data['code'] === 0) {
              this.$Message.info(response.data['message']);
              this.$router.push({name: 'Login'});
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
  margin-bottom: 2vh;
}

.findAccountReset {
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
