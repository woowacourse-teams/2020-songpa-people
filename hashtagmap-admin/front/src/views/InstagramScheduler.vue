<template>
  <v-container>
    <v-btn
      id="instagram-update-btn"
      class="ma-2"
      color="indigo"
      outlined
      @click="instagramScheduling"
      :disabled="updateBtn.disabled"
      >{{ updateBtn.text }}
    </v-btn>

    <v-snackbar v-model="snackbar" timeout="99999999" right>
      {{ snackbarText }}

      <template v-slot:action="{ attrs }">
        <v-btn color="blue" text v-bind="attrs" @click="snackbar = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import { MESSAGE, UPDATE_BUTTON_STATE } from "../utils/constants";

export default {
  name: "InstagramScheduler",
  data() {
    return {
      updateBtn: {
        disabled: false,
        text: "인스타그램 update 실행"
      },
      snackbar: false,
      snackbarText: ""
    };
  },
  methods: {
    instagramScheduling() {
      this.popUpSnackBar(MESSAGE.UPDATE_START);
      this.setUpdateBtnRunning();

      this.axios
        .put("/instagram-scheduler")
        .then(() => {
          this.popUpSnackBar(MESSAGE.SUCCESS);
          this.setUpdateBtnInit();
        })
        .catch(error => {
          this.popUpSnackBar(error.message);
          this.setUpdateBtnInit();
        });
    },
    setUpdateBtnRunning() {
      this.updateBtn.text = UPDATE_BUTTON_STATE.RUNNING;
      this.updateBtn.disabled = true;
    },
    popUpSnackBar(text) {
      this.snackbarText = text;
      this.snackbar = true;
    },
    setUpdateBtnInit() {
      this.updateBtn.text = UPDATE_BUTTON_STATE.STAND_BY;
      this.updateBtn.disabled = false;
    }
  }
};
</script>
