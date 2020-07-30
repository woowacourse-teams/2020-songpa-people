<template>
  <v-row justify="center">
    <v-dialog v-model="isShowDetailModal" max-width="800">
      <v-card>
        <v-card-title class="headline"
          >Use Google's location service?</v-card-title
        >

        <v-card-text>
          Let Google help apps determine location. This means sending anonymous
          location data to Google, even when no apps are running.
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapState(["isShowDetailModal"]),
  },
};
</script>
