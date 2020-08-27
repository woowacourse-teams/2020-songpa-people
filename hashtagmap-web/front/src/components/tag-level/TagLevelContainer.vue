<template>
  <div id="tag-level">
    <v-container class="container-design">
      <PlaceCategories />
      <PlaceSearchInput/>
      <TagLevelSelector />
      <TagLevelDetail />
    </v-container>
  </div>
</template>

<script>
import TagLevelSelector from "@/components/tag-level/TagLevelSelector";
import TagLevelDetail from "@/components/tag-level//TagLevelDetail";
import PlaceCategories from "@/components/place/PlaceCategories";
import PlaceSearchInput from "@/components/place/PlaceSearchInput";

export default {
  components: {
    PlaceCategories,
    PlaceSearchInput,
    TagLevelSelector,
    TagLevelDetail,
  },
};
</script>

<style scoped>
@media all and (min-width: 768px) {
  #tag-level {
    z-index: 10;
    position: fixed;
    top: 0px;
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
