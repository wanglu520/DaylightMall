<template>
  <div>
    <!-- 查询条件 -->
    <el-row style="margin-bottom:10px;">
      <el-input v-model="params.userName" style="width: 200px;" placeholder="请输入用户名"></el-input>
      <el-input v-model="params.userId" style="width: 200px;" placeholder="请输入用户Id"></el-input>
      <el-input v-model="params.userPhone" style="width: 200px;" placeholder="请输入手机号"></el-input>
      <el-button type="primary" @click="search" icon="el-icon-search">查询</el-button>
      <el-button type="primary" icon="el-icon-download">导出</el-button>
    </el-row>
    <!----列表--->
    <el-table :data="tableData" border style="width: 100%" height="350">
      <el-table-column fixed prop="userId" label="用户ID" width="150" sortable></el-table-column>
      <el-table-column prop="userName" label="用户名"></el-table-column>
      <el-table-column prop="userPhone" label="手机号"></el-table-column>
      <el-table-column align="center" prop="gender" label="性别">
        <template slot-scope="scope">
          <el-tag>{{genderDic[scope.row.gender]||"未知"}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="birthday" label="生日"></el-table-column>
      <el-table-column align="center" prop="level" label="用户等级">
        <template slot-scope="scope">
          <el-tag>{{levelDic[scope.row.level]||"普通会员"}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="state" label="状态">
        <template slot-scope="scope">
          <el-tag>{{statusDic[scope.row.state]||""}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleDetail(scope.row)">详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!----分页--->
    <div class="block">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="100"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
      <!-- 用户信息详情框 -->
      <el-dialog title="用户编辑" :visible.sync="dialogVisible" :modal-append-to-body="false">
        <el-form
          ref="form"
          :model="userDetail"
          label-width="100px"
          style="width: 400px; margin-left:50px;"
        >
          <el-form-item label="用户名">
            <el-input v-model="userDetail.userName" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="用户昵称">
            <el-input v-model="userDetail.nickname"></el-input>
          </el-form-item>
          <el-form-item label="用户密码">
            <el-input v-model="userDetail.passWord"></el-input>
          </el-form-item>
          <el-form-item label="用户手机号">
            <el-input v-model="userDetail.userPhone"></el-input>
          </el-form-item>
          <el-form-item label="用户性别">
            <el-select v-model="userDetail.gender" placeholder="请选择">
              <el-option
                v-for="(item, index) in genderDic"
                :key="index"
                :label="item"
                :value="index"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="用户等级">
            <el-select v-model="userDetail.level" placeholder="请选择">
              <el-option
                v-for="(item, index) in levelDic"
                :key="index"
                :label="item"
                :value="index"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="userDetail.state" placeholder="请选择">
              <el-option
                v-for="(item, index) in statusDic"
                :key="index"
                :label="item"
                :value="index"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="updateUserDetail">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { userDetail, updateDetail } from "@/api/user";
import { Notification } from "element-ui";
export default {
  created() {
    this.queryUserDetail();
  },
  methods: {
    handleSizeChange(val) {
      this.params.limit = val;
      this.search();
    },
    handleCurrentChange(val) {
      this.params.page = (val - 1) * this.params.limit;
      this.queryUserDetail();
    },
    handleDetail(row) {
      row.gender = row.gender - 0;
      row.level = row.level - 0;
      row.state = row.state - 0;
      this.userDetail = row;
      this.dialogVisible = true;
    },
    search() {
      this.params.page = 0;
      this.queryUserDetail();
    },
    queryUserDetail() {
      userDetail(this.params)
        .then(response => {
          this.total = (response.data.bean || {}).total || 0;
          this.tableData = response.data.beans || [];
        })
        .catch(() => {
          this.tableData = [];
        });
    },
    updateUserDetail() {
      updateDetail(this.userDetail)
        .then(response => {
          this.dialogVisible = false;
          Notification.success("更新成功");
        })
        .catch(() => {
          this.dialogVisible = false;
          Notification.error("更新失败");
        });
    }
  },
  data() {
    return {
      currentPage: 1,
      total: 0,
      tableData: [],
      userDetail: {},
      dialogVisible: false,
      statusDic: ["可用", "禁用", "注销"],
      levelDic: ["普通会员", "VIP", "高级VIP"],
      genderDic: ["男", "女", "未知"],
      params: {
        userId: "",
        userName: "",
        userPhone: "",
        sortType: "asc",
        page: 0,
        limit: 5
      }
    };
  }
};
</script>