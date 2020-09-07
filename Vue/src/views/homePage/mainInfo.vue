<template>
  <el-row :gutter="40">
    <el-col :span="6" class="infoCol">
      <div class="card-panel">
        <div class="class-icon-wrapper icon-user">
          <i class="el-icon-user-solid class-icon" />
        </div>
        <div class="card-panel-content">
          <div class="card-panel-text">用户数量</div>
          <countTo :start-val="0" :end-val="userTotal" :duration="2000" />
        </div>
      </div>
    </el-col>
    <el-col :span="6" class="infoCol">
      <div class="card-panel">
        <div class="class-icon-wrapper icon-shop">
          <i class="el-icon-s-shop class-icon" />
        </div>
        <div class="card-panel-content">
          <div class="card-panel-text">商品数量</div>
          <countTo :start-val="0" :end-val="goodsTotal" :duration="2500" />
        </div>
      </div>
    </el-col>
    <el-col :span="6" class="infoCol">
      <div class="card-panel">
        <div class="class-icon-wrapper icon-box">
          <i class="el-icon-box class-icon" />
        </div>
        <div class="card-panel-content">
          <div class="card-panel-text">货品数量</div>
          <countTo :start-val="0" :end-val="productTotal" :duration="3000" />
        </div>
      </div>
    </el-col>
    <el-col :span="6" class="infoCol">
      <div class="card-panel">
        <div class="class-icon-wrapper icon-shopping">
          <i class="el-icon-shopping-cart-full class-icon" />
        </div>
        <div class="card-panel-content">
          <div class="card-panel-text">订单数量</div>
          <countTo :start-val="0" :end-val="orderTotal" :duration="3500" class="card-panel-num"/>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import countTo from "vue-count-to";
export default {
  data() {
    return {
      userTotal: 10,
      goodsTotal: 20,
      productTotal: 30,
      orderTotal: 40
    };
  },

  components: { countTo }
};
</script>

<style scoped>
.class-icon-wrapper {
  float: left;
}
.class-icon {
  font-size: 50px;
  padding: 25px;
}
.el-icon-user-solid {
  color: #40c9c6;
}
.el-icon-s-shop {
  color: rgb(29, 117, 128);
}
.card-panel-content {
  float: right;
  padding: 25px 15px 0px 0px;
}
.infoCol {
  background-color: #f0f2f5;
  height: 200px;
  padding: 32px;
}
.card-panel {
  background-color: white;
  height: 100px;
  margin-top: 10px;
  transition: all 0.5s ease-out;
  border-radius: 6px;
}
.card-panel:hover .icon-user {
  background-color: #40c9c6;
}
.card-panel:hover .el-icon-user-solid {
  color: white;
}
.card-panel:hover .icon-shop {
  background-color: rgb(29, 117, 128);
}
.card-panel:hover .el-icon-s-shop {
  color: white;
}
.el-icon-shopping-cart-full {
  color: rgb(54, 204, 109);
}
.card-panel:hover .icon-shopping {
  background-color: rgb(54, 204, 109);
}
.card-panel:hover .el-icon-shopping-cart-full {
  color: white;
}
.el-icon-box {
  color: rgb(188, 95, 107);
}
.card-panel:hover .icon-box {
  background-color: rgb(188, 95, 107);
}
.card-panel:hover .el-icon-box {
  color: white;
}
.card-panel-num{
    font-size: 20px
}
</style>