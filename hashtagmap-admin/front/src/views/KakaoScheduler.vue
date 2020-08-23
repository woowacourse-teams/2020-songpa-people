<template>
  <v-container>
    <div>
      <v-btn @click="startKakaoScheduler" class="ma-2" color="primary" outlined
        >카카오 스케줄러 실행
      </v-btn>
      <v-btn @click="stopKakaoScheduler" class="ma-2" color="error" outlined
        >카카오 스케줄러 정지
      </v-btn>
      <v-btn
        :color="getScheduleActiveStatus.color"
        @click="fetchScheduleActiveStatus"
        class="ma-2"
        outlined
        >상태 : {{ getScheduleActiveStatus.message }}
      </v-btn>
    </div>
    <div>
      <v-btn
        @click="toggleKakaoSchedulerAutoRunnable"
        class="ma-2"
        color="indigo"
        outlined
        >카카오 자동 실행 상태 변경
      </v-btn>
      <v-btn
        :color="getSchedulerAutoRunnableStatus.color"
        @click="fetchScheduleAutoRunnable"
        class="ma-2"
        outlined
        >상태 : {{ getSchedulerAutoRunnableStatus.message }}
      </v-btn>
    </div>
    <div class="period-input">
      <v-form v-model="cronValid">
        <v-text-field
          :value="getExpression"
          @input="INPUT_EXPRESSION"
          :rules="rules.kakao.cron"
          label="정규 표현식"
          hint="(예) 0 0/5 * * * ?"
        />
      </v-form>
    </div>
    <v-btn
      :disabled="!cronValid || isScheduleRunning"
      @click="changePeriod"
      class="ma-2"
      color="indigo"
      outlined
    >
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
import validator from "@/utils/validator";
import { convert } from "@/utils/responseConverter";

export default {
  name: "KakaoScheduler",

  components: {
    CustomSnackbar
  },

  data: () => {
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
      ],
      cronValid: false,
      rules: {
        ...validator
      }
    };
  },

  created() {
    this.fetchScheduleActiveStatus();
    this.fetchScheduleAutoRunnable();
  },

  computed: {
    ...mapGetters("kakao", [
      "getPeriodHistory",
      "getExpression",
      "getScheduleActiveStatus",
      "getSchedulerAutoRunnableStatus",
      "isScheduleRunning"
    ])
  },

  methods: {
    ...mapActions("kakao", [
      "startKakaoScheduler",
      "stopKakaoScheduler",
      "fetchScheduleActiveStatus",
      "fetchScheduleAutoRunnable",
      "setPeriod",
      "findPeriodHistory",
      "toggleKakaoSchedulerAutoRunnable"
    ]),
    ...mapMutations("snackbar", ["SHOW_SNACKBAR"]),
    ...mapMutations("kakao", ["INPUT_EXPRESSION"]),
    async changePeriod() {
      const response = await this.setPeriod();
      const snackbarContents = convert.toSnackBarContent(response);
      this.SHOW_SNACKBAR(snackbarContents);
    },
    async showPeriodHistory() {
      const response = await this.findPeriodHistory();
      const snackbarContents = convert.toSnackBarContent(response);
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
