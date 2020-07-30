<template>
  <v-container>
    <v-img id="image" :src="faviconPath" contain width="400px" />
  </v-container>
</template>

<script>
export default {
  name: "InstagramScheduler",
  data() {
    return {
      faviconPath: require("../assets/bright-cow-image.png")
    };
  }
};
</script>

<style scoped>
#image {
  position: relative;
  left: 50%;
  transform: translate(-50%, 30%);
}
</style>
