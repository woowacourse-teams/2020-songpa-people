<template>
  <v-container>
    <v-snackbar
      v-model="getSnackbar.show"
      :color="getSnackbar.type"
      :timeout="getSnackbar.type === 'error' ? 10000 : 4000"
    >
      {{ getSnackbar.code ? [getSnackbar.code] : "" }} {{ getSnackbar.message }}
      <template v-slot:action="{ attrs }">
        <v-btn dark text v-bind="attrs" @click="getSnackbar.show = false">
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
    ...mapGetters("snackbar", ["getSnackbar"])
  },
  created() {}
};
</script>
