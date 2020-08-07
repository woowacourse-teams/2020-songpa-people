<template>
  <v-container>
    <v-snackbar
      v-model="snackbar.show"
      :color="snackbar.type"
      :timeout="snackbar.type === 'error' ? 10000 : 4000"
    >
      {{ snackbar.code ? [snackbar.code] : "" }} {{ snackbar.message }}
      <template v-slot:action="{ attrs }">
        <v-btn dark text v-bind="attrs" @click="snackbar.show = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<!-- Todo snack bar 겹칠 때 안보이는 문제 해결 -->
<script>
import { mapGetters } from "vuex";

export default {
  name: "CustomSnackbar",
  computed: {
    ...mapGetters(["snackbar"])
  },
  created() {
    console.log(this.snackbar);
  }
};
</script>
