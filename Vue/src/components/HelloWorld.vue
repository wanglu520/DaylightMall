<template>
  <div class="mainNavigation">
    <el-container class="heightAll">
      <div class="aa">
        <el-aside :class="{'hidAsideBar':!isHid}">
          <el-menu
            :default-active="firstMenuId"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            active-text-color="#ffd04b"
            background-color="#545c64"
            text-color="#fff"
            :collapse="collapse"
            :show-timeout="200"
          >
            <AsideBar v-for="bean in beans" :bean="bean" :key="bean.path||bean.id" :index="bean.path||bean.id+''" />
          </el-menu>
        </el-aside>
      </div>

      <el-container>
        <el-header style="padding:0px; height:auto">
          <el-row>
            <el-col :span="24">
              <Header @changeCollapse="setCollapsState" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <TagViews />
            </el-col>
          </el-row>
        </el-header>
        <el-main style="padding:10px;">
          <MainBar />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import AsideBar from "./asideBar/AsideBar";
import Header from "./asideBar/Header";
import MainBar from "./asideBar/MainArea";
import TagViews from "./asideBar/tagViews";
import {queryMenu} from "@/api/user";

export default {
  data() {
    return {
      collapse: false,
      isHid: true,
      iconClass: "el-icon-s-unfold",
      iconStyleClasses: "iconfont-def iconHead",
      currRoute: [],
      beans: []
    };
  },
  components: {
    AsideBar,
    Header,
    MainBar,
    TagViews
  },
  computed: {
    menubeans() {
      queryMenu({ userId: "1" })
        .then(response => {
          let beans = response.data.beans || [];
          this.beans.length = 0;
          this.beans.push(...beans);
        })
        .catch(error => {
          this.$message({
            showClose: true,
            message: error,
            type: "error"
          });
          return [];
        });
    },
    firstMenuId() {
      return this.$route.path;
      //return (this.beans && this.beans[0] && this.beans[0].id + "") || "";
    }
  },
  mounted() {
    this.querybeans();
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    setCollapsState() {
      this.collapse = !this.collapse;
      this.isHid = !this.isHid;
    },
    querybeans() {
      this.$ajax
        .post("menu/queryMenu", { userId: "1" })
        .then(response => {
          this.beans = response.data.beans;
        })
        .catch(error => {
          this.$message({
            showClose: true,
            message: error,
            type: "error"
          });
          return [];
        });
    }
  }
};
</script>

<style acoped>
.mainNavigation {
  position: fixed;
  height: 100%;
  width: 100%;
}
.heightAll {
  height: 100%;
}
.el-aside {
  background-color: rgb(84, 92, 100);
  height: 100%;
  width: 217px !important;
  transition: width 0.28s;
  -moz-transition: width 0.28s; /* Firefox 4 */
  -webkit-transition: width 0.28s; /* Safari and Chrome */
  -o-transition: width 0.28s; /* Opera */
}
.hidAsideBar {
  width: 54px !important;
}
</style>>