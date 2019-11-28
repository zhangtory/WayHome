<template>
  <div class="main">
    <Row type="flex" justify="center">
      <Col>
        <Form ref="formData" :model="formData" :rules="rule">
          <FormItem prop="oldPassword">
            <Input type="password" password size="large" placeholder="请输入旧密码" maxlength="20" prefix="ios-lock-outline" v-model="formData.oldPassword"/>
          </FormItem>
          <FormItem prop="password">
            <Input type="password" password size="large" placeholder="请输入新密码" maxlength="20" prefix="ios-lock-outline" v-model="formData.password"/>
          </FormItem>
          <FormItem prop="repassword">
            <Input type="password" password size="large" placeholder="请重复新密码" maxlength="20" prefix="ios-lock-outline" v-model="formData.repassword"/>
          </FormItem>
          <FormItem v-if="msg">
            <label class="warning"><Icon class="warning" type="ios-information-circle-outline" /> {{msg}}</label>
          </FormItem>
          <Button type="success" size="large" @click="reset('formData')">修改密码</Button>
        </Form>
      </Col>
    </Row>
  </div>
</template>

<script>
export default {
  name: "ApplyAddress",
  data () {
    const validateRePassword = (rule, value, callback) => {
      if (value !== this.formData.password) {
        callback(new Error('两次输入的密码不一致'));
      } else {
        callback();
      }
    };

    return {
      msg: '',
      formData: {
        oldPassword: '',
        password: '',
        repassword: ''
      },
      rule: {
        oldPassword: [
          {required: true, message: '请输入旧密码', trigger: 'blur'},
          {type: 'string', min: 6, message: '密码不能少于6位', trigger: 'blur'}
        ],
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
          // 请求后端
          let fd = new FormData();
          fd.append('oldPassword', this.formData.oldPassword);
          fd.append('password', this.formData.password);
          fd.append('repassword', this.formData.repassword);
          this.axios.post('https://wayhome.zhangtory.com/api/password', fd,{
            headers:{
              'Content-Type':'multipart/form-data'
            }
          }).then(response => {
            if (response.data['code'] === 0) {
              this.$Message.info(response.data['msg']);
              this.$router.push({name:'Dashboard'});
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
  .main {
    height: 50vh;
    text-align: center;

    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>
