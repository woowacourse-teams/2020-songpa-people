<template>
    <div id="kakao-map">

    </div>
</template>

<script>
    export default {
        name: "KakaoMap",
        async mounted() {
            await this.$loadMap();
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