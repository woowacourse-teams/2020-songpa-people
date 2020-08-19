<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col>
          <v-btn outlined color="indigo" @click="setSubBlackList"
            >blackList 갱신</v-btn
          >
          <v-data-table
            :headers="subBlackListHeaders"
            :items="getSubBlackList"
            class="elevation-1"
            options="tableOptions"
            dense
          ></v-data-table>
        </v-col>
        <v-col> </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  data() {
    return {
      subBlackListHeaders: [
        { text: "장소 이름", value: "placeName" },
        { text: "해쉬태그 수", value: "hashtagCount" },
        { text: "주소", value: "roadAddressName" }
      ],
      tableOptions: {
        sortBy: "hashtagCount",
        sortDesc: true
      }
    };
  },

  methods: {
    ...mapActions("blacklist", ["setSubBlackList"])
  },

  computed: {
    ...mapGetters("blacklist", ["getSubBlackList"])
  }
};
</script>

<style scoped></style>
