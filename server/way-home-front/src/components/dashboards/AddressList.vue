<template>
  <div class="main">
    <div class="addBtn">
      <Button type="success" size="large" @click="applyKey()">添加地址</Button>
    </div>
    <div>
      <Table border :columns="columns1" :data="data1">
        <template slot-scope="{ row, index }" slot="action">
          <Button type="success" size="small" @click="goHome(row.keyId)">GoHome</Button>
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
            title: '地址',
            key: 'address'
          },
          {
            title: '更新时间',
            key: 'updateTime'
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
        this.axios.post('https://wayhome.zhangtory.com/api/key').then(response => {
          this.$Message.info(findResultMsg(response.data['msg']));
          let addr = response.data.data;
          let address = "未设置";
          if (addr.ip != null) {
            address = addr.protocol + addr.ip + addr.port + addr.path;
          }
          let updateTime = addr.updateTime[0] + "-"
            + addr.updateTime[1] + "-"
            + addr.updateTime[2] + "  "
            + addr.updateTime[3] + ":"
            + addr.updateTime[4] + ":"
            + addr.updateTime[5];
          this.data1.push({keyId: addr.keyId, secretKey: addr.secretKey, address: address, updateTime: updateTime});
        }).catch(function (error) {
          console.log(error);
        })
      },
      getAddressList() {
        this.axios.get('https://wayhome.zhangtory.com/api/key').then(response => {
          if (response.data['code'] === 0) {
            let arr = response.data.data;
            arr.forEach(item => {
              let keyId = item.keyId;
              let secretKey = item.secretKey;
              let address = "未设置";
              if (item.ip != null) {
                address = item.protocol + "://" + item.ip + ":" + item.port;
              }
              if (item.path != null) {
                address += item.path;
              }
              let updateTime = item.updateTime[0] + "-"
                              + item.updateTime[1] + "-"
                              + item.updateTime[2] + "  "
                              + item.updateTime[3] + ":"
                              + item.updateTime[4] + ":"
                              + item.updateTime[5];
              this.data1.push({keyId: keyId, secretKey: secretKey, address: address, updateTime: updateTime});
            });
          } else {
            this.$Message.info(findResultMsg(response.data['msg']));
          }
        }).catch(function (error) {
          console.log(error);
        })
      },
      remove(keyId, index) {
        this.axios.delete('https://wayhome.zhangtory.com/api/key/' + keyId).then(response => {
          this.data1.splice(index, 1);
        }).catch(function (error) {
          console.log(error);
        })
      },
      goHome(keyId) {
        window.open("https://wayhome.zhangtory.com/api/go/" + keyId);
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
