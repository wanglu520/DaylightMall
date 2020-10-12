<template>
  <div>
    <!-- 查询条件 -->
    <el-row style="margin-bottom:10px;">
      <el-input v-model="params.id" style="width: 200px;" placeholder="请输入品牌商ID"></el-input>
      <el-input v-model="params.name" style="width: 200px;" placeholder="请输入品牌商名称"></el-input>
      <el-button type="primary" @click="search" icon="el-icon-search">查询</el-button>
      <el-button type="primary" @click="handleAddClick" icon="el-icon-edit">添加</el-button>
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
            <img v-if="scope.row.picUrl" :src="scope.row.picUrl" width="80" height="40" :key="scope.row.picUrl" />
          </viewer>
        </template>
      </el-table-column>
      <el-table-column prop="desc" align="center" min-width="400px" label="介绍"></el-table-column>
      <el-table-column prop="floorPrice" label="底价"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button @click="handleUpdateClick(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="handleDeleted(scope.row.id)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :page.sync="params.page"
      :limit.sync="params.limit"
      :total="total"
      @pagination="search"
    />
    <!-- 操作弹框 -->
    <el-dialog
      :visible="dialogVisible"
      :title="title"
      :modal-append-to-body="false"
      :before-close="handleClose"
    >
      <el-form
        ref="brandDetail"
        :model="brandDetail"
        :rules="rules"
        label-width="100px"
        style="width: 450px; margin-left:50px;"
      >
        <el-form-item label="品牌商名称" prop="name">
          <el-input v-model="brandDetail.name"></el-input>
        </el-form-item>
        <el-form-item label="介绍">
          <el-input v-model="brandDetail.desc"></el-input>
        </el-form-item>
        <el-form-item label="品牌商图片">
          <el-upload
            class="avatar-uploader"
            :headers="headers"
            :action="uploadPath"
            :on-success="uploadPicUrl"
            :on-error="uploadErr"
            :show-file-list="false"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <viewer>
              <img
                v-if="brandDetail.picUrl"
                :src="brandDetail.picUrl"
                class="avatar"
              />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </viewer>
          </el-upload>
        </el-form-item>
        <el-form-item label="底价">
          <el-input v-model="brandDetail.floorPrice"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button v-if="title === '创建'" type="primary" @click="addDate">确 定</el-button>
        <el-button v-else type="primary" @click="updateDate">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { queryBrand, deleteBrand, updateBrand, addBrand, uploadPath } from "@/api/mall";
import { Notification, Message } from "element-ui";
import pagination from "@/components/Pagination";

export default {
  data() {
    console.log(process)
    
    return {
      uploadPath,
      params: {
        id: "",
        name: "",
        page: 1,
        start: 0,
        limit: 10
      },
      tabeData: [],
      total: 0,
      dialogVisible: false,
      title: "",
      brandDetail: {
        name: "",
        picUrl:""
      },
      oldBranDetail: {},
      rules: {
        name: [{ required: true, message: "请输入品牌商名称", trigger: "blur" }]
      }
    };
  },
  components: { pagination },
  computed:{
    headers(){
      return {
        Authorization : localStorage.getItem('token')
      }
    }
  },
  created() {
    this.search();
  },
  methods: {
    search() {
      console.log(process)
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
    handleDeleted(id) {
      if (!id) {
        Message.error("品牌商编码不存在，删除失败");
      }
      deleteBrand(id)
        .then(response => {
          if (response.data.returnCode === "0") {
            Message.success("删除成功");
          } else {
            Message.error("删除失败");
          }
          this.search();
        })
        .catch(() => {
          Message.error("异常：删除失败，请联系管理人员");
        });
    },
    handleAddClick() {
      this.brandDetail = {
        picUrl:undefined
      };
      this.dialogVisible = true;
      this.title = "创建";
    },
    addDate() {
      this.$refs["brandDetail"].validate(valid => {
        if (valid) {
          addBrand(this.brandDetail)
            .then(response => {
              //this.tabeData.unshift(response.data.bean);
              this.$notify.success({
                title: "成功",
                message: "新增成功"
              });
              this.dialogVisible = false;
              this.search();
            })
            .catch(response => {
              this.$notify.error({
                title: "失败",
                message: response.data.errmsg
              });
            });
        }
      });
    },
    updateDate() {
      this.$refs["brandDetail"].validate(valid => {
        if (valid) {
          updateBrand(this.brandDetail)
            .then(() => {
              Object.assign(this.oldBranDetail, this.brandDetail);
              this.$notify.success({
                title: "成功",
                message: "更新成功"
              });
              this.dialogVisible = false;
            })
            .catch(response => {
              this.$notify.error({
                title: "失败",
                message: response.data.errmsg
              });
            });
        }
      });
    },
    uploadPicUrl(response){
      this.brandDetail.picUrl = response.bean.url;
    },
    uploadErr(response){
      this.$message.error(`上传图片失败:${response.data}`);
    },
    handleUpdateClick(bean) {
      this.title = "编辑";
      this.brandDetail = Object.assign({}, bean);
      this.oldBranDetail = bean;
      this.dialogVisible = true;
    },
    handleClose() {
      this.dialogVisible = false;
    }
  }
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>