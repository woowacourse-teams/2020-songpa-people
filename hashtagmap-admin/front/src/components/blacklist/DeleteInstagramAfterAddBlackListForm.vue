<template>
  <v-app>
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
          <v-dialog v-model="deleteDialog" persistent max-width="300px">
            <template v-slot:activator="{ on }">
              <v-btn color="indigo" outlined v-on="on">
                삭제
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="headline"
                >'{{ blackListDataForDeleteInstagram.kakaoId }}'을
                삭제하시겠습니까?</v-card-title
              >
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="deleteDialog = false"
                  >취소
                </v-btn>
                <v-btn color="red darken-1" text @click="deleteInstagram"
                  >삭제
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-col>
      </v-row>
    </v-form>
    <CustomSnackBar />
  </v-app>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
import { convert } from "@/utils/responseConverter";
import CustomSnackBar from "@/components/CustomSnackBar";

export default {
  components: {
    CustomSnackBar
  },
  data() {
    return {
      deleteDialog: false,
      blackListDataForDeleteInstagram: {
        kakaoId: "",
        replaceName: ""
      }
    };
  },
  methods: {
    ...mapActions("blackList", [
      "setSemiBlackList",
      "deleteInstagramAfterAddBlackList"
    ]),
    ...mapMutations("snackbar", ["SHOW_SNACKBAR"]),
    async deleteInstagram() {
      this.deleteDialog = false;
      const res = await this.deleteInstagramAfterAddBlackList(
        this.blackListDataForDeleteInstagram
      );
      const snackbarContents = convert.toSnackBarContent(res);
      this.SHOW_SNACKBAR(snackbarContents);
    }
  },

  computed: {
    ...mapGetters("blackList", ["getSemiBlackList"])
  }
};
</script>

<style scoped></style>
