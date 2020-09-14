<template>
  <div>
    <!-- 查询条件 -->
    <el-row style="margin-bottom:10px;">
      <el-input v-model="params.id" style="width: 200px;" placeholder="请输入品牌商ID"></el-input>
      <el-input v-model="params.name" style="width: 200px;" placeholder="请输入品牌商名称"></el-input>
      <el-button type="primary" @click="search" icon="el-icon-search">查询</el-button>
      <el-button type="primary" @click="search" icon="el-icon-edit">添加</el-button>
      <el-button type="primary" icon="el-icon-download">导出</el-button>
    </el-row>
    <!-- 查询结果 -->
    <el-table :data="tabeData" row-key="id" style="width: 100%;margin-bottom: 20px;" border>
      <el-table-column prop="id" label="品牌商id"></el-table-column>
      <el-table-column prop="name" label="品牌商名称"></el-table-column>
      <el-table-column prop="pic_url" label="品牌商图片"></el-table-column>
      <el-table-column prop="desc" label="介绍"></el-table-column>
      <el-table-column prop="floor_price" label="底价"></el-table-column>
      <el-table-column fixed="right" label="操作" width="100" align="center">
        <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        <el-button type="text" size="small">编辑</el-button>
      </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data(){
    return{
      params:{
        id:"",
        name:""
      },
      tabeData:[]
    }
  },
  methods:{
    search(){

    }
  }
}
</script>