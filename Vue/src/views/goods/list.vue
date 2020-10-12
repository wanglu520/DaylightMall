<template>
  <div>
    <!-- 查询条件 -->
    <el-row>
      <el-input v-model="params.id" style="width: 160px;" placeholder="请输入商品ID" clearable></el-input>
      <el-input v-model="params.goodsSn" style="width: 160px;" placeholder="请输入商品编号" clearable></el-input>
      <el-input v-model="params.name" style="width: 160px;" placeholder="请输入商品名称" clearable></el-input>
      <el-button type="primary" @click="search" icon="el-icon-search">查询</el-button>
      <el-button type="primary" @click="handleAddClick" icon="el-icon-edit">添加</el-button>
      <el-button type="primary" icon="el-icon-download">导出</el-button>
    </el-row>
    <!-- 数据展示 -->
    <el-table
      v-loading="listLoading"
      :data="tabeData"
      element-koading-text="正在查询中。。。"
      row-key="id"
      style="width: 100%;margin: 20px 0px;"
      border
    >
      <el-table-column type="expand"></el-table-column>
      <el-table-column prop="id" label="商品Id"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="picUrl" label="图片"></el-table-column>
      <el-table-column prop="shareUrl" label="分享图"></el-table-column>
      <el-table-column prop="detail" label="详情"></el-table-column>
      <el-table-column prop="counterPrice" label="市场售价"></el-table-column>
      <el-table-column prop="retailPrice" label="当前价格"></el-table-column>
      <el-table-column prop="isNew" label="是否新品"></el-table-column>
      <el-table-column prop="isHot" label="是否热品"></el-table-column>
      <el-table-column prop="isOnSale" label="是否在售"></el-table-column>
      <el-table-column label="操作"></el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data(){
    return {
      params:{
        id:undefined,
        goodsSn:undefined,
        name:undefined
      }
    }
  },
  methods:{
    search(){},
    handleAddClick(){}
  }
}
</script>