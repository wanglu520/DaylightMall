<template>
  <div class="tags-view-content">
    <router-link
      v-for="(tag, index) in visitedViews"
      :key="tag.path"
      :to="tag.path"
      tag="el-tag"
      :class="isActive(tag) ? 'active' : ''"
      class="tags-view-item"
    >
    {{tag.meta.title}}
    <i v-show="index != 0" class="el-tag__close el-icon-close" @click="tagClosed(tag,index)"></i>
    </router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      visitedViews: [],
      isShowVisitedMap: {}
    };
  },
  watch: {
    $route() {
      this.getVisited();
    }
  },
  created() {   
    this.getVisited();
  },
  methods: {
    getVisited() {
      const myRoute = this.$route;
      if (!this.isShowVisitedMap[myRoute.path]) {
        this.isShowVisitedMap[myRoute.path] = myRoute;
        this.visitedViews.push(myRoute);
      }
      //this.visitedViews = Object.values(this.isShowVisitedMap);
      if(this.visitedViews[0] && this.visitedViews[0].path != "/main"){
        //固定第一个页签
          const defaultTag = { path: "/main", meta: { title: "首页" }};
          this.visitedViews = ([defaultTag]).concat(this.visitedViews);
          this.isShowVisitedMap[defaultTag.path] = defaultTag;
      }
    },
    isActive(tag) {
      return tag.path === this.$route.path;
    },
    tagClosed(tag={}, index){
      //关闭对应标签
      this.visitedViews.splice(index,1);
      this.isShowVisitedMap[tag.path]="";
      //如果关闭的是选中的标签，路由到前一个标签
      if(this.isActive(tag)){
        this.$router.push(this.visitedViews[index-1].path);
      }
    }
  }
};
</script>

<style scoped>
.tags-view-content {
  border: 1px solid;
  line-height: 34px;
  height: 34px;
  border-color: #d8dce5;
}
.tags-view-content span {
  padding: 0px 8px;
  margin: 0px 5px;
  line-height: 27px;
  height: 26px;
}
.tags-view-content .tags-view-item {
  border: 1px solid #d8dce5;
  display: inline-block;
  font-size: 12px;
}
.tags-view-content .active {
  background-color: #34b84c;
  border: #34b84c;
  color: #fff;
}
.tags-view-content .active>i{
    color: #fff;
}
.active:before {
  content: "";
  background: #fff;
  width: 8px;
  height: 8px;
  position: relative;
  display: inline-block;
  margin-right: 2px;
  border-radius: 50%;
}
</style>