export const textBalloonTemplate = place =>
  `<div class="btn text-balloon marker-box" >` +
  `  <div>` +
  `    <div class="marker-title" >${place.title}` +
  `    </div>` +
  `    <div class="marker-text">#${place.hashtag_count}</div>` +
  `  </div>` +
  `</div>`;
