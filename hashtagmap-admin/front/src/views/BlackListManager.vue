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
          <h2>Black List 등록 후 instagram Update</h2>
          <v-form>
            <v-row>
              <v-col md="4">
                <v-select
                  outlined
                  dense
                  :items="getSemiBlackList.map(item => item.kakaoId)"
                  v-model="blackListDataForUpdateInstagram.kakaoId"
                  label="바꿀 장소의 카카오 id"
                >
                </v-select>
              </v-col>
              <v-col md="4">
                <v-text-field
                  outlined
                  dense
                  v-model="blackListDataForUpdateInstagram.replaceName"
                  label="바꿀 이름"
                >
                </v-text-field>
              </v-col>
              <v-col md="4">
                <v-btn outlined color="indigo" @click="registerBlackList"
                  >변경하기
                </v-btn>
              </v-col>
            </v-row>
            <v-alert type="info">
              {{ addBlackListResult }}
            </v-alert>
          </v-form>
          <h2>Black List 등록 후 instagram Delete</h2>
          <v-form>
            <v-row>
              <v-col md="4">
                <v-select
                  outlined
                  dense
                  :items="getSemiBlackList.map(item => item.kakaoId)"
                  v-model="blackListDataForDeleteInstagram.kakaoId"
                  label="삭제할 장소의 카카오 id"
                >
                </v-select>
              </v-col>
              <v-col md="4">
                <v-btn outlined color="indigo" @click="deleteInstagram"
                  >삭제하기
                </v-btn>
              </v-col>
            </v-row>
          </v-form>
        </v-col>
      </v-row>
    </v-container>
    <CustomSnackBar />
  </v-app>
</template>

<script>
  import {mapActions, mapGetters, mapMutations} from "vuex";
  import CustomSnackBar from "@/components/CustomSnackBar";
  import {isOk} from "@/request";
  import {convert} from "@/utils/responseConverter";
  import {WEB_PAGE} from "@/utils/constants";

  export default {
  components: {
    CustomSnackBar
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
      },
      kakaoSearchKey: "",
      instagramSearchKey: "",
      blackListDataForUpdateInstagram: {
        kakaoId: "",
        replaceName: ""
      },
      blackListDataForDeleteInstagram: {
        kakaoId: "",
        replaceName: ""
      },
      addBlackListResult: "대체된 인스타그램 정보"
    };
  },

  methods: {
    ...mapActions("blackList", [
      "setSemiBlackList",
      "updateInstagramAfterAddBlackList",
      "deleteInstagramAfterAddBlackList"
    ]),
    ...mapMutations("snackbar", ["SHOW_SNACKBAR"]),
    openKakao() {
      window.open(WEB_PAGE.KAKAO, "_blank");
    },
    openInstagram() {
      window.open(WEB_PAGE.INSTAGRAM, "_blank");
    },
    async registerBlackList() {
      if (confirm(`${this.blackListDataForUpdateInstagram.replaceName}(으)로 등록합니까?`)) {
        const res = await this.updateInstagramAfterAddBlackList(
          this.blackListDataForUpdateInstagram
        );
        if (isOk(res)) {
          this.addBlackListResult = res.body.data;
        }
        const snackbarContents = convert.toSnackBarContent(res);
        this.SHOW_SNACKBAR(snackbarContents);
      }
    },
    async deleteInstagram() {
      if (
        confirm(`정말로 삭제하시겠습니까?(${this.blackListDataForDeleteInstagram.kakaoId})`)
      ) {
        const res = await this.deleteInstagramAfterAddBlackList(
          this.blackListDataForDeleteInstagram
        );
        const snackbarContents = convert.toSnackBarContent(res);
        this.SHOW_SNACKBAR(snackbarContents);
      }
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
</style>
