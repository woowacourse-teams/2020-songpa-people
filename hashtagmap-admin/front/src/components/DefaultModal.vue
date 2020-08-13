<template>
  <v-row justify="center">
    <v-dialog
      :value="isActive(modalName)"
      @input="DEACTIVATE_MODAL(modalName)"
      max-width="70%"
    >
      <v-card>
        <v-card-title>
          <span class="headline">{{ title }}</span>
        </v-card-title>

        <v-card-text>
          <v-container>
            <slot></slot>
          </v-container>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="DEACTIVATE_MODAL(modalName)"
            >Cancel
          </v-btn>
          <v-btn color="blue darken-1" text @click="ok"
            >{{ okEventText }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";

export default {
  name: "DefaultModal",
  props: {
    modalName: {
      type: String,
      required: true
    },
    title: {
      type: String,
      required: false
    },
    okEvent: {
      type: Function,
      required: true
    },
    okEventText: {
      type: String,
      required: true
    }
  },
  computed: {
    ...mapGetters("modal", ["isActive"])
  },
  methods: {
    ...mapMutations("modal", ["DEACTIVATE_MODAL"]),
    ok() {
      this.okEvent();
      this.DEACTIVATE_MODAL();
    }
  }
};
</script>

<style scoped></style>
