<template>
  <v-app-bar absolute dense>
    <router-link to="/">
      <v-btn text>
        <v-img :src="logo" width="2rem" />
      </v-btn>
    </router-link>
    <v-toolbar-title>당신이 몰랐던 그 곳, 미소</v-toolbar-title>

    <v-spacer></v-spacer>

    <v-menu
      offset-y="true"
      transition="scroll-y-transition"
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
      isHiddenTagLevelContainer: true,
    };
  },

  methods: {},
};
</script>

<style scoped>
.tag-level-container {
  width: 250px;
  height: 185px;
}
</style>
