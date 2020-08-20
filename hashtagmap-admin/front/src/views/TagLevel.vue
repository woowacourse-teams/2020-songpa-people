<template>
  <v-container>
    <v-btn @click="showTagLevel" class="ma-2" color="indigo" outlined>
      태그레벨 기록 보기
    </v-btn>
    <v-data-table :headers="headers" :items="tagLevels" class="elevation-1" />
    <v-btn @click="updateTagLevel" class="ma-2" color="indigo" outlined>
      태그레벨 갱신하기
    </v-btn>
  </v-container>
</template>

<script>
import { SNACK_BAR_TEMPLATE } from "../utils/constants";
import { mapMutations } from "vuex";
import { tagLevelApi } from "@/request/api/tag-level.js";

export default {
  name: "TagLevel",
  data() {
    return {
      headers: [
        {
          text: "태그레벨",
          align: "start",
          sortable: false,
          value: "tagLevel"
        },
        { text: "최소 hashtag 개수", value: "minHashtagCount" },
        { text: "최대 hashtag 개수", value: "maxHashtagCount" }
      ],
      tagLevels: [
        {
          tagLevel: "",
          minHashtagCount: "",
          maxHashtagCount: ""
        }
      ]
    };
  },
  methods: {
    ...mapMutations("snackbar", ["SHOW_SNACKBAR"]),
    async showTagLevel() {
      try {
        const response = await tagLevelApi.show();
        this.tagLevels = response.data.data;
        this.SHOW_SNACKBAR(SNACK_BAR_TEMPLATE.SUCCESS);
      } catch (e) {
        this.SHOW_SNACKBAR(SNACK_BAR_TEMPLATE.ERROR(e));
      }
    },
    async updateTagLevel() {
      try {
        await tagLevelApi.update();
        this.SHOW_SNACKBAR(SNACK_BAR_TEMPLATE.SUCCESS);
      } catch (e) {
        this.SHOW_SNACKBAR(SNACK_BAR_TEMPLATE.ERROR(e));
      }
    }
  }
};
</script>
