<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-app-bar absolute dense>
    <router-link to="/">
      <v-btn text>
        <v-img :src="logo" width="2rem" />
      </v-btn>
    </router-link>
    <v-toolbar-title>당신이 몰랐던 그 곳, 미소</v-toolbar-title>

    <v-spacer></v-spacer>

    <v-menu
      v-model="isShowTagLevelContainer"
      :offset-y="true"
      transition="scroll-y-transition"
      :close-on-click="false"
      :close-on-content-click="false"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="secondary" class="ma-2" v-bind="attrs" v-on="on" icon>
          <v-icon>{{ menuBarIcon }}</v-icon>
        </v-btn>
      </template>
      <div class="tag-level-container">
        <TagLevelContainer />
      </div>
    </v-menu>
  </v-app-bar>
</template>

<script>
import { mdiMenu } from "@mdi/js";
import TagLevelContainer from "@/components/tag-level/TagLevelContainer";

export default {
  components: {
    TagLevelContainer,
  },

  data() {
    return {
      logo: require("../../public/img/icons/themiso-icon-192x192.png"),
      menuBarIcon: mdiMenu,
      isShowTagLevelContainer: true,
    };
  },

  methods: {},
};
</script>

<style lang="stylus" scoped>
.tag-level-container {
  width: 250px;
  height: 246px;
}

.v-menu__content {
  transform: translateY(0px) !important;
}

.v-btn:not(.v-btn--text):not(.v-btn--outlined):focus:before {
  opacity: 0 !important;
}
</style>
