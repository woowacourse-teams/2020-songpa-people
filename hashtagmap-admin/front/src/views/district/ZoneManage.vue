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
        <v-select
          :items="getDistrictNames"
          v-model="zoneInput.districtName"
          @click:append-outer="fetchDistrictNames"
          append-outer-icon="mdi-refresh"
          label="지역 선택"
          solo
        >
        </v-select>
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
                >취소
              </v-btn>
              <v-btn color="red darken-1" text @click="deleteSelectedZone"
                >삭제
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>
    <v-form v-model="isValidForNew">
      <v-row>
        <v-col class="col-sm-3">
          <v-text-field
            label="좌상단 위도(y) 입력"
            hint="36.xxxx"
            v-model="zoneInput.topLeftLatitude"
            :rules="rules.zone.latitude"
            required
          />
        </v-col>
        <v-col class="col-sm-3">
          <v-text-field
            label="좌상단 경도(x) 입력"
            hint="124.xxxx"
            v-model="zoneInput.topLeftLongitude"
            :rules="rules.zone.longitude"
            required
          />
        </v-col>
        <v-col class="col-sm-3">
          <v-text-field
            label="우하단 위도(y) 입력"
            hint="36.xxxx"
            v-model="zoneInput.bottomRightLatitude"
            :rules="rules.zone.latitude"
            required
          />
        </v-col>
        <v-col class="col-sm-3">
          <v-text-field
            label="우하단 경도(x) 입력"
            hint="124.xxxx"
            v-model="zoneInput.bottomRightLongitude"
            :rules="rules.zone.longitude"
            required
          />
        </v-col>
        <v-col>
          <v-btn
            :disabled="!isValidForNew"
            class="ma-2"
            color="indigo"
            outlined
            @click="addNewZone"
          >
            저장
          </v-btn>
        </v-col>
      </v-row>
    </v-form>
    <DefaultModal
      modalName="zoneEdit"
      title="좌표 수정"
      :ok-eventHistory="editZone"
      ok-eventHistory-text="수정하기"
      :is-valid="isValidForEdit"
    >
      <v-form v-model="isValidForEdit">
        <v-row>
          <v-col cols="12" sm="6" md="4">
            <v-select
              :items="getDistrictNames"
              v-model="editZoneInput.districtName"
              @click:append-outer="fetchDistrictNames"
              append-outer-icon="mdi-refresh"
              label="지역 선택"
              solo
            >
            </v-select>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" sm="6" md="4">
            <v-text-field
              v-model="editZoneInput.topLeftLatitude"
              label="좌상단 위도(y) 입력"
              :rules="rules.zone.latitude"
              hint="36.xxx"
              required
            />
          </v-col>
          <v-col cols="12" sm="6" md="4">
            <v-text-field
              v-model="editZoneInput.topLeftLongitude"
              label="좌상단 경도(x) 입력"
              :rules="rules.zone.longitude"
              hint="127.xxx"
              required
            />
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" sm="6" md="4">
            <v-text-field
              v-model="editZoneInput.bottomRightLatitude"
              label="우하단 위도(y) 입력"
              :rules="rules.zone.latitude"
              hint="36.xxx"
              required
            />
          </v-col>
          <v-col cols="12" sm="6" md="4">
            <v-text-field
              v-model="editZoneInput.bottomRightLongitude"
              label="우하단 경도(x) 입력"
              :rules="rules.zone.longitude"
              hint="127.xxx"
              required
            />
          </v-col>
        </v-row>
      </v-form>
    </DefaultModal>
    <CustomSnackbar />
  </v-container>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
import CustomSnackbar from "@/components/CustomSnackBar";
import DefaultModal from "@/components/DefaultModal";
import validator from "@/utils/validator";
import { convert } from "@/utils/responseConverter";
import { SNACK_BAR_TYPE } from "@/utils/constants";

export default {
  name: "ZoneManage",
  components: {
    DefaultModal,
    CustomSnackbar
  },
  created() {
    this.fetchDistrictNames();
  },
  data: () => {
    return {
      isValidForNew: true,
      isValidForEdit: true,
      rules: { ...validator },
      zoneInput: {
        districtName: "",
        topLeftLatitude: "",
        topLeftLongitude: "",
        bottomRightLatitude: "",
        bottomRightLongitude: ""
      },
      editZoneInput: {
        zoneId: "",
        districtName: "",
        topLeftLatitude: "",
        topLeftLongitude: "",
        bottomRightLatitude: "",
        bottomRightLongitude: ""
      },
      dialog: false,
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
    ...mapGetters("zone", ["getZones", "getDistrictNames"])
  },
  methods: {
    clearZoneInput: function(zone) {
      zone.districtName = "";
      zone.topLeftLongitude = "";
      zone.topLeftLatitude = "";
      zone.bottomRightLongitude = "";
      zone.bottomRightLatitude = "";
    },
    ...mapMutations("modal", ["ACTIVATE_MODAL", "DEACTIVATE_MODAL"]),
    ...mapMutations("snackbar", ["SHOW_SNACKBAR"]),
    ...mapMutations("zone", ["ADD_ZONE", "CLEAR_ZONES"]),
    ...mapActions("zone", [
      "setZone",
      "updateZone",
      "removeZones",
      "fetchZones",
      "fetchDistrictNames"
    ]),
    async findAllZone() {
      const response = await this.fetchZones();
      this.SHOW_SNACKBAR(convert.toSnackBarContent(response));
    },
    async addNewZone() {
      const response = await this.setZone(this.zoneInput);
      const snackbarContents = convert.toSnackBarContent(response);
      this.SHOW_SNACKBAR(snackbarContents);
      if (snackbarContents.type !== SNACK_BAR_TYPE.ERROR) {
        this.clearZoneInput(this.zoneInput);
      }
    },
    deleteSelectedZone() {
      this.dialog = false;
      this.removeZones(this.selected);
    },
    showEditModal(modalName, zone) {
      this.setEditZone(zone);
      this.ACTIVATE_MODAL(modalName);
    },
    setEditZone(zone) {
      this.editZoneInput.zoneId = zone.zoneId;
      this.editZoneInput.districtName = zone.districtName;
      this.editZoneInput.topLeftLatitude = zone.topLeftLatitude;
      this.editZoneInput.topLeftLongitude = zone.topLeftLongitude;
      this.editZoneInput.bottomRightLatitude = zone.bottomRightLatitude;
      this.editZoneInput.bottomRightLongitude = zone.bottomRightLongitude;
    },
    async editZone() {
      const response = await this.updateZone(this.editZoneInput);
      const snackbarContents = convert.toSnackBarContent(response);
      this.SHOW_SNACKBAR(snackbarContents);
      this.clearZoneInput(this.editZoneInput);
      this.editZoneInput.zoneId = "";
    }
  }
};
</script>

<style scoped></style>
