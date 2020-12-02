<template>
  <div class="navHeader">
    <i :class="[iconClass, iconStyleClasses]" @click="showOrShrink"></i>
    <el-breadcrumb separator="/" class="bread">
      <el-breadcrumb-item v-for="item in levelList" :key="item.path">
        <a @click="jumpPage(item)">{{item.meta.title}}</a>
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
export default {
  data() {
    return {
      iconClass: "el-icon-s-unfold",
      iconStyleClasses: "iconfont-def iconHead",
      levelList: null
    };
  },
  watch: {
    $route() {
      this.getLevel();
    }
  },
  created() {
    this.getLevel();
  },
  methods: {
    showOrShrink() {
      var icons = {
        "el-icon-s-unfold": "el-icon-s-fold",
        "el-icon-s-fold": "el-icon-s-unfold"
      };
      this.iconClass = icons[this.iconClass];
      //修改左侧导航菜单状态 (改为使用Vuex口罩)
      //this.$emit("changeCollapse");
      console.log(this.$store.state.app);
      this.$store.state.app.asideBar.open = !this.$store.state.app.asideBar.open
    },
    getLevel() {
      this.levelList = this.$route.matched.filter(
        item => item.meta && item.meta.title
      );
      if(this.levelList.length === 0 || !this.levelList[0].meta || this.levelList[0].meta.title !== "首页"){
        this.levelList = [{path: '/main', meta: {title: '首页'}}].concat(this.levelList);
      }
      
    },
    jumpPage(item) {
      if(item.meta.type && item.meta.type === "nogo"){
        return;
      }
      this.$router.push(item.path);
    }
  }
};
</script>

<style scoped>
.navHeader {
  float: left;
  height: 50px;
  line-height: 58px;
  border-radius: 0px !important;
}
.iconHead {
  line-height: 58px;
  height: 50px;
  float: left;
}
.bread {
  display: inline-block;
  font-size: 14px;
  line-height: 50px;
  margin-left: 10px;
  padding-top: 2px;
}
</style>