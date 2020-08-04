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
    <CustomSnackbar />
    <v-btn @click="toggleKakaoScheduling" class="ma-2" color="indigo" outlined
    >카카오 스케줄러 토글
    </v-btn
    >
    <v-btn
            :color="kakaoScheduleActiveStatus.color"
            @click="getKakaoScheduleActiveStatus"
            class="ma-2"
            outlined
    >상태 : {{ kakaoScheduleActiveStatus.message }}
    </v-btn>
  </v-container>
</template>

<script>
  import axios from "axios";
  import {mapMutations, mapActions, mapState} from "vuex";
  import CustomSnackbar from "../components/CustomSnackBar";
  import {MESSAGE, SNACK_BAR_TYPE} from "../utils/constants";

  export default {
    name: "KakaoScheduler",
    components: {
      CustomSnackbar
    },
    data() {
      return {
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
    };
  },
    created() {
      this.getKakaoScheduleActiveStatus();
    },
    computed: {
      ...mapState("kakao", ["kakaoScheduleActiveStatus"])
    },
  methods: {
    toggleKakaoScheduling() {
      //작업이 끝나면 this.loading = false;
    },
    ...mapActions("kakao", ["getKakaoScheduleActiveStatus"])
    ...mapMutations(["showSnackbar"]),
    kakaoScheduling() {
      // do something
    },
    async changePeriod() {
      if (this.expression === "") {
        this.showSnackbar({
          type: SNACK_BAR_TYPE.INFO,
          message: MESSAGE.NO_INPUT
        });
        return;
      }

      try {
        await axios.put("/kakao/scheduler/period", this.expression, {
          headers: {
            "Content-Type": "application/json"
          }
        });
        this.showSnackbar({
          type: SNACK_BAR_TYPE.SUCCESS,
          message: MESSAGE.SUCCESS
        });
      } catch (e) {
        this.showSnackbar({
          type: SNACK_BAR_TYPE.ERROR,
          message: e.response.data.message,
          code: e.response.data.code
        });
      } finally {
        this.expression = "";
      }
    },
    async showPeriodHistory() {
      try {
        this.periods = [];
        const res = await axios.get("/kakao/scheduler/period");
        console.dir(res);
        if (res.data.data.length == 1) {
          this.showSnackbar({
            type: SNACK_BAR_TYPE.INFO,
            message: MESSAGE.NO_CONTENT
          });
          return;
        }
        res.data.data.map(period =>
                this.periods.push({
                  changedDate: period.changedDate,
                  expression: period.expression,
                  member: period.member
                })
        );
      } catch (e) {
        this.showSnackbar({
          type: SNACK_BAR_TYPE.ERROR,
          message: e.response.data.message,
          code: e.response.data.code
        });
      }
    }
  }
};
</script>

<style>
.period-input {
  width: 200px;
}
</style>
