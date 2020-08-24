<template>
  <v-app>
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
          <v-dialog v-model="updateDialog" persistent max-width="300px">
            <template v-slot:activator="{ on }">
              <v-btn color="indigo" outlined v-on="on" :disabled="buttonDisabled">
                변경하기
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="headline"
                >'{{ blackListDataForUpdateInstagram.replaceName }}'으로
                변경하시겠습니까?</v-card-title
              >
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="updateDialog = false"
                  >취소
                </v-btn>
                <v-btn color="red darken-1" text @click="registerBlackList"
                  >변경
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-col>
      </v-row>
      <v-alert type="info">
        {{ addBlackListResult }}
      </v-alert>
    </v-form>
    <CustomSnackBar />
  </v-app>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
import CustomSnackBar from "@/components/CustomSnackBar";
import { isOk } from "@/request";
import { convert } from "@/utils/responseConverter";

export default {
  components: {
    CustomSnackBar
  },
  data() {
    return {
      updateDialog: false,
      blackListDataForUpdateInstagram: {
        kakaoId: "",
        replaceName: ""
      },
      addBlackListResult: "대체된 인스타그램 정보"
    };
  },

  methods: {
    ...mapActions("blackList", ["updateInstagramAfterAddBlackList"]),
    ...mapMutations("snackbar", ["SHOW_SNACKBAR"]),
    async registerBlackList() {
      this.updateDialog = false;
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

  computed: {
    ...mapGetters("blackList", ["getSemiBlackList"]),
    buttonDisabled() {
      return this.blackListDataForUpdateInstagram.replaceName === "";
    }
  }
};
</script>

<style scoped>
</style>
