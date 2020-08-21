export const MESSAGE = {
  NO_INPUT: "입력값이 없습니다.",
  SUCCESS: "성공😊",
  FAIL: "실패😨",
  NO_CONTENT: "데이터가 없습니다",
  UPDATE_START: "update를 시작합니다"
};

export const UPDATE_BUTTON_STATE = {
  STAND_BY: "update 실행",
  RUNNING: "update 실행중"
};

export const KAKAO = {
  SCHEDULE: {
    NAME: "KAKAO",
    ACTIVATE_COLOR: "primary",
    ACTIVATE_MESSAGE: "ON",
    DEACTIVATE_COLOR: "error",
    DEACTIVATE_MESSAGE: "OFF",
    UNKNOWN_COLOR: "indigo",
    UNKNOWN_MESSAGE: "알 수 없음"
  }
};

export const EVENT_TYPE = {
  CLICK: "click"
};

export const SNACK_BAR_TYPE = {
  SUCCESS: "success",
  ERROR: "error",
  INFO: "info"
};

export const SNACK_BAR_TEMPLATE = {
  CUSTOM: (type, message, code) => {
    return {
      type: "",
      message: message,
      code: code
    };
  },
  INFO: message => {
    return {
      type: SNACK_BAR_TYPE.INFO,
      message: message,
      code: ""
    };
  },
  SUCCESS: () => {
    return {
      type: SNACK_BAR_TYPE.SUCCESS,
      message: MESSAGE.SUCCESS,
      code: ""
    };
  },
  ERROR: error => {
    return {
      type: SNACK_BAR_TYPE.ERROR,
      message: error.body.message,
      code: error.body.code
    };
  }
};

export const WEB_PAGE = {
  KAKAO: "https://map.kakao.com/",
  INSTAGRAM: "https://www.instagram.com/"
};

export const CONST = {
  ADMIN_LOGIN_KEY: "hashtagmapAdminLogin"
};
