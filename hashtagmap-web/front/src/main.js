import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import KakaoMap from "@/plugins/KakaoMap";
import vuetify from "./plugins/vuetify";

Vue.config.productionTip = false;
Vue.use(KakaoMap);

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App),
}).$mount("#app");
