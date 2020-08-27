<template>
  <div>
    <MainPage v-if="isFirstVisit" />
    <router-view v-else />
  </div>
</template>

<script>
import MainPage from "./views/MainPage";
import { VISIT_KEY } from "./utils/constants";

export default {
  components: {
    MainPage,
  },

  computed: {
    isFirstVisit() {
      return VISIT_KEY !== localStorage.getItem(VISIT_KEY);
    },
  },
};
</script>

<style scoped></style>
