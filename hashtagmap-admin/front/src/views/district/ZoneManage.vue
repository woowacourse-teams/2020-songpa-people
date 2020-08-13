<template>
  <v-container>
    <v-row>
      <v-col>
        <v-subheader>좌표 데이터 목록</v-subheader>
      </v-col>
      <v-col class="text-right">
        <v-btn class="ma-2" color="indigo" outlined @click="findAllZone">
          좌표 데이터 목록 조회
        </v-btn>
      </v-col>
    </v-row>
    <v-data-table
      v-model="selected"
      :headers="headers"
      :items="getZones"
      :items-per-page="5"
      item-key="zoneId"
      show-select
      class="elevation-1"
    >
      <template v-slot:item.actions="{ item }">
        <v-icon small class="mr-2" @click="showEditModal('zoneEdit', item)">
          mdi-pencil
        </v-icon>
      </template>
    </v-data-table>

    <v-row>
      <v-col class="col-sm-3">
        <DistrictNameSelectBox />
      </v-col>
      <v-col class="text-right">
        <v-btn class="ma-2" color="error" outlined @click="deleteSelectedZone">
          삭제
        </v-btn>
      </v-col>
    </v-row>

    <v-row>
      <v-col class="col-sm-3">
        <v-text-field
          label="좌상단 위도(y) 입력"
          hint="36.xxxx"
          :value="getZoneInput.topLeftLatitude"
          @input="INPUT_NEW_TOP_LEFT_LATITUDE"
        />
      </v-col>
      <v-col class="col-sm-3">
        <v-text-field
          label="좌상단 경도(x) 입력"
          hint="124.xxxx"
          :value="getZoneInput.topLeftLongitude"
          @input="INPUT_NEW_TOP_LEFT_LONGITUDE"
        />
      </v-col>
      <v-col class="col-sm-3">
        <v-text-field
          label="우하단 위도(y) 입력"
          hint="36.xxxx"
          :value="getZoneInput.bottomRightLatitude"
          @input="INPUT_NEW_BOTTOM_RIGHT_LATITUDE"
        />
      </v-col>
      <v-col class="col-sm-3">
        <v-text-field
          label="우하단 경도(x) 입력"
          hint="124.xxxx"
          :value="getZoneInput.bottomRightLongitude"
          @input="INPUT_NEW_BOTTOM_RIGHT_LONGITUDE"
        />
      </v-col>
      <v-col>
        <v-btn class="ma-2" color="indigo" outlined @click="addNewZone">
          저장
        </v-btn>
      </v-col>
    </v-row>
    <DefaultModal
      modalName="zoneEdit"
      title="좌표 수정"
      :ok-event="editZone"
      ok-event-text="수정하기"
    >
      <v-row>
        <v-col cols="12" sm="6" md="4">
          <v-text-field
            :value="getEditTargetZone.topLeftLatitude"
            @input="INPUT_EDIT_TOP_LEFT_LATITUDE"
            label="좌상단 위도(y) 입력"
            hint="36.xxx"
          />
        </v-col>
        <v-col cols="12" sm="6" md="4">
          <v-text-field
            :value="getEditTargetZone.topLeftLongitude"
            @input="INPUT_EDIT_TOP_LEFT_LONGITUDE"
            label="좌상단 경도(x) 입력"
            hint="127.xxx"
          />
        </v-col>
        <v-col cols="12" sm="6" md="4">
          <DistrictNameSelectBox />
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" sm="6" md="4">
          <v-text-field
            :value="getEditTargetZone.bottomRightLatitude"
            @input="INPUT_EDIT_BOTTOM_RIGHT_LATITUDE"
            label="우하단 위도(y) 입력"
            hint="36.xxx"
          />
        </v-col>
        <v-col cols="12" sm="6" md="4">
          <v-text-field
            :value="getEditTargetZone.bottomRightLongitude"
            @input="INPUT_EDIT_BOTTOM_RIGHT_LONGITUDE"
            label="우하단 경도(x) 입력"
            hint="127.xxx"
          />
        </v-col>
      </v-row>
    </DefaultModal>
    <CustomSnackbar />
  </v-container>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
import CustomSnackbar from "@/components/CustomSnackBar";
import DefaultModal from "@/components/DefaultModal";
import DistrictNameSelectBox from "@/components/DistrictNameSelectBox";
export default {
  name: "ZoneManage",
  components: {
    DefaultModal,
    CustomSnackbar,
    DistrictNameSelectBox
  },
  created() {
    this.fetchDistrictNames();
  },
  data: () => {
    return {
      names: [
        { id: 1, text: "a" },
        { id: 2, text: "b" }
      ],
      selected: [],
      headers: [
        { text: "ID", value: "zoneId", align: "start" },
        { text: "지역 ID", value: "districtId" },
        { text: "지역 이름", value: "districtName" },
        { text: "좌상단 위도(y)", value: "topLeftLatitude" },
        { text: "좌상단 경도(x)", value: "topLeftLongitude" },
        { text: "우하단 위도(y)", value: "bottomRightLatitude" },
        { text: "우하단 경도(x)", value: "bottomRightLongitude" },
        { text: "생성 날짜", value: "createdTime" },
        { text: "변경 시간", value: "updatedTime" },
        { text: "변경한 사람", value: "memberName" },
        { text: "수정", value: "actions", sortable: false }
      ]
    };
  },
  computed: {
    ...mapGetters("zone", ["getZones", "getZoneInput", "getEditTargetZone"])
  },
  methods: {
    ...mapMutations("modal", ["ACTIVATE_MODAL", "DEACTIVATE_MODAL"]),
    ...mapMutations("snackbar", ["SHOW_SNACKBAR"]),
    ...mapMutations("zone", [
      "INPUT_ZONE",
      "CLEAR_ZONE_INPUT",
      "ADD_ZONE",
      "CLEAR_ZONES",
      "INPUT_NEW_TOP_LEFT_LATITUDE",
      "INPUT_NEW_TOP_LEFT_LONGITUDE",
      "INPUT_NEW_BOTTOM_RIGHT_LATITUDE",
      "INPUT_NEW_BOTTOM_RIGHT_LONGITUDE",
      "INPUT_EDIT_TOP_LEFT_LATITUDE",
      "INPUT_EDIT_TOP_LEFT_LONGITUDE",
      "INPUT_EDIT_BOTTOM_RIGHT_LATITUDE",
      "INPUT_EDIT_BOTTOM_RIGHT_LONGITUDE",
      "SET_EDIT_TARGET_ZONE",
      "CLEAR_SELECT_DISTRICT_NAME"
    ]),
    ...mapActions("zone", [
      "setZone",
      "removeZones",
      "modifyZone",
      "fetchZones",
      "fetchDistrictNames"
    ]),
    async findAllZone() {
      const snackbarContents = await this.fetchZones();
      this.SHOW_SNACKBAR(snackbarContents);
    },
    async addNewZone() {
      const snackbarContents = await this.setZone();
      this.SHOW_SNACKBAR(snackbarContents);
      this.CLEAR_SELECT_DISTRICT_NAME();
    },
    deleteSelectedZone() {
      this.removeZones(this.selected);
    },
    showEditModal(modalName, zone) {
      this.CLEAR_SELECT_DISTRICT_NAME();
      this.SET_EDIT_TARGET_ZONE(zone);
      this.ACTIVATE_MODAL(modalName);
    },
    async editZone() {
      await this.modifyZone();
      await this.fetchZones();
      this.CLEAR_SELECT_DISTRICT_NAME();
    }
  }
};
</script>

<style scoped></style>
