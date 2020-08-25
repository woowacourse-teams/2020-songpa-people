<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col>
          <v-btn
            class="margin-1"
            outlined
            color="indigo"
            @click="setSemiBlackList"
            >blackList 갱신
          </v-btn>
          <v-btn class="margin-1" outlined color="indigo" @click="openKakao"
            >카카오맵 열기
          </v-btn>
          <v-btn class="margin-1" outlined color="indigo" @click="openInstagram"
            >인스타그램 열기
          </v-btn>
          <v-data-table
            :headers="subBlackListHeaders"
            :items="getSemiBlackList"
            class="elevation-1"
            options="tableOptions"
            dense
          ></v-data-table>
        </v-col>
        <v-col>
          <UpdateInstagramAfterAddBlackListForm class="height-250px" />
          <DeleteInstagramAfterAddBlackListForm class="height-250px" />
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import { WEB_PAGE } from "@/utils/constants";
import UpdateInstagramAfterAddBlackListForm from "@/components/blacklist/UpdateInstagramAfterAddBlackListForm";
import DeleteInstagramAfterAddBlackListForm from "@/components/blacklist/DeleteInstagramAfterAddBlackListForm";

export default {
  components: {
    UpdateInstagramAfterAddBlackListForm,
    DeleteInstagramAfterAddBlackListForm
  },

  data() {
    return {
      subBlackListHeaders: [
        { text: "kakao Id", value: "kakaoId" },
        { text: "장소 이름", value: "placeName" },
        { text: "해시태그 이름", value: "hashtagName" },
        { text: "해시태그 수", value: "hashtagCount" },
        { text: "주소", value: "roadAddressName" }
      ],
      tableOptions: {
        sortBy: "hashtagCount",
        sortDesc: true
      }
    };
  },

  methods: {
    ...mapActions("blackList", ["setSemiBlackList"]),
    openKakao() {
      window.open(WEB_PAGE.KAKAO, "_blank");
    },
    openInstagram() {
      window.open(WEB_PAGE.INSTAGRAM, "_blank");
    }
  },

  computed: {
    ...mapGetters("blackList", ["getSemiBlackList"])
  }
};
</script>

<style scoped>
.margin-1 {
  margin: 3px 0 3px 3px;
  padding: 3px 0 3px 3px;
}
.height-250px {
  height: 250px;
}
</style>
