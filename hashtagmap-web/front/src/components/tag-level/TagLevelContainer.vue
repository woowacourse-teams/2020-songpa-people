<template>
  <div id="tag-level">
    <v-container class="container-design">
      <v-layout>
        <v-flex>
          <PlaceSearchContainer />
          <TagLevelSelector />
          <TagLevelDetail />
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import PlaceSearchContainer from "@/components/place/PlaceSearchContainer";
import TagLevelSelector from "./TagLevelSelector";
import TagLevelDetail from "./TagLevelDetail";

export default {
  components: {
    PlaceSearchContainer,
    TagLevelSelector,
    TagLevelDetail,
  },
};
</script>

<style>
@media all and (min-width: 768px) {
  #tag-level {
    z-index: 10;
    position: fixed;
    top: 10px;
    left: 30px;
  }
}

@media all and (max-width: 767px) {
  #tag-level {
    z-index: 10;
    position: fixed;
    top: 0px;
    left: 0px;
  }
}

.container-design {
  background-color: white;
  border-radius: 4px;
  box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2),
    0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12);
}
</style>
