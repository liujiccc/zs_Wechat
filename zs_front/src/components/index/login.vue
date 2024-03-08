<template>

    <div>

      <button class="login-btn" open-type="getUserInfo" @getuserinfo="getUserInfo">授权登录</button>

    </div>


</template>


<script>
import config from "../../utils/config";
import accounts from "./accounts.vue";
  export default {
    components: {accounts},
    methods:{
    getUserInfo(e){
      const _this = this;
      let currentUser =e.target.userInfo;
      const loginUrl = config.loginUrl


      wx.login({
        success (res) {//微信拿到登录授权信息的success

          if (res.code) {
            //发起网络请求
             wx.request({
              url: loginUrl,
              data: {
                code: res.code
              },
               success(loginRes){//用户openid的success
                currentUser['openId']=(JSON.parse(loginRes.data.msg))['openid']
                console.log(currentUser)
                 wx.setStorage({
                   key:"userinfo",
                   data:currentUser
                 })
                 _this.$emit('loginsuccess')
               }
            })
          } else {
            console.log('登录失败！' + res.errMsg)
          }
        }
      })
    }
  }

}
</script>
<style lang="scss">

.login-btn{
  margin-top: 800rpx;
  background: linear-gradient(to right,#f9957f, #f2f5d0, #aebaf8, #c973ff );
  width: 80%;
  border-radius: 50rpx;

}

</style>

