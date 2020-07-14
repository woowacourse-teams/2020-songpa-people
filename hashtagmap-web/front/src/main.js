import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import KakaoMap from "@/plugins/KakaoMap";

Vue.config.productionTip = false;
Vue.use(KakaoMap);

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount("#app");
