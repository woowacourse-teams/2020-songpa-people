export const textBalloonTemplate = place => {
  let $textBalloonElement = createTextBalloonElement(place);
  let $container = createDivElement();
  const $markerTitleElement = createMarkerTitleElement(place);
  const $hashTagCountElement = createHashtagCountElement(place);
  $textBalloonElement.appendChild($container);
  $container.appendChild($markerTitleElement);
  $container.appendChild($hashTagCountElement);
  return $textBalloonElement;
};
const createTextBalloonElement = place => {
  let $textBalloonContent = createDivElement;
  $textBalloonContent.id = place.kakaoId;
  $textBalloonContent.className = "btn text-balloon-box";
  return $textBalloonContent;
};

const createMarkerTitleElement = place => {
  let $markerTitle = createDivElement;
  $markerTitle.className = "text-balloon-title";
  $markerTitle.textContent = place.placeName;
  return $markerTitle;
};

const createHashtagCountElement = place => {
    let $hashtagCount = createDivElement;
    $hashtagCount.className = "text-balloon-text";
    $hashtagCount.textContent = place.hashtagCount;
    return $hashtagCount;
};

const createDivElement = () => {
  return document.createElement("div");
};
