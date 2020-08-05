<template>
  <v-container>
    <v-chip-group active-class="primary" column multiple>
      <v-row align="start" justify="space-around">
        <v-chip :key="tag" label v-for="tag in tags">
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
</style>
