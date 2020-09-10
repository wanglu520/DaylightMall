<template>
  <div>
    <!-- 查询条件 -->
    <el-row style="margin-bottom:10px;">
      <el-input v-model="params.userId" style="width: 200px;" placeholder="请输入用户Id"></el-input>
      <el-input v-model="params.name" style="width: 200px;" placeholder="请输入收货人姓名"></el-input>
      <el-button type="primary" @click="search" icon="el-icon-search">查询</el-button>
      <el-button type="primary" icon="el-icon-download">导出</el-button>
    </el-row>
    <!----列表--->
    <el-table :data="addressInfo" border style="width: 100%" height="350">
      <el-table-column fixed prop="id" label="地址ID" width="80" sortable></el-table-column>
      <el-table-column prop="userId" label="用户ID"></el-table-column>
      <el-table-column prop="name" label="收货人名称"></el-table-column>
      <el-table-column prop="tel" label="手机号"></el-table-column>
      <el-table-column prop="" label="区域地址"></el-table-column>
      <el-table-column prop="addressDetail" label="详细地址"></el-table-column>
    </el-table>
    <!----分页--->
    <div class="block">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-sizes="[5, 10, 15, 20]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import { userAddress } from "@/api/user";
export default {
  data() {
    return {
      params: {
        userId: "",
        name: "",
        page: 0,
        limit: 5
      },
      total: 0,
      currentPage:1,
      addressInfo: []
    };
  },
  created() {
    this.search();
  },
  methods: {
    search() {
      userAddress(this.params)
        .then(response => {
          this.addressInfo = response.data.beans || [];
          this.total = (response.data.bean||{}).total;
        })
        .catch();
    },
    handleSizeChange(val) {
      this.params.limit = val;
      this.params.page = 0;
      this.currentPage = 1;
      this.search();
    },
    handleCurrentChange(val) {
      this.params.page = (val - 1) * this.params.limit;
      this.search();
    }
  }
};
</script>