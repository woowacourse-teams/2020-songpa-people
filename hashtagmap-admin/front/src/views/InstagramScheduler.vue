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

    <v-snackbar v-model="snackbar" right>
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
      this.popUpSnackBar("update를 실행합니다.");
      this.setUpdateBtnLoading();

      this.axios
        .put("/instagram-scheduler")
        .then(() => {
          console.log("s");
          this.popUpSnackBar("성공😊");
          this.setUpdateBtnInit();
        })
        .catch(() => {
          console.log("e");
          this.popUpSnackBar("실패😨");
          this.setUpdateBtnInit();
        });
    },
    setUpdateBtnLoading() {
      this.updateBtn.text = "인스타그램 post update 실행중";
      this.updateBtn.disabled = true;
    },
    popUpSnackBar(text) {
      this.snackbarText = text;
      this.snackbar = true;
    },
    setUpdateBtnInit() {
      this.updateBtn.text = "인스타그램 update 실행";
      this.updateBtn.disabled = false;
    }
  }
};
</script>
