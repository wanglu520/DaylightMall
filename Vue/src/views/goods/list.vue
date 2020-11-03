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
      header-align="center"
    >
      <!-- 商品扩展信息 -->
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-form label-width="100px" label-position="left" class="goods-table-expand">
            <el-form-item label="商品编号">
              <span>{{scope.row.goodsSn}}</span>
            </el-form-item>
            <el-form-item label="宣传画廊">
              <img v-for="item in JSON.parse(scope.row.gallery)" :src="item" :key="item" class="form-gallery">
            </el-form-item>
            <el-form-item label="商品介绍">
              <span>{{scope.row.brief}}</span>
            </el-form-item>
            <el-form-item label="商品单位">
              <span>{{scope.row.unit}}</span>
            </el-form-item>
            <el-form-item label="商品关键字">
              <span>{{scope.row.keywords}}</span>
            </el-form-item>
            <el-form-item label="类目ID">
              <span>{{scope.row.categoryId}}</span>
            </el-form-item>
            <el-form-item label="品牌商ID">
              <span>{{scope.row.brandId}}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="id" label="商品Id"></el-table-column>
      <el-table-column align="center" prop="name" label="名称"></el-table-column>
      <el-table-column align="center" prop="iconUrl" label="图片">
        <template slot-scope="scope" >
          <img :src="scope.row.picUrl" width="40" :key="scope.row.picUrl">
        </template>
      </el-table-column>
      <el-table-column align="center" prop="iconUrl" label="分享图">
        <template slot-scope="scope">
          <img :src="scope.row.shareUrl" width="40">
        </template>
      </el-table-column>
      <el-table-column align="center" prop="detail" label="详情">
        <template slot-scope="scope">
          <el-button type="primary" @click="showDetail(scope.row.detail)">查看</el-button>
          <el-dialog title="商品详情"  :visible.sync="detailDialog" :modal-append-to-body="false">
            <div class="goods-detail-box" v-html="goodsDetail" />
          </el-dialog>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="counterPrice" label="市场售价"></el-table-column>
      <el-table-column align="center" prop="retailPrice" label="当前价格"></el-table-column>
      <el-table-column align="center" prop="isNew" label="是否新品">
        <template slot-scope="scope">
          <el-tag type="scope.row.isNew ? 'success' : 'error'">{{scope.row.isNew ? '新品' :'非新品'}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="isHot" label="是否热品">
        <template slot-scope="scope">
          <el-tag type="scope.row.isHot ? 'success' : 'error'">{{scope.row.isHot ? '热品' :'非热品'}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="isOnSale" label="是否在售">
        <template slot-scope="scope">
          <el-tag type="scope.row.isOnSale ? 'success' : 'error'">{{scope.row.isOnsale ? '在售' : '非在售'}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column width="200" align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini">编辑</el-button>
          <el-button type="danger" size="mini">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {searchList} from '@/api/goods'
import { Notification, Message } from "element-ui";
export default {
  data(){
    return {
      listLoading:true,
      params:{
        id:undefined,
        goodsSn:undefined,
        name:undefined,
        start:0,
        limit:10
      },
      tabeData:[],
      total:0,
      detailDialog:false,
      goodsDetail:''
    }
  },
  created(){
    this.search();
  },
  methods:{
    search(){
      searchList(this.params)
      .then(response => {
        this.tabeData = response.data.beans || [];
        this.total = response.data.bean.total || 0;
      })
      .catch(() => {
        Notification.error("查询失败");
      })
      .finally(()=>{
        this.listLoading = false;
      });
    },
    handleAddClick(){},
    showDetail(val){
      this.detailDialog = true;
      this.goodsDetail = val;
    }
  }
}
</script>
<style>
  .goods-table-expand label{
    color: #99a9bf;
  }
  .form-gallery{
    height: 100px;
    margin-right: 15px;
  }
</style>