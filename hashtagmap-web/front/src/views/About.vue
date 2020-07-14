<template>
  <div class="about">
    <h1>This is an about page</h1>
    <div id="kakao-map" style="width:500px;height:400px;"></div>
  </div>
</template>

<script>

  export default {
    mounted() {
      this.$createMap();
    }
  }
</script>
