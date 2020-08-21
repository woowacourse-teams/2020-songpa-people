export const textBalloonTemplate = place => {
  let $textBalloonElement = document.createElement("div");
  $textBalloonElement.id = place.kakaoId;
  $textBalloonElement.className = "text-balloon-box";

  let $textBalloonTitle = document.createElement("div");
  $textBalloonTitle.className = "text-balloon-title";
  $textBalloonTitle.textContent = place.placeName;

  let $hashtagCount = document.createElement("div");
  $hashtagCount.className = "text-balloon-text";
  $hashtagCount.textContent = "#" + place.hashtagCount;

  let $close = document.createElement("div");
  $close.className = "close";

  $textBalloonElement.appendChild($close);
  $textBalloonElement.appendChild($textBalloonTitle);
  $textBalloonElement.appendChild($hashtagCount);
  return $textBalloonElement;
};
