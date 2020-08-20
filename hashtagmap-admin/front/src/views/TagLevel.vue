<template>
  <v-container>
    <v-btn @click="showTagLevel" class="ma-2" color="indigo" outlined>
      태그레벨 기록 보기
    </v-btn>
    <v-data-table
      :headers="headers"
      :items="getTagLevels"
      class="elevation-1"
    />
    <v-btn @click="update" class="ma-2" color="indigo" outlined>
      태그레벨 갱신하기
    </v-btn>
    <CustomSnackbar />
  </v-container>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
import { convert } from "@/utils/responseConverter";
import CustomSnackbar from "@/components/CustomSnackBar";

export default {
  name: "TagLevel",
  components: {
    CustomSnackbar
  },
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
      ]
    };
  },
  computed: {
    ...mapGetters("tagLevel", ["getTagLevels"])
  },
  methods: {
    ...mapMutations("snackbar", ["SHOW_SNACKBAR"]),
    ...mapActions("tagLevel", ["updateTagLevel", "fetchTagLevels"]),

    async showTagLevel() {
      const response = await this.fetchTagLevels();
      const snackbarContent = convert.toSnackBarContent(response);
      console.dir(snackbarContent);
      this.SHOW_SNACKBAR(snackbarContent);
    },
    async update() {
      const response = await this.updateTagLevel();
      const snackbarContent = convert.toSnackBarContent(response);
      this.SHOW_SNACKBAR(snackbarContent);
    }
  }
};
</script>
