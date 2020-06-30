<template>
  <div class="main">
    <div class="addBtn">
      <Input size="large" style="width: 10vw;" placeholder="待创建的钥匙名" :maxlength="20" v-model="keyName"/>
      <Button type="success" size="large" @click="applyKey()">添加地址</Button>
    </div>
    <div>
      <Table border :columns="columns1" :data="data1">
        <template slot-scope="{ row, index }" slot="action">
          <Button type="success" size="small" @click="goHome(row.userName, row.keyId)">GoHome</Button>
          <Button type="error" size="small" @click="remove(row.keyId, index)">删除</Button>
        </template>
      </Table>
    </div>
  </div>
</template>

<script>
  import {findResultMsg} from '../../service/msg.js'
  export default {
    name: "AddressList",
    data() {
      return {
        userName: '',
        keyName: '',
        columns1: [
          {
            title: 'KeyId',
            key: 'keyId'
          },
          {
            title: 'SecretKey',
            key: 'secretKey'
          },
          {
            title: 'keyName',
            key: 'keyName'
          },
          {
            title: '访问地址',
            key: 'address'
          },
          {
            title: '创建时间',
            key: 'createTime'
          },
          {
            title: '操作',
            slot: 'action',
            width: 300,
            align: 'center'
          }
        ],
        data1: [
        ]
      }
    },
    mounted: function () {
      this.getAddressList();
    },
    methods: {
      applyKey() {
        // this.axios.post('https://wayhome.zhangtory.com/admin/key').then(response => {
        this.axios.post('http://127.0.0.1:8001/admin/key', {
          keyName: this.keyName
        }).then(response => {
          if (response.data['code'] === 0) {
            this.getAddressList();
          } else {
            this.$Message.info(findResultMsg(response.data['msg']));
          }
        }).catch(function (error) {
          console.log(error);
        })
      },
      getAddressList() {
        // this.axios.get('https://wayhome.zhangtory.com/admin/key').then(response => {
        this.axios.get('http://127.0.0.1:8001/admin/key').then(response => {
          if (response.data['code'] === 0) {
            let arr = response.data.data;
            arr.forEach(item => {
              let keyId = item.id;
              let secretKey = item.secretKey;
              this.userName = item.userName;
              let keyName = item.keyName;
              let address = "https://wayhome.zhangtory.com/go/" + item.userName + "/" +keyName;
              let createTime = item.createTime[0] + "-"
                              + item.createTime[1] + "-"
                              + item.createTime[2] + "  "
                              + item.createTime[3] + ":"
                              + item.createTime[4] + ":"
                              + item.createTime[5];
              this.data1.push({keyId: keyId, secretKey: secretKey, keyName: keyName, address: address,createTime: createTime});
            });
          } else {
            this.$Message.info(findResultMsg(response.data['msg']));
          }
        }).catch(function (error) {
          console.log(error);
        })
      },
      remove(keyId, index) {
        // this.axios.delete('https://wayhome.zhangtory.com/admin/key/' + keyId).then(response => {
        this.axios.delete('http://127.0.0.1:8001/admin/key/' + keyId).then(response => {
          this.data1.splice(index, 1);
        }).catch(function (error) {
          console.log(error);
        })
      },
      goHome(userName, keyName) {
        window.open("https://wayhome.zhangtory.com/api/address/" + userName + "/" + keyName);
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
