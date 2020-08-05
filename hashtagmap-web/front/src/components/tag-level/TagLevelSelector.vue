<template>
  <v-container>
    <v-chip-group active-class="primary" column multiple>
      <v-row class="ma-0" align="start" justify="space-around">
        <v-chip class="ma-0 chip-size" label color="rgb(216,160,250)"></v-chip>
        <v-chip class="ma-0 chip-size" label color="rgb(185,112,243)"></v-chip>
        <v-chip class="ma-0 chip-size" label color="rgb(158,76,237)"></v-chip>
        <v-chip class="ma-0 chip-size" label color="rgb(116,22,227)"></v-chip>
        <v-chip class="ma-0 chip-size" label color="rgb(65,12,162)"></v-chip>
      </v-row>
    </v-chip-group>
  </v-container>
</template>

<script>
export default {
  name: "TagLevelSelector",
};
</script>

<style scoped>
.container {
  padding: 0 !important;
}

.chip-size {
  width: 35px !important;
}
</style>
