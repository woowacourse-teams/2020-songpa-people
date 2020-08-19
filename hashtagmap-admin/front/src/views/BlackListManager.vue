<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col>
          <v-btn
            class="margin-1"
            outlined
            color="indigo"
            @click="setSubBlackList"
            >blackList 갱신</v-btn
          >
          <v-btn class="margin-1" outlined color="indigo" @click="openKakao"
            >카카오맵 열기</v-btn
          >
          <v-btn class="margin-1" outlined color="indigo" @click="openInstagram"
            >인스타그램 열기</v-btn
          >
          <v-data-table
            :headers="subBlackListHeaders"
            :items="getSubBlackList"
            class="elevation-1"
            options="tableOptions"
            dense
          ></v-data-table>
        </v-col>
        <v-col>
          <h2>blackList 등록하기</h2>
          <v-form>
            <v-row>
              <v-col md="4">
                <v-select
                  outlined
                  dense
                  v-model="inputBlackList.originName"
                  :items="getSubBlackList.map(item => item.placeName)"
                  label="이전 이름"
                >
                </v-select>
              </v-col>
              <v-col md="4">
                <v-text-field
                  outlined
                  dense
                  :rules="replaceNameRule"
                  v-model="inputBlackList.replaceName"
                  label="바꿀 이름"
                >
                </v-text-field>
              </v-col>
              <v-col md="4">
                <v-btn outlined color="indigo" @click="registerBlackList"
                  >변경하기</v-btn
                >
              </v-col>
            </v-row>
          </v-form>
        </v-col>
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
      instagramSearchKey: "",
      inputBlackList: {
        originName: "",
        replaceName: ""
      },
      replaceNameRule: [v => !!v || "바꿀 이름을 입력하세요"]
    };
  },

  methods: {
    ...mapActions("blackList", ["setSubBlackList", "addBlackList"]),
    openKakao() {
      window.open("https://map.kakao.com/", "_blank");
    },
    openInstagram() {
      window.open("https://www.instagram.com/", "_blank");
    },
    registerBlackList() {
      if (confirm(`${this.inputBlackList.replaceName}으로 등록합니까?`)) {
        this.addBlackList(this.inputBlackList);
      }
    }
  },

  computed: {
    ...mapGetters("blackList", ["getSubBlackList"])
  }
};
</script>

<style scoped>
.margin-1 {
  margin: 3px 0 3px 3px;
  padding: 3px 0 3px 3px;
}
</style>
