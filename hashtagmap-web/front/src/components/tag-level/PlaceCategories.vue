<template>
  <v-container>
    <v-chip-group active-class="primary" column>
      <v-row align="start" justify="space-around">
        <v-chip :key="tag" label v-for="tag in tags" color="white">
          {{ tag }}
        </v-chip>
      </v-row>
    </v-chip-group>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    tags: ["카페", "식당"],
  }),
};
</script>

<style lang="stylus" scoped>
.container {
  padding: 0;
}

.v-slide-group__content {
  padding: 0 !important;
}

.v-chip-group .v-chip {
  margin: 0 !important;
}

.v-chip--label {
  border: 2px solid;
}

.theme--light.v-chip {
  border-color: black !important;
}

.v-application .primary {
  background-color: darkgray !important;
}
</style>
