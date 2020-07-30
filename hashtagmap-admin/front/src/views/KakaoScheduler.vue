<template>
  <v-container>
    <v-btn
      class="ma-2"
      color="indigo"
      outlined
      :loading="loading"
      :disabled="loading"
      @click="kakaoScheduling"
      >카카오 스케줄러 실행</v-btn
    >
  </v-container>
</template>

<script>
export default {
  name: "KakaoScheduler",
  data() {
    return {
      loading: false
    };
  },
  methods: {
    kakaoScheduling() {
      this.loading = true;

      //작업이 끝나면 this.loading = false;
    }
  }
};
</script>
