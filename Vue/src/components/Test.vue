<template>
  <div class="login-container">
    <el-form ref="form" :rules="rules" :model="form" label-width="80px" class="login-form">
      <h2 class="login-title">系统管理</h2>
      <p v-if="err" class="login-errInfo">用户名或密码不正确</p>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" show-password></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
    <script>
export default {
  data() {
    return {
      err:false,
      form: {
        username: "admin",
        password: "123456"
      },
      rules: {
        username: [
          { required: true, message: "用户名不能为空", trigger: "blur" },
          { min: 3, max: 10, message: "用户名3-5位", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" },
          { min: 6,  max:8, message: "密码6-8位", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      console.log(formName);
      console.log(this.$ajax);
      this.$refs[formName].validate(valid => {
        console.log(this.form); //验证通过为true，有一个不通过就是false
        if (valid) {
          // 通过的逻辑
          this.$ajax
            .post("user/login", this.form)
            .then(response => {
              console.log("1231:" + response);
              // 成功
              if (response.data.returnCode === "0") {
                localStorage.setItem("token", response.data.userKey);
                this.$message({
                  showClose: true,
                  message: "恭喜你，这是一条成功消息",
                  type: ""
                });
                this.$router.push("/main");
              }else{//账号或密码不正确
                this.err = true;
              }
            })
            .catch(error => {
              // 请求失败处理
              console.log(`error:${{error}}`);
              this.$message({
                showClose:true,
                message:error,
                type:"error"
              });
            });
        } else {
          console.log("验证失败");
          return false;
        }
      });
    }
  }
};
</script>

  <style acoped>
.login-form {
  width: 350px;
  margin: 160px auto; /* 上下间距160px，左右自动居中*/
  background-color: rgb(255, 255, 255, 0.8); /* 透明背景色 */
  padding: 30px;
  border-radius: 20px; /* 圆角 */
}

/* 背景 */
.login-container {
  position: fixed;
  width: 100%;
  height: 100%;
  background: url("../assets/login.jpg");
  background-size: 99% 99%;
}

/* 标题 */
.login-title {
  color: #303133;
  text-align: center;
}

/* 登录失败提示 */
.login-errInfo{
  color: #F56C6C;
  font-size: 12px;
}
</style>