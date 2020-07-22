<template>
  <v-app>
    <v-main>
        <KakaoMap/>
    </v-main>
  </v-app>
</template>

<script>
  import KakaoMap from "@/views/KakaoMap";

  export default {
    name: "App",

  components: {
    KakaoMap,
  },

    data: () => ({
      //
    }),
};
</script>

<style lang="stylus">
    #app
        font-family Avenir, Helvetica, Arial, sans-serif
        -webkit-font-smoothing antialiased
        -moz-osx-font-smoothing grayscale
        text-align center
        color #2c3e50
        margin-top 60px
</style>