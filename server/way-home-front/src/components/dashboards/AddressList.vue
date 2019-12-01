<template>
  <div class="main">
    <div class="addBtn">
      <Button type="success" size="large" @click="applyKey()">添加地址</Button>
    </div>
    <div>
      <Table border :columns="columns1" :data="data1">
        <template slot-scope="{ row }" slot="action">
          <Button type="success" size="small" @click="goHome(row.appId)">GoHome</Button>
          <Button type="error" size="small" @click="remove(row.appId)">删除</Button>
        </template>
      </Table>
    </div>
  </div>
</template>

<script>
  export default {
    name: "AddressList",
    data() {
      return {
        columns1: [
          {
            title: 'AppId',
            key: 'appId'
          },
          {
            title: 'SecretKey',
            key: 'secretKey'
          },
          {
            title: 'Action',
            slot: 'action',
            width: 300,
            align: 'center'
          }
        ],
        data1: [
          {
            appId: '123',
            secretKey: 'asddsa',
          }
        ]
      }
    },
    mounted: function () {
      this.getAddressList();
    },
    methods: {
      applyKey() {
        this.axios.post('https://wayhome.zhangtory.com/api/key').then(response => {
          this.$Message.info(response.data['msg']);
        }).catch(function (error) {
          this.msg = error;
        })
        this.getAddressList();
      },
      getAddressList() {
        // this.data1.push({appId: 'test', secretKey: 'testKey'});
        this.axios.post('https://wayhome.zhangtory.com/api/dashboard').then(response => {
          if (response.data['code'] === 0) {
            let appId = response.data['userKeyList']['appId'];
            let secretKey = response.data['userKeyList'][0]['secretKey'];
            this.data1.push({appId: appId, secretKey: secretKey});
          } else {
            this.$Message.info(response.data['msg']);
          }
        }).catch(function (error) {
          this.msg = error;
        })
      },
      remove(appId) {
        this.axios.delete('https://wayhome.zhangtory.com/api/key/' + appId).then(response => {
        }).catch(function (error) {
          this.msg = error;
        })
        this.getAddressList();
      },
      goHome(appId) {
        window.open("https://wayhome.zhangtory.com/api/go/" + appId);
      }
    }
  }
</script>

<style scoped>
  .addBtn {
    margin-left: 1vw;
    margin-top: 1vw;
    margin-bottom: 1vw;
  }
</style>
