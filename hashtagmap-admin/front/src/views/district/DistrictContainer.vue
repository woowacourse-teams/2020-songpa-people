<template>
  <div>
    <DistrictManage/>
    <ZoneManage/>
  </div>
</template>

<script>
  import ZoneManage from "@/views/district/ZoneManage";
  import DistrictManage from "@/views/district/DistrictManage";

  export default {
    name: "DistrictContainer",
    components: {DistrictManage, ZoneManage},
    component: {
      DistrictManage,
      ZoneManage
    }
  };
</script>

<style scoped></style>
