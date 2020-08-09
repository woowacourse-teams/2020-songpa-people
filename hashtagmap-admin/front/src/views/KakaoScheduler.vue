<template>
  <v-container>
    <v-btn @click="toggleKakaoSchedule" class="ma-2" color="indigo" outlined
      >카카오 스케줄러 토글
    </v-btn>
    <v-btn
      :color="getScheduleActiveStatus.color"
      @click="fetchScheduleActiveStatus"
      class="ma-2"
      outlined
      >상태 : {{ getScheduleActiveStatus.message }}
    </v-btn>
    <div class="period-input">
      <v-text-field
        :value="getExpression"
        @input="INPUT_EXPRESSION"
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
      :items="getPeriodHistory"
      :items-per-page="5"
      class="elevation-1"
    />
    <CustomSnackbar />
  </v-container>
</template>

<script>
import { mapMutations, mapActions, mapGetters } from "vuex";
import CustomSnackbar from "../components/CustomSnackBar";

export default {
  name: "KakaoScheduler",
  components: {
    CustomSnackbar
  },
  data() {
    return {
      headers: [
        {
          text: "변경된 날짜 및 시간",
          align: "start",
          sortable: false,
          value: "changedDate"
        },
        { text: "정규화식", value: "expression" },
        { text: "변경한 사람", value: "member" }
      ]
    };
  },
  created() {
    this.fetchScheduleActiveStatus();
  },
  computed: {
    ...mapGetters("kakao", [
      "getPeriodHistory",
      "getExpression",
      "getScheduleActiveStatus"
    ])
  },
  methods: {
    ...mapActions("kakao", [
      "toggleKakaoSchedule",
      "fetchScheduleActiveStatus",
      "setPeriod",
      "findPeriodHistory"
    ]),
    ...mapMutations("snackbar",["SHOW_SNACKBAR"]),
    ...mapMutations("kakao", ["INPUT_EXPRESSION"]),
    async changePeriod() {
      const snackbarContents = await this.setPeriod();
      this.SHOW_SNACKBAR(snackbarContents);
    },
    async showPeriodHistory() {
      const snackbarContents = await this.findPeriodHistory();
      this.SHOW_SNACKBAR(snackbarContents);
    }
  }
};
</script>

<style>
.period-input {
  width: 200px;
}
</style>
