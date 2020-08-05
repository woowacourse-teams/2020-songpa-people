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
        text: UPDATE_BUTTON_STATE.STAND_BY
      },
      snackbar: false,
      snackbarText: ""
    };
  },
  methods: {
    async instagramScheduling() {
      this.popUpSnackBar(MESSAGE.UPDATE_START);
      this.setUpdateBtnRunning();

      try {
        await this.axios.put('/instagram-scheduler')
        this.popUpSnackBar(MESSAGE.SUCCESS)
      } catch (error) {
        this.popUpSnackBar(error.message)
      } finally {
        this.setUpdateBtnInit()
      }
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
