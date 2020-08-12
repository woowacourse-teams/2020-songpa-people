<template>
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
      :headers="headers"
      :items="getDistricts"
      :items-per-page="5"
      class="elevation-1"
    />
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
    </v-row>
    <CustomSnackbar />
  </v-container>
</template>

<script>
import { mapMutations, mapActions, mapGetters } from "vuex";
import CustomSnackbar from "@/components/CustomSnackBar";

export default {
  name: "DistrictManage",
  components: {
    CustomSnackbar
  },
  data: () => {
    return {
      headers: [
        { text: "ID", value: "districtId", align: "start" },
        { text: "지역 이름", value: "districtName" },
        { text: "생성 날짜", value: "createdTime" },
        { text: "변경된 날짜 및 시간", value: "updatedTime" },
        { text: "변경한 사람", value: "memberName" }
      ]
    };
  },
  computed: {
    ...mapGetters("district", ["getDistricts", "getDistrictInput"])
  },
  methods: {
    ...mapActions("district", ["setDistrict", "fetchDistricts"]),
    ...mapMutations("snackbar", ["SHOW_SNACKBAR"]),
    ...mapMutations("district", ["INPUT_DISTRICT_TEXT"]),
    async addNewDistrict() {
      const snackbarContents = await this.setDistrict();
      this.SHOW_SNACKBAR(snackbarContents);
    },
    async findAllDistricts() {
      const snackbarContents = await this.fetchDistricts();
      this.SHOW_SNACKBAR(snackbarContents);
    }
  }
};
</script>

<style scoped></style>
