export const textBalloonTemplate = place =>
  `<div id="${place.id}" class="btn marker-box">` +
  `  <div>` +
  `    <div class="marker-title" >${place.title}` +
  `    </div>` +
  `    <div class="marker-text">#${place.hashtagCount}</div>` +
  `  </div>` +
  `</div>`;
