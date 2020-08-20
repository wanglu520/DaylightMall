<template>
  <div>
    <h2>{{fullName}}</h2>
    <h2>{{count}}</h2>
    <button @click="add">+</button>
    <button @click="sub">-</button>
    <aa><span>llllllll</span></aa>
    <div>
      <table v-if="items != null && items.length > 0">
        <thead>
          <tr>
            <th></th>
            <th>书籍名称</th>
            <th>出版日期</th>
            <th>价格</th>
            <th>购买数量</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(bean, index) in items" :key="index">
            <td>{{index+1}}</td>
            <td>{{bean.name}}</td>
            <td>{{bean.date}}</td>
            <td>{{bean.price}}</td>
            <th>
              <button @click="sub(bean)">-</button>
              {{bean.num}}
              <button @click="add(bean)">+</button>
            </th>
            <th>
              <button @click="deleteOffer(index)">移除</button>
            </th>
          </tr>
        </tbody>
        总价：￥{{total}}
      </table>
      <span v-else>购物车无任何物品</span>
    </div>

    <ul>
      <li v-for="(item,index) in movies" :key="index" @click="liClick(item)">{{item}}</li>
    </ul>
    <!-- <img v-bind:src="imgUrl" style="height:50px;"/> -->
  </div>
</template>
<script>
import aa from './mytest'

export default {
  data() {
    return {
      firstName: "wang",
      lastName: "Lu",
      count: 0,
      movies: ["1", "2", "3"],
      total: 0,
      items: [
        { name: "算法导论", date: "2006-9", price: 85.0, num: 1 },
        { name: "UNIX编程艺术", date: "2006-2", price: 59.0, num: 1 },
        { name: "编程珠玑", date: "2008-10", price: 39.0, num: 1 },
        { name: "代码大全", date: "2006-3", price: 128.0, num: 1 }
      ],
      imgUrl:
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596343479295&di=2aceb780c58f05a52fe9400a6d6e7884&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F22%2F59%2F19300001325156131228593878903.jpg"
    };
  },
  components:{aa},
  computed: {
    fullName: {
      set: function(val) {
        const newVal = val.split(" ");
        this.firstName = newVal[0];
        this.lastName = newVal[1];
      },
      get: function() {
        return `${this.firstName} ${this.lastName}`;
      }
    }
  },
  mounted() {
    this.getTotal();
  },
  methods: {
    deleteOffer(index) {
      this.items[index].num = 0;
      this.getTotal();
      this.items.splice(index, 1);
    },
    liClick(item) {
      this.fullName = "ww ee";
    },
    getTotal() {
      this.total = this.items.reduce((val1, val2) => {
        return val1 + val2.price * val2.num;
      }, 0);
      console.log(this.total);
    },
    add(bean) {
      bean.num++;
      this.count++;
      this.total += bean.price;
      console.log(bean.num);
    },
    sub(bean) {
      if (bean.num > 0) {
        bean.num--;
        this.total -= bean.price;
      }
      this.count--;
    }
  }
};
</script>