<template>
  <v-app>
    <v-container>
      <h1 class="title">admin</h1>
      <v-form>
        <v-text-field
          class="form-item"
          v-model="nickName"
          label="아이디"
          prepend-icon="mdi-account-circle"
          required
        />
        <v-text-field
          class="form-item"
          v-model="password"
          type="password"
          label="비밀번호"
          prepend-icon="mdi-lock-open"
          dense
          lang="en"
        />
        <v-btn
          class="form-item text-center margin-bottom-25px"
          color="info"
          outlined
          @click="login"
          >로그인</v-btn
        >
      </v-form>
    </v-container>
  </v-app>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data() {
    return {
      nickName: "",
      password: ""
    };
  },
  methods: {
    ...mapActions("member", ["loginRequest"]),
    login() {
      const loginData = {
        nickName: this.nickName,
        password: this.password
      };
      this.loginRequest(loginData);
    }
  }
};
</script>

<style scoped>
.title {
  padding-top: 20px;
  font-size: 40px;
  text-align: center;
}

.form-item {
  width: 300px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
}

.login-form-nav {
  border-top: #7f7f7f solid 1px;
  padding-top: 4px;
  color: #888888;
  text-align: right;
}

.background-color-white {
  background-color: white;
}
</style>
