import mapMarker1 from "../assets/markers/mapMarker1.png";
import mapMarker2 from "../assets/markers/mapMarker2.png";
import mapMarker3 from "../assets/markers/mapMarker3.png";
import mapMarker4 from "../assets/markers/mapMarker4.png";
import mapMarker5 from "../assets/markers/mapMarker5.png";

const markerImages = new Map();
markerImages.set(1, mapMarker1);
markerImages.set(2, mapMarker2);
markerImages.set(3, mapMarker3);
markerImages.set(4, mapMarker4);
markerImages.set(5, mapMarker5);

export const getMarkerImage = tagLevel => {
  return markerImages.get(tagLevel);
};

export const SIZE = {
  width: 50,
  height: 50,
};
