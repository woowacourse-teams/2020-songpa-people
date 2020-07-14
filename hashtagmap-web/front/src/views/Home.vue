<template>
    <div class="home">
        <img alt="Vue logo" src="../assets/logo.png"/>
        <HelloWorld msg="Welcome to Your Vue.js App"/>
        <div>
            지도
            <div id="kakao-map" style="width:500px;height:400px;"></div>
        </div>
    </div>
</template>

<script>
    import HelloWorld from "@/components/HelloWorld.vue";

    export default {
        name: "Home",
        components: {
            HelloWorld
        },
        mounted() {
            this.$createMap();
        }

    };
</script>
