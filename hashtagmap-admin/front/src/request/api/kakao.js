import { customWrapAxios } from "@/request";

const kakaoApi = {
  startScheduler() {
    return customWrapAxios().post("/kakao/scheduler/start");
  },
  stopScheduler() {
    return customWrapAxios().post("/kakao/scheduler/stop");
  },
  toggleSchedulerAutoRunnable(scheduleName) {
    const toggleDto = { name: scheduleName };
    return customWrapAxios().patch("/kakao/scheduler/auto/toggle", toggleDto);
  },
  getSchedulerAutoRunnableStatus(scheduleName) {
    return customWrapAxios().get("/kakao/scheduler/auto", {
      params: {
        name: scheduleName
      }
    });
  },
  changePeriodPeriod(expression) {
    return customWrapAxios().put("/kakao/scheduler/period", expression, {
      headers: {
        "Content-Type": "application/json"
      }
    });
  },
  getPeriodHistory() {
    return customWrapAxios().get("/kakao/scheduler/period");
  },
  getSchedulerStatus() {
    return customWrapAxios().get("/kakao/scheduler");
  }
};

export default kakaoApi;
