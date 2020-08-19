<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col>
          <v-btn class="margin-1" outlined color="indigo" @click="setSubBlackList"
            >blackList 갱신</v-btn
          >
          <v-btn class="margin-1" outlined color="indigo" @click="openKakao">카카오맵 열기</v-btn>
          <v-btn class="margin-1" outlined color="indigo" @click="openInstagram">인스타그램 열기</v-btn>
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
      },
      kakaoSearchKey: "",
      instagramSearchKey: ""
    };
  },

  methods: {
    ...mapActions("blacklist", ["setSubBlackList"]),
    openKakao() {
      window.open("https://map.kakao.com/", "_blank");
    },
    openInstagram() {
      window.open("https://www.instagram.com/", "_blank");
    }
  },

  computed: {
    ...mapGetters("blacklist", ["getSubBlackList"])
  }
};
</script>

<style scoped>
  .margin-1 {
    margin: 3px 0 3px 3px;
    padding: 3px 0 3px 3px;
  }
</style>
