import customAxios from "@/request";
import { KAKAO, MESSAGE, SNACK_BAR_TYPE } from "@/utils/constants.js";

export default {
  namespaced: true,
  state: {
    kakaoScheduleActiveStatus: {
      message: KAKAO.SCHEDULE.UNKNOWN_MESSAGE,
      color: KAKAO.SCHEDULE.UNKNOWN_COLOR
    },
    expression: "",
    periodHistory: []
  },
  getters: {
    getExpression: state => {
      return state.expression;
    },
    getPeriodHistory: state => {
      return state.periodHistory;
    },
    getScheduleActiveStatus: state => {
      return state.kakaoScheduleActiveStatus;
    }
  },
  mutations: {
    CHANGE_KAKAO_SCHEDULE_ACTIVE_STATUS: (state, { message, color }) => {
      state.kakaoScheduleActiveStatus.message = message;
      state.kakaoScheduleActiveStatus.color = color;
    },
    INPUT_EXPRESSION: (state, newExpression) => {
      state.expression = newExpression;
    },
    CLEAR_EXPRESSION: state => {
      state.expression = "";
    },
    ADD_PERIOD_HISTORY: (state, history) => {
      state.periodHistory.push(history);
    },
    CLEAR_PERIOD_HISTORY: state => {
      state.periodHistory = [];
    }
  },
  actions: {
    toggleKakaoSchedule: async ({ dispatch }) => {
      try {
        const toggleDto = {
          name: "KAKAO"
        };

        await customAxios().post("/kakao/scheduler/toggle", toggleDto, {
          headers: {
            "Content-Type": "application/json"
          }
        });
      } finally {
        await dispatch("fetchScheduleActiveStatus");
      }
    },
    fetchScheduleActiveStatus: async ({ commit }) => {
      const status = {
        message: KAKAO.SCHEDULE.UNKNOWN_MESSAGE,
        color: KAKAO.SCHEDULE.UNKNOWN_COLOR
      };

      try {
        const response = await customAxios().get("/kakao/scheduler/status", {
          params: {
            name: KAKAO.SCHEDULE.NAME
          }
        });
        const active = response.data.data;
        status.message = active
          ? KAKAO.SCHEDULE.ACTIVATE_MESSAGE
          : KAKAO.SCHEDULE.DEACTIVATE_MESSAGE;
        status.color = active
          ? KAKAO.SCHEDULE.ACTIVATE_COLOR
          : KAKAO.SCHEDULE.DEACTIVATE_COLOR;
      } finally {
        commit("CHANGE_KAKAO_SCHEDULE_ACTIVE_STATUS", status);
      }
    },
    setPeriod: async ({ commit, state }) => {
      const snackbarContents = {
        type: SNACK_BAR_TYPE.SUCCESS,
        message: MESSAGE.SUCCESS,
        code: ""
      };
      const expression = state.expression;
      if (!expression || expression.trim() === "") {
        snackbarContents.type = SNACK_BAR_TYPE.INFO;
        snackbarContents.message = MESSAGE.NO_INPUT;
        return snackbarContents;
      }

      try {
        await customAxios().put("/kakao/scheduler/period", expression, {
          headers: {
            "Content-Type": "application/json"
          }
        });
      } catch (error) {
        snackbarContents.type = SNACK_BAR_TYPE.ERROR;
        snackbarContents.message = error.response.data.message;
        snackbarContents.code = error.response.data.code;
      }
      commit("CLEAR_EXPRESSION");
      return snackbarContents;
    },
    findPeriodHistory: async ({ commit }) => {
      const snackbarContents = {
        type: SNACK_BAR_TYPE.INFO,
        message: MESSAGE.NO_CONTENT,
        code: ""
      };
      try {
        commit("CLEAR_PERIOD_HISTORY");
        const response = await customAxios().get("/kakao/scheduler/period");
        if (response.data.data.length === 0) {
          return snackbarContents;
        }
        response.data.data.map(period =>
          commit("ADD_PERIOD_HISTORY", {
            changedDate: period.changedDate,
            expression: period.expression,
            member: period.member
          })
        );
        snackbarContents.type = SNACK_BAR_TYPE.SUCCESS;
        snackbarContents.message = MESSAGE.SUCCESS;
      } catch (error) {
        snackbarContents.type = SNACK_BAR_TYPE.ERROR;
        snackbarContents.message = error.response.data.message;
        snackbarContents.code = error.response.data.code;
      }
      return snackbarContents;
    }
  }
};
