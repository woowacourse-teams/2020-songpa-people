export const textBalloonTemplate = place => {
  let $textBalloonElement = document.createElement("div");
  $textBalloonElement.id = place.kakaoId;
  $textBalloonElement.className = "btn text-balloon-box";

  let $container = document.createElement("div");

  let $textBalloonTitle = document.createElement("div");
  $textBalloonTitle.className = "text-balloon-title";
  $textBalloonTitle.textContent = place.placeName;

  let $hashtagCount = document.createElement("div");
  $hashtagCount.className = "text-balloon-text";
  $hashtagCount.textContent = "#" + place.hashtagCount;

  $textBalloonElement.appendChild($container);
  $container.appendChild($textBalloonTitle);
  $container.appendChild($hashtagCount);
  return $textBalloonElement;
};
