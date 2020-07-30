export const textBallonTemplate = place =>
  `<div class="btn btn-default text-balloon" 
    style="position: relative;
           bottom: 77px;
           border-radius: 30px / 30px;
           box-shadow: 1px 1px 1px 1px darkgrey">` +
  `  <div>` +
  `    <div style="width: auto;right: 90%" >${place.title}` +
  `    </div>` +
  `    <div>#${place.hashtag_count}</div>` +
  `  </div>` +
  `</div>`;
