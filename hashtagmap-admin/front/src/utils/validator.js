const validator = {
  district: {
    name: [
      v => !!v || "자치구 이름이 필요합니다.",
      v => v.trim() !== "" || "공백은 입력할 수 없습니다."
    ]
  },
  zone: {
    latitude: [
      v =>
        (Number(v) >= 33 && Number(v) <= 44) ||
        "올바른 위도 범위가 아닙니다. 33 ~ 43.x"
    ],
    longitude: [
      v =>
        (Number(v) >= 124 && Number(v) <= 133) ||
        "올바른 경도 범위가 아닙니다. 124 ~ 132.x"
    ]
  }
};

export default validator;
