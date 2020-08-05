<template>
  <v-container>
    <v-btn @click="kakaoScheduling" class="ma-2" color="indigo" outlined>
      카카오 스케줄러 실행
    </v-btn>
    <div class="period-input">
      <v-text-field
        v-model="expression"
        label="정규 표현식"
        hint="(예) 0 0/5 * * * ?"
      />
    </div>
    <v-btn @click="changePeriod" class="ma-2" color="indigo" outlined>
      스케줄 시간 변경
    </v-btn>
    <br />
    <v-btn @click="showPeriodHistory" class="ma-2" color="indigo" outlined>
      스케줄 변경 기록 보기
    </v-btn>
    <v-data-table
      :headers="headers"
      :items="periods"
      :items-per-page="5"
      class="elevation-1"
    />
    <CustomSnackBar />
  </v-container>
</template>

<script>
  import axios from "axios";
  import CustomSnackBar from "../components/CustomSnackBar";
  import {MESSAGE, SNACK_BAR_TYPE} from "../utils/constants";

  export default {
    name: "KakaoScheduler",
    components: {
      CustomSnackBar
    },
    data: () => ({
      expression: "",
      headers: [
        {
          text: "변경된 날짜 및 시간",
          align: "start",
        sortable: false,
        value: "changedDate"
      },
      { text: "정규화식", value: "expression" },
      { text: "변경한 사람", value: "member" }
    ],
    periods: []
  }),
  methods: {
    kakaoScheduling() {
      // do something
    },
    changePeriod() {
      if (this.expression === "") {
        this.$store.commit("SHOW_SNACKBAR", {
          type: SNACK_BAR_TYPE.INFO,
          message: MESSAGE.NO_INPUT
        });
      }
      axios
              .put("/kakao/scheduler/period", this.expression, {
                headers: {
                  "Content-Type": "application/json"
                }
              })
        .then(res => {
          console.log(res);
          if (res.data.data) {
            this.$store.commit("SHOW_SNACKBAR", {
              type: SNACK_BAR_TYPE.SUCCESS,
              message: MESSAGE.CHANGE_PERIOD
            });
          }
        })
        .catch(err => {
          // Todo alert 띄우는 것 aop로 중복 제거 안되나
          console.dir(err);
          this.$store.commit("SHOW_SNACKBAR", {
            type: SNACK_BAR_TYPE.ERROR,
            message: err.response.data.message,
            code: err.response.data.code
          });
        });
      this.expression = "";
    },
    showPeriodHistory() {
      this.periods = [];
      axios.get("/kakao/scheduler/period").then(res => {
        console.log(res);
        if (res.data.data) {
          res.data.data.map(period =>
                  this.periods.push({
                    changedDate: period.changedDate,
                    expression: period.expression,
                    member: period.member
                  })
          );
        }
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
