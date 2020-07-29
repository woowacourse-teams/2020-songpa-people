export const markerInfoTemplate = place =>
  `<div class="btn btn-default info-box" 
    style="position: relative;
           bottom: 77px;
           border-radius: 30px / 30px;
           box-shadow: 1px 1px 1px 1px darkgrey">` +
  `  <div class="text-primary" style="font-weight: bold">` +
  `    <div style="width: auto;right: 90%" >#${place.title}` +
  `    </div>` +
  `    <div>해쉬태그 수 : ${place.hashtag_count}</div>` +
  `  </div>` +
  `</div>`;
