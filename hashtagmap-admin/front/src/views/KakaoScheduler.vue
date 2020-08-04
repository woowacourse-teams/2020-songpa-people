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
  </v-container>
</template>

<script>
  import axios from "axios";

  export default {
    name: "KakaoScheduler",
    data: () => ({
      period: ""
    }),
    methods: {
      kakaoScheduling() {
        // do something
      },
      changePeriod() {
        if (this.period == "") {
        alert("정규식을 입력해주세요");
      }
      axios
        .post("/kakao-scheduler/change-period", this.period)
        .then(res => {
          console.log(res);
          if (res.data.data) {
            alert("성공했습니다.");
          } else {
            alert("실패했습니다. 에러코드: " + res.data.code + ", " + res.data.message);
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
