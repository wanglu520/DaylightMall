<template>
  <div class="goods_content">
    <h3>商品介绍</h3>
    <el-form :ref="form" :model="form" :rules="rules" label-width="150px">
      <el-form-item label="商品编号" prop="goodsSn">
        <el-input v-model="form.goodsSn"></el-input>
      </el-form-item>
      <el-form-item label="商品名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="市场售价">
        <el-input v-model="form.counterPrice" placeholder="0.00"></el-input>
      </el-form-item>
      <el-form-item label="是否新品">
        <el-radio-group v-model="form.isNew">
          <el-radio :label="true">新品</el-radio>
          <el-radio :label="false">非新品</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="是否热卖">
        <el-radio-group v-model="form.isHot">
          <el-radio :label="false">普通</el-radio>
          <el-radio :label="true">热卖</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="是否在售">
        <el-radio-group v-model="form.isOnSale">
          <el-radio :label="true">在售</el-radio>
          <el-radio :label="false">未售</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="商品图片">
        <el-upload
          class="avatar-uploader"
          :limit="1"
          :headers="headers"
          :action="uploadPath"
          :on-success="uploadPicUrl"
          :on-exceed="handleExceed"
          :on-remove="handleRemove"
          accept=".jpg, .jpeg, .png, .gif"
          list-type="picture-card"
        >
          <i class="el-icon-plus"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="宣传画廊">
        <el-upload
          class="avatar-uploader"
          :limit="5"
          :headers="headers"
          :action="uploadPath"
          :on-success="handleSuccess"
          :on-exceed="handleExceed"
          :on-remove="handleRemove"
          accept=".jpg, .jpeg, .png, .gif"
          list-type="picture-card"
        >
          <i class="el-icon-plus"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="商品单位">
        <el-input v-model="form.unit" placeholder="件/个/盒"></el-input>
      </el-form-item>
      <el-form-item label="关键字">
        <el-tag
          v-for="tag in keywords"
          :key="tag"
          closable
          :disable-transitions="false"
          @close="handleClose(tag)"
        >{{tag}}</el-tag>
        <el-input
          v-model="inputValue"
          v-if="inputVisible"
          size="small"
          class="input-new-tag"
          @blur="handleInputConfirm"
          @keyup.enter.native="handleInputConfirm"
        ></el-input>
        <el-button v-else type="primary" class="button-new-tag" @click="showInput">+增加</el-button>
      </el-form-item>
      <el-form-item label="所属分类">
        <el-cascader
          :options="classificationList"
          :props="{expandTrigger:'hover'}"
          @change="handleChange"
        ></el-cascader>
      </el-form-item>
      <el-form-item label="所属品牌商">
        <el-select v-model="form.brandId">
          <el-option v-for="item in brandList" :key="item.id" :label="item.name" :value="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="商品介绍">
        <el-input v-model="form.brief"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { uploadPath } from "@/api/mall";
import { queryCategory } from "@/api/goods";
import { Notification } from "element-ui";
export default {
  data() {
    return {
      uploadPath,
      keywords: [],
      classificationList: [],
      brandList: [],
      inputValue: undefined,
      inputVisible: false,
      form: {
        goodsSn: undefined,
        name: undefined,
        counterPrice: undefined,
        isNew: true,
        isHot: false,
        isOnSale: true,
        picUrl: undefined,
        gallery: [],
        unit: undefined,
        keywords: "",
        brandId: "",
        categoryId:""
      },
      rules: {
        goodsSn: { required: true, message: "请输入商品编码", trigger: "blur" }
      }
    };
  },
  computed: {
    headers() {
      return {
        Authorization: localStorage.getItem("token")
      };
    }
  },
  created() {
    this.queryCategory();
  },
  methods: {
    queryCategory() {
      var data = { level: "L1" };
      queryCategory(data)
        .then(response => {
          this.classificationList = response.data.beans || [];
          this.brandList = response.data.bean.brandList || [];
        })
        .catch(() => {
          Notification.error("所属分类查询失败");
        });
    },
    uploadPicUrl(response) {
      this.form.picUrl = response.bean.url;
    },
    handleRemove() {
      this.form.picUrl = "";
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前不能超过${fileList.length}个`);
    },
    handleSuccess(response) {
      this.form.gallery.push(response.bean.url);
    },
    handleGalleryRemove() {},
    handleClose(tag) {
      this.keywords.splice(this.keywords.indexOf(tag), 1);
    },
    handleInputConfirm() {
      this.inputVisible = false;
      if (this.inputValue) {
        this.keywords.push(this.inputValue);
      }
      this.inputValue = "";
    },
    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    handleChange(val) {
      this.goods.categoryId = value[value.length - 1]
    }
  }
};
</script>

<style>
.goods_content {
  margin: 10px;
  padding: 20px;
  border: 1px solid #e6ebf5;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.goods_content label {
  font-weight: bold;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>