<template>
    <div id="kakao-map" style="width:500px;height:400px;">

    </div>
</template>

<script>
    export default {
        name: "KakaoMap",
        mounted() {
            this.$loadMap();
        }
    }
</script>

<style scoped>
</style>