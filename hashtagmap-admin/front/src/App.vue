<template>
  <v-app id="app">
    <v-navigation-drawer v-model="drawer" app clipped>
      <v-list dense>
        <v-list-item to="/">
          <v-list-item-action>
            <v-icon>mdi-account-box</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item to="/kakao-scheduler">
          <v-list-item-action>
            <v-icon>mdi-chat</v-icon>
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
        <v-list-item to="/district-manage">
          <v-list-item-action>
            <v-icon>mdi-flag-variant</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>district manage</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item to="/tag-level">
          <v-list-item-action>
            <v-icon>mdi-air-purifier</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Tag Level</v-list-item-title>
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
      drawer: null
    };
  },

  methods: {
    ...mapActions("member", ["logout"])
  },
  computed: { ...mapGetters("member", ["isLogin"]) }
};
</script>

<style scoped></style>
