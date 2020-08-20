<template>
  <div>
    <app-Link v-if="hasChildNode(bean)" :to="bean.path">
      <el-menu-item :index="index">
        <i :class="bean.icon"></i>
        <span slot="title">{{bean.name}}</span>
      </el-menu-item>
    </app-Link>
    <el-submenu v-else :index="index">
      <template slot="title">
        <i v-if="!!bean.icon" :class="bean.icon"></i>
        <span slot="title">{{bean.name}}</span>
      </template>
      <AsideBar
        v-for="childBean in bean.child"
        :bean="childBean"
        :key="childBean.path||childBean.id"
        :index="childBean.path||childBean.id+''"
      />
    </el-submenu>
  </div>
</template>
<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  height: 100%;
}
</style>

<script>
import AppLink from "./Link"

export default {
  name: "AsideBar",
  data() {
    return {};
  },
  components:{AppLink},
  props: ["bean", "index"],
  methods: {
    hasChildNode(bean) {
      return !bean.child || bean.child.length === 0;
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
};
</script>
