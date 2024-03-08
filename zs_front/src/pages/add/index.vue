<template>
  <div class="edit-container">
    <div class="edit-items">

      <div class="edit-item-val-type" @click="callCalculator" @touchstart="touchStart" @touchend="touchEnd">
        <div class="edit-item-type">{{accountItem.type === 0? '支出':'收入'}}</div>
        <div class="edit-item-value">{{accountItem.value}}</div>
      </div>
      <div>分类【主分类和子分类】</div>
      <div>描述</div>
      <div>日期【日历表组件】</div>
      <div>支付方式</div>
    </div>

    <div class="cal-root" >
      <calculator @res="getCalRes" v-if="showCalculator"></calculator>
    </div>
    <div>
    <picker-view indicator-style="height: 50px;" :value="testVal" style="width: 100%; height: 300px;"  @change="bindChange">
      <picker-view-column>
        <view v-for="(item,index) in category" :key="index" style="line-height: 50px; text-align: center;">{{item.name}}</view>
      </picker-view-column>
      <picker-view-column>
        <view v-for="(item,index) in category" :key="index" style="line-height: 50px; text-align: center;">{{item}}</view>
      </picker-view-column>

    </picker-view>
    </div>

    <div v-show="showSelector" class="cal-root">
      <scroll-selector  @res="getSelectRes" ref="selector"></scroll-selector>
    </div>

</div>

</template>


<script>
import {Category} from "../../customConfig/catConfig";
import calculator from "../../components/add/calculator.vue";
export default {
  components:{
    calculator

  },
  data(){
    return{
      testVal:[0],
      category:[Category],
      showCalculator:false,
      accountItem:{
        type:0,
        value : '0.00'
      },

      touchStartX:null

    }
  },
  methods:{
    getCalRes(res){
      this.accountItem.value = res
   },
    callCalculator(){
      this.showCalculator = !this.showCalculator;
    },
    touchStart(e){
      this.touchStartX = e.pageX
    },
    touchEnd(e){
      if(Math.abs(e.mp.changedTouches[0].pageX-this.touchStartX) >50){
        this.accountItem.type = 1 - this.accountItem.type
      }
    },
    bindChange(e) {
      const val = e.detail.value
      console.log(val)
      /*this.setData({
        year: this.data.years[val[0]],
        month: this.data.months[val[1]],
        day: this.data.days[val[2]],
        isDaytime: !val[3]
      })*/
    },
  }
}
</script>



<style lang="scss">
.line {
  margin: 20px;
  border-bottom: 1px solid #cccccc;
  box-shadow:0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
}
.edit-item-tip {
  text-align: center;
  font-size: 15px;
  color: #cccccc;
}
.edit-item-pay {
  ul {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    .deactivate-pay {
      background-color: rgba(0, 0, 0, 0.3)!important;
    }
    .pay {
      box-shadow:0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
      width: 25%;
      margin: 10px;
      color: white;
      text-align: center;
      border-radius: 4px;
      font-size: 12px;
      padding: 2px;
      font-weight: bolder;
    }
  }
}

.edit-container{
  padding: 10px;
  display: flex;
  min-height: 100vh;
  flex-direction: column;
  .edit-items {
    flex: 1 0 auto;
    border-radius: 4px;
    padding: 5px;
    .edit-item-desc {
      width: auto!important;
      margin-left: 20px;
      margin-right: 20px;
      padding: 20px;
      height: 60px;
    }
  }
  .cal-root {
    position: fixed;
    bottom: 10px;
    width: 90%;
    margin: 0 auto;
    left: 0;
    right: 0;
  }
}
.edit-item-val-type {
  margin-top: 0px;
  border: 1px solid black;
  border-radius: 4px;
  height: 50px;
  line-height: 50px;
  font-size: 30px;
  font-weight: bolder;
  text-shadow: 0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
  display: flex;
  .edit-item-type {
    font-size: 12px;
    margin-left: 25px;
  }
  .edit-item-value {
    text-align: center;
    translate: -10%;
    flex: 1;
  }

}
.in {
  color: crimson;
}
.out {
  color: green;
}
.edit-item-cat {
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  :first-child {
    font-weight: bolder;
    color: #322f3b;
    border: 1px solid white;
    border-radius: 4px;
  }
  :last-child {
    font-weight: bolder;
    color: #322f3b;
    border: 1px solid white;
    border-radius: 4px;
  }
  div {
    font-weight: bolder;
    padding: 5px;
    border-radius: 4px;
    width: 30%;
    text-align: center;
    height: 30px;
    line-height: 30px;
  }
}
.picker-view {
  width: 100%;
  height: 300px;
}
.picker-view-col {
  line-height: 50px;
  text-align: center;
}
.log {
  text-align: center;
  border-radius: 4px;
  font-weight: bolder;
  background-color: deepskyblue;
  color: white;
  height: 30px;
  line-height: 30px;
  width: 80%;
  margin: auto;
  margin-top: 20px;
  border: 0;
  box-shadow:0 1px 1px rgba(0,0,0,0.11), 0 2px 2px rgba(0,0,0,0.11), 0 4px 4px rgba(0,0,0,0.11), 0 6px 8px rgba(0,0,0,0.11), 0 8px 16px rgba(0,0,0,0.11);
}
</style>
