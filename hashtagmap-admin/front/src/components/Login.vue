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
    <CustomSnackBar />
  </v-app>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
import { convert } from "@/utils/responseConverter";
import CustomSnackBar from "@/components/CustomSnackBar";

export default {
  data() {
    return {
      nickName: "",
      password: ""
    };
  },
  components: {
    CustomSnackBar
  },
  methods: {
    ...mapActions("member", ["loginRequest"]),
    ...mapMutations("snackbar", ["SHOW_SNACKBAR"]),
    async login() {
      const loginData = {
        nickName: this.nickName,
        password: this.password
      };
      const res = await this.loginRequest(loginData);
      this.SHOW_SNACKBAR(convert.toSnackBarContent(res));
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
</style>
