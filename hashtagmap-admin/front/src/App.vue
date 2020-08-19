<template>
  <v-app id="app">
    <v-navigation-drawer v-model="drawer" app clipped>
      <v-list>
        <v-list-item
          v-for="navigator in navigators"
          :key="navigator.title"
          :to="navigator.to"
        >
          <v-list-item-action>
            <v-icon> {{ navigator.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>kakao scheduler</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item to="/instagram-scheduler">
          <v-list-item-action>
            <v-icon>mdi-air-purifier</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>instagram scheduler</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item to="/district-manager">
          <v-list-item-action>
            <v-icon>mdi-flag-variant</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>district manager</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item to="/blacklist-manager">
          <v-list-item-action>
            <v-icon>mdi-sunglasses</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>blackList manager</v-list-item-title>
            <v-list-item-title> {{ navigator.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app clipped-left height="40px">
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>명소를 찾아서</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn v-if="isLogin" text @click="logout">
        로그아웃
      </v-btn>
    </v-app-bar>

    <v-main>
      <div v-if="isLogin">
        <router-view />
      </div>
      <Login v-else />
    </v-main>
  </v-app>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import Login from "@/components/Login";

export default {
  name: "App",

  components: {
    Login
  },
  data: () => {
    return {
      drawer: null,
      navigators: [
        { to: "/", title: "Home", icon: "mdi-account-box" },
        { to: "/kakao-scheduler", title: "kakao scheduler", icon: "mdi-chat" },
        {
          to: "/instagram-scheduler",
          title: "instagram scheduler",
          icon: "mdi-air-purifier"
        },
        {
          to: "/district-manage",
          title: "district-manage",
          icon: "mdi-flag-variant"
        },
        { to: "/tag-level", title: "Tag Level", icon: "mdi-air-purifier" }
      ]
    };
  },

  methods: {
    ...mapActions("member", ["logout"])
  },
  computed: { ...mapGetters("member", ["isLogin"]) }
};
</script>

<style scoped></style>
