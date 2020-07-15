<template>
    <div id="kakao-map">

    </div>
</template>

<script>
    export default {
        name: "KakaoMap",
        mounted() {
            this.$loadMap();
            console.log(1111111)
            this.$loadCurrentPosition();
        }
    }
</script>

<style scoped>
    #kakao-map {
        width: 100%;
        height: 600px;
    }
</style>