<template>
  <div>
    <v-container>
      <v-row>
        <v-col>
          <v-subheader>지역 데이터 목록</v-subheader>
        </v-col>
        <v-col class="text-right">
          <v-btn class="ma-2" color="indigo" outlined @click="findAllDistricts">
            지역 데이터 목록 조회
          </v-btn>
        </v-col>
      </v-row>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="getDistricts"
        :items-per-page="5"
        item-key="districtId"
        show-select
        class="elevation-1"
      >
        <template v-slot:item.actions="{ item }">
          <v-icon
            small
            class="mr-2"
            @click="showEditModal('districtEdit', item)"
          >
            mdi-pencil
          </v-icon>
        </template>
      </v-data-table>
      <v-row>
        <v-col class="col-sm-3">
          <v-text-field
            label="구 이름"
            hint="(예) 서울 송파구"
            :value="getDistrictInput"
            @input="INPUT_DISTRICT_TEXT"
          />
        </v-col>
        <v-col>
          <v-btn class="ma-2" color="indigo" outlined @click="addNewDistrict">
            저장
          </v-btn>
        </v-col>
        <v-col class="text-right">
          <v-dialog v-model="dialog" persistent max-width="300px">
            <template v-slot:activator="{ on }">
              <v-btn class="ma-2" color="error" outlined dark v-on="on">
                삭제
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="headline">삭제하시겠습니까?</v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false"
                  >취소</v-btn
                >
                <v-btn color="red darken-1" text @click="deleteSelectedDistrict"
                  >삭제</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-col>
      </v-row>

      <DefaultModal
        modalName="districtEdit"
        title="구역 수정"
        :ok-event="editDistrict"
        ok-event-text="수정하기"
      >
        <v-row>
          <v-col cols="12" sm="6" md="4">
            <v-text-field
              :value="getEditTargetDistrictName"
              @input="INPUT_EDIT_TARGET_DISTRICT_NAME"
              label="변경할 지역 이름"
            />
          </v-col>
        </v-row>
      </DefaultModal>
      <CustomSnackbar />
    </v-container>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
import CustomSnackbar from "@/components/CustomSnackBar";
import DefaultModal from "@/components/DefaultModal";

export default {
  name: "DistrictManage",
  components: {
    DefaultModal,
    CustomSnackbar
  },
  data: () => {
    return {
      dialog: false,
      selected: [],
      headers: [
        { text: "ID", value: "districtId", align: "start" },
        { text: "지역 이름", value: "districtName" },
        { text: "생성 날짜", value: "createdTime" },
        { text: "변경 시간", value: "updatedTime" },
        { text: "변경한 사람", value: "memberName" },
        { text: "수정", value: "actions", sortable: false }
      ]
    };
  },
  computed: {
    ...mapGetters("district", [
      "getDistricts",
      "getDistrictInput",
      "getEditTargetDistrictName"
    ])
  },
  methods: {
    ...mapActions("district", [
      "setDistrict",
      "fetchDistricts",
      "removeDistricts",
      "modifyDistrict"
    ]),
    ...mapMutations("modal", ["ACTIVATE_MODAL", "DEACTIVATE_MODAL"]),
    ...mapMutations("snackbar", ["SHOW_SNACKBAR"]),
    ...mapMutations("district", [
      "INPUT_DISTRICT_TEXT",
      "INPUT_EDIT_TARGET_DISTRICT_NAME",
      "SET_EDIT_TARGET_DISTRICT"
    ]),
    async addNewDistrict() {
      const snackbarContents = await this.setDistrict();
      this.SHOW_SNACKBAR(snackbarContents);
    },
    async findAllDistricts() {
      const snackbarContents = await this.fetchDistricts();
      this.SHOW_SNACKBAR(snackbarContents);
    },
    deleteSelectedDistrict() {
      this.dialog = false;
      this.removeDistricts(this.selected);
    },
    showEditModal(modalName, district) {
      this.SET_EDIT_TARGET_DISTRICT(district);
      this.ACTIVATE_MODAL(modalName);
    },
    async editDistrict() {
      await this.modifyDistrict();
      await this.fetchDistricts();
    }
  }
};
</script>

<style scoped></style>
