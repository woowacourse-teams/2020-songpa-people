<template>
  <v-container>
    <v-row>
      <v-col>
        <v-subheader>지역 데이터 목록</v-subheader>
      </v-col>
      <v-col class="text-right">
        <v-btn class="ma-2" color="indigo" outlined>
          지역 데이터 목록 가져오기
        </v-btn>
      </v-col>
    </v-row>
    <v-data-table
      :headers="headers"
      :items="items"
      :items-per-page="5"
      class="elevation-1"
    />
  </v-container>
</template>

<script>
export default {
  name: "DistrictManage",
  data: () => {
    return {
      headers: [
        { text: "ID", value: "districtId", align: "start", sortable: false },
        { text: "지역 이름", value: "districtName", sortable: true },
        { text: "생성 날짜", value: "createdDate" },
        { text: "변경된 날짜 및 시간", value: "changedDate" },
        { text: "변경한 사람", value: "member" }
      ],
      items: [
          {}
      ]
    };
  }
};
</script>

<style scoped></style>
