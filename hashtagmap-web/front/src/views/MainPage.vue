<template>
  <v-content>
    <div class="instagram-container">
      <div class="profile">
        <div class="profile-image">
          <img src="../assets/mainpage/miso_circle.png" alt="" />
        </div>

        <div class="profile-user-settings">
          <h1 class="profile-user-name">offical_miso</h1>
          <router-link to="/map">
            <button class="btn profile-follow-btn">start</button>
          </router-link>
        </div>
        <div class="profile-stats">
          <ul>
            <li><span class="profile-stat-count">164</span> posts</li>
            <li><span class="profile-stat-count">188</span> followers</li>
            <li><span class="profile-stat-count">206</span> following</li>
          </ul>
        </div>

        <div class="profile-bio">
          <p>
            <span class="profile-real-name">offical_miso</span>
            <br />
            당신이 몰랐던 그 곳, 미소
            <br />
            인스타그램 핫플레이스를 만나보세요.
          </p>
        </div>
      </div>
      <Story></Story>
      <hr />
    </div>
    <Slider></Slider>
  </v-content>
</template>

<script>
import Story from "@/components/main-page/Story.vue";
import Slider from "@/components/main-page/Slider.vue";

export default {
  name: "App",
  components: {
    Slider,
    Story,
  },
};
</script>

<style>
@import "../assets/css/instagram.css";
</style>
