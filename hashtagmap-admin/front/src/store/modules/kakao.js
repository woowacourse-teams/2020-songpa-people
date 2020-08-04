import customAxios from "@/request";

export default {
  namespaced: true,
  state: {
    kakaoScheduleActiveStatus: {
      message: "알 수 없음",
      color: "indigo"
    }
  },
  mutations: {
    GET_KAKAO_SCHEDULE_ACTIVE_STATUS(state, {message, color}) {
      state.kakaoScheduleActiveStatus.message = message;
      state.kakaoScheduleActiveStatus.color = color;
    }
  },
  actions: {
    toggleKakaoSchedule({commit}) {
      commit("SELECT_CATEGORY", "KAKAO");
    },
    async getKakaoScheduleActiveStatus({commit}) {
      const status = {
        message: "갱신 실패",
        color: "indigo"
      };

      try {
        const response = await customAxios().get("/kakao/scheduler/status", {
          params: {
            target: "KAKAO"
          }
        });
        const active = response.body.data;
        status.message = active ? "실행중" : "중지";
        status.color = active ? "primary" : "error";
      } finally {
        commit("GET_KAKAO_SCHEDULE_ACTIVE_STATUS", status);
      }
    }
  }
};
