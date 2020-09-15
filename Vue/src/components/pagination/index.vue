<template>
  <div class="block">
    <el-pagination
      :background="background"
      :current-page.sync="currentPage"
      :page-sizes="pageSizes"
      :page-size.sync="pageSize"
      :layout="layout"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    ></el-pagination>
  </div>
</template>

<script>

export default {
  name:'Pagination',
  props:{
    background:{
      type:Boolean,
      default:false
    },
    pageSizes:{
      type:Array,
      default(){
        return [5, 10, 15, 20]
      }
    },
    layout:{
      type:String,
      default:'total, sizes, prev, pager, next, jumper'
    },
    total:{
      required: true,
      type:Number
    },
    page:{
      type:Number,
      default:1
    },
    limit:{
      type:Number,
      default:10
    }
  },
  computed:{
    currentPage:{
      get(){
        return this.page
      },
      set(val){
        this.$emit('update:page', val)
      }
    },
    pageSize:{
      get(){
        return this.limit
      },
      set(val){
        this.$emit('update:limit',val)
      }
    }
  },
  methods:{
    handleSizeChange(val){
      this.currentPage = 1;
      this.$emit('pagination', {page: this.currentPage, limit:val});
    },
    handleCurrentChange(val){
      this.$emit('pagination', {page: val, limit:this.pageSize})
    }
  }
}
</script>