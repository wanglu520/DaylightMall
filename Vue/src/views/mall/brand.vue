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
      <el-table-column prop="picUrl" label="品牌商图片">
        <template slot-scope="scope" v-viewer>
          <!-- 
          <div class="images" v-viewer>
            <img v-if="scope.row.picUrl" :src="scope.row.picUrl" width="80" />
          </div>
          -->
          <viewer>
            <img v-if="scope.row.picUrl" :src="scope.row.picUrl" width="80" :key="scope.row.picUrl" />
          </viewer>
        </template>
      </el-table-column>
      <el-table-column prop="desc" align="center" min-width="400px" label="介绍"></el-table-column>
      <el-table-column prop="floorPrice" label="底价"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="handleDeleted(scope.row.id)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination :page.sync="params.page" :limit.sync="params.limit" :total="total" @pagination="search" />
  </div>
</template>

<script>
import { queryBrand, deleteBrand } from "@/api/mall";
import { Notification, Message } from "element-ui";
import pagination from "@/components/Pagination";

export default {
  data() {
    return {
      params: {
        id: "",
        name: "",
        page:1,
        start: 0,
        limit: 10
      },
      tabeData: [],
      total: 0
    };
  },
  components:{pagination},
  created() {
    this.search();
  },
  methods: {
    search() {
      this.params.start = (this.params.page - 1) * this.params.limit;
      queryBrand(this.params)
        .then(response => {
          this.tabeData = response.data.beans || [];
          this.total = response.data.bean.total || 0;
        })
        .catch(() => {
          Notification.error("品牌制造商信息查询异常");
        });
    },
    handleDeleted(id){
      if(!id){
        Message.error("品牌商编码不存在，删除失败");
      }
      deleteBrand(id)
      .then(response => {
        if(response.data.returnCode === "0"){
          Message.success("删除成功");
        }else{
          Message.error("删除失败");
        }
        this.search();
      })
      .catch(
        () => {
          Message.error("异常：删除失败，请联系管理人员");
        }
      )
    }
  }
};
</script>