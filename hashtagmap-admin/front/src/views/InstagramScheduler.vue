<template>
  <v-container>
    <v-btn
      class="ma-2"
      color="indigo"
      outlined
      @click="instagramScheduling"
      >인스타 스케줄러 실행</v-btn
    >
  </v-container>
</template>

<script>
export default {
  name: "InstagramScheduler",
  methods: {
    instagramScheduling() {

      //작업이 끝나면 this.loading = false;
    }
  }
};
</script>
