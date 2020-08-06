import customAxios from "@/request";
import {KAKAO} from "@/utils/constants.js";

export default {
  namespaced: true,
  state: {
    kakaoScheduleActiveStatus: {
      message: KAKAO.SCHEDULE.UNKNOWN_MESSAGE,
      color: KAKAO.SCHEDULE.UNKNOWN_COLOR
    }
  },
  mutations: {
    GET_KAKAO_SCHEDULE_ACTIVE_STATUS(state, {message, color}) {
      state.kakaoScheduleActiveStatus.message = message;
      state.kakaoScheduleActiveStatus.color = color;
    }
  },
  actions: {
    async toggleKakaoSchedule({dispatch}) {
      try {
        const toggleDto = {
          name: "KAKAO"
        };

        await customAxios().post("/kakao/scheduler/toggle", toggleDto);
      } finally {
        await dispatch("getKakaoScheduleActiveStatus");
      }
    },
    async getKakaoScheduleActiveStatus({commit}) {
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
        const active = response.body.data;
        status.message = active
            ? KAKAO.SCHEDULE.ACTIVATE_MESSAGE
            : KAKAO.SCHEDULE.DEACTIVATE_MESSAGE;
        status.color = active
            ? KAKAO.SCHEDULE.ACTIVATE_COLOR
            : KAKAO.SCHEDULE.DEACTIVATE_COLOR;
      } finally {
        commit("GET_KAKAO_SCHEDULE_ACTIVE_STATUS", status);
      }
    }
  }
};
