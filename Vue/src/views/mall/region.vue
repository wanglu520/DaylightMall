<template>
  <div>
    <!-- <el-table :data="tabeData" row-key="id" style="width: 100%;margin-bottom: 20px;" border="">
      <el-table-column prop="name" label="区域名称"></el-table-column>
      <el-table-column prop="type" label="区域类型"></el-table-column>
      <el-table-column prop="code" label="区域编码"></el-table-column>
    </el-table>-->
    <el-table
      v-loading="listLoading"
      :data="tabeData"
      element-loading-text="正在查询中。。。"
      row-key="id"
      style="width: 100%;margin-bottom: 20px;"
      border
    >
      <el-table-column label="区域名称" prop="name" />

      <el-table-column label="区域类型" prop="type">
        <template slot-scope="scope">{{ scope.row.type | typeFilter }}</template>
      </el-table-column>

      <el-table-column label="区域编码" prop="code" />
    </el-table>
  </div>
</template>

<script>
import { region } from "@/api/mall";
import { Notification } from "element-ui";

export default {
  data() {
    return {
      tabeData: [],
      listLoading: false
    };
  },
  created() {
    this.queryRegion();
  },
  filters: {
    typeFilter(type) {
      const typeMap = {
        "1": "省",
        "2": "市",
        "3": "区"
      };
      return typeMap[type] || "";
    }
  },
  methods: {
    queryRegion() {
      this.listLoading = true;
      region()
        .then(response => {
          this.tabeData = response.data.beans || [];
        })
        .catch(() => {
          Notification.error("查询失败");
        })
        .finally(()=>{
          this.listLoading = false;
        });
    }
  }
};
</script>