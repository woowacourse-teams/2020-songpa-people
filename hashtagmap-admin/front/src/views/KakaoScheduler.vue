<template>
  <v-container>
    <v-btn @click="kakaoScheduling" class="ma-2" color="indigo" outlined>
      카카오 스케줄러 실행
    </v-btn>
    <div class="period-input">
      <v-text-field
        v-model="period"
        label="정규 표현식"
        hint="(예) 0 0/5 * * * ?"
      />
    </div>
    <v-btn @click="changePeriod" class="ma-2" color="indigo" outlined>
      스케줄 시간 변경
    </v-btn>
    <CustomSnackBar />
  </v-container>
</template>

<script>
import axios from "axios";
import CustomSnackBar from "../components/CustomSnackBar";

export default {
  name: "KakaoScheduler",
  components: {
    CustomSnackBar
  },
  data: () => ({
    period: ""
  }),
  methods: {
    kakaoScheduling() {
      // do something
    },
    changePeriod() {
      if (this.period == "") {
        this.$store.commit("SHOW_SNACKBAR", {
          type: "info",
          message: "정규식을 입력하세요."
        });
      }
      axios
        .post("/kakao-scheduler/change-period", this.period)
        .then(res => {
          console.log(res);
          if (res.data.data) {
            this.$store.commit("SHOW_SNACKBAR", {
              type: "success",
              message: "주기가 변경되었습니다."
            });
          } else {
            this.$store.commit("SHOW_SNACKBAR", {
              type: "error",
              message: res.data.message,
              code: res.data.code
            });
          }
        })
        .catch(err => {
          console.dir(err);
        });
    }
  }
};
</script>

<style>
.period-input {
  width: 200px;
}
</style>
