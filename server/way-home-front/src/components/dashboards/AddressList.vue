<template>
  <div class="main">
    <div class="addBtn">
      <Input size="large" style="width: 10vw;" placeholder="待创建的钥匙名" :maxlength="20" v-model="keyName"/>
      <Button type="success" size="large" @click="applyKey()">创建钥匙</Button>
    </div>
    <div>
      <Table border :columns="columns1" :data="data1">
        <template slot-scope="{ row, index }" slot="action">
          <Button type="success" size="small" @click="goHome(row.goUrl)">GoHome</Button>
          <Button type="error" size="small" @click="remove(row.keyId, index)">删除</Button>
        </template>
      </Table>
    </div>
  </div>
</template>

<script>
  import {formatDate} from '../../service/date';
  export default {
    name: "AddressList",
    data() {
      return {
        currentPage: 1,
        userName: localStorage.getItem("username"),
        keyName: '',
        columns1: [

          {
            title: '钥匙名(keyName)',
            key: 'keyName'
          },
          {
            title: '书签地址',
            key: 'address'
          },
          {
            title: '短链接',
            key: 'shortUrl'
          },
          {
            title: '目标地址',
            key: 'url'
          },
          {
            title: '钥匙秘钥(secretKey)',
            key: 'secretKey'
          },
          {
            width: 200,
            title: '创建时间',
            key: 'createTime',
            sortable: true
          },
          {
            title: '操作',
            slot: 'action',
            width: 200,
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
        if (this.keyName === '') {
          this.$Message.info('请输入钥匙名');
          return;
        }
        this.axios.post('/admin/key/add', {
          keyName: this.keyName
        }).then(response => {
          if (response.data['code'] === 0) {
            this.getAddressList();
          } else {
            this.$Message.info(response.data['message']);
          }
          this.keyName = '';
        }).catch(function (error) {
          console.log(error);
        })
      },
      getAddressList() {
        this.axios.get('/admin/key/query/' + this.currentPage).then(response => {
          if (response.data['code'] === 0) {
            let keyPage = response.data.data;
            keyPage['records'].forEach(item => {
              let keyId = item.id;
              let keyName = item.keyName;
              let goUrl = "/go/" + this.userName + "/" +keyName;
              let shortUrl = item.shortUrl;
              let address = "https://" + document.location.hostname + goUrl;
              let url = item.url;
              let secretKey = item.secretKey;
              let createTime = formatDate(new Date(item.createTime), 'yyyy-MM-dd hh:mm');
              this.data1.push({keyId: keyId, secretKey: secretKey, keyName: keyName, url: url, address: address,
                goUrl: goUrl, createTime: createTime, shortUrl: shortUrl});
            });
          } else {
            this.$Message.info(response.data['message']);
          }
        }).catch(function (error) {
          console.log(error);
        })
      },
      remove(keyId, index) {
        this.axios.delete('/admin/key/' + keyId).then(response => {
          this.data1.splice(index, 1);
        }).catch(function (error) {
          console.log(error);
        })
      },
      goHome(address) {
        window.open(address);
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
