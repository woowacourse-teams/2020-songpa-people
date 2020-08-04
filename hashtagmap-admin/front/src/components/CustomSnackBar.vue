<template>
  <v-container>
    <v-snackbar
      v-model="snackbar.show"
      :bottom="y === 'top'"
      :color="snackbar.type"
      :timeout="snackbar.type === 'error' ? 10000 : 4000"
    >
      {{ snackbar.code ? [snackbar.code] : '' }} {{ snackbar.message }}
      <template v-slot:action="{ attrs }">
        <v-btn dark text v-bind="attrs" @click="snackbar = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<!-- Todo snack bar 겹칠 때 안보이는 문제 해결 -->
<script>
export default {
  name: "CustomSnackBar",
  computed: {
    snackbar() {
      return this.$store.state.snackbar;
    }
  }
};
</script>
