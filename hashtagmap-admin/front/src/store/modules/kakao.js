import { KAKAO } from "@/utils/constants.js";
import kakaoApi from "../../request/api/kakao";

export default {
  namespaced: true,

  state: {
    kakaoScheduleActiveStatus: {
      message: KAKAO.SCHEDULE.UNKNOWN_MESSAGE,
      color: KAKAO.SCHEDULE.UNKNOWN_COLOR
    },
    kakaoSchedulerAutoRunnableStatus: {
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
    },
    getSchedulerAutoRunnableStatus: state => {
      return state.kakaoSchedulerAutoRunnableStatus;
    },
    isScheduleRunning: state => {
      return (
        state.kakaoScheduleActiveStatus.message ===
        KAKAO.SCHEDULE.ACTIVATE_MESSAGE
      );
    },
    isScheduleAutoRunnable: state => {
      return (
        state.kakaoSchedulerAutoRunnableStatus.message ===
        KAKAO.SCHEDULE.ACTIVATE_MESSAGE
      );
    }
  },

  mutations: {
    CHANGE_KAKAO_SCHEDULE_ACTIVE_STATUS: (state, { message, color }) => {
      state.kakaoScheduleActiveStatus.message = message;
      state.kakaoScheduleActiveStatus.color = color;
    },
    CHANGE_KAKAO_SCHEDULE_AUTO_RUNNABLE_STATUS: (state, { message, color }) => {
      state.kakaoSchedulerAutoRunnableStatus.message = message;
      state.kakaoSchedulerAutoRunnableStatus.color = color;
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
    startKakaoScheduler: async ({ dispatch }) => {
      try {
        return await kakaoApi.startScheduler("KAKAO");
      } catch (error) {
        return error;
      } finally {
        await dispatch("fetchScheduleActiveStatus");
      }
    },
    stopKakaoScheduler: async ({ dispatch }) => {
      try {
        return await kakaoApi.stopScheduler("KAKAO");
      } catch (error) {
        return error;
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
        const response = await kakaoApi.getSchedulerStatus();
        const active = response.body.data;
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
    toggleKakaoSchedulerAutoRunnable: async ({ dispatch }) => {
      try {
        await kakaoApi.toggleSchedulerAutoRunnable(KAKAO.SCHEDULE.NAME);
      } finally {
        await dispatch("fetchScheduleAutoRunnable");
      }
    },
    fetchScheduleAutoRunnable: async ({ commit }) => {
      const status = {
        message: KAKAO.SCHEDULE.UNKNOWN_MESSAGE,
        color: KAKAO.SCHEDULE.UNKNOWN_COLOR
      };

      try {
        const response = await kakaoApi.getSchedulerAutoRunnableStatus(
          KAKAO.SCHEDULE.NAME
        );
        const active = response.body.data;
        status.message = active
          ? KAKAO.SCHEDULE.ACTIVATE_MESSAGE
          : KAKAO.SCHEDULE.DEACTIVATE_MESSAGE;
        status.color = active
          ? KAKAO.SCHEDULE.ACTIVATE_COLOR
          : KAKAO.SCHEDULE.DEACTIVATE_COLOR;
      } finally {
        commit("CHANGE_KAKAO_SCHEDULE_AUTO_RUNNABLE_STATUS", status);
      }
    },
    setPeriod: async ({ commit, state }) => {
      const expression = state.expression;
      try {
        const response = await kakaoApi.changePeriodPeriod(expression);
        commit("CLEAR_EXPRESSION");
        return response;
      } catch (error) {
        return error;
      }
    },
    findPeriodHistory: async ({ commit }) => {
      try {
        const response = await kakaoApi.getPeriodHistory();
        commit("CLEAR_PERIOD_HISTORY");
        response.body.data.map(period =>
          commit("ADD_PERIOD_HISTORY", {
            changedDate: period.changedDate,
            expression: period.expression,
            member: period.member
          })
        );
        return response;
      } catch (error) {
        return error;
      }
    }
  }
};
