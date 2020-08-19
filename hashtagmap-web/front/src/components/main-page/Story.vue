<template>
  <v-content>
    <div class="story-container">
      <div class="story">
        <router-link to="/map">
          <div class="story-image">
            <img src="../../assets/mainpage/hot_place_ex1.jpg" />
          </div>
        </router-link>
        <div class="story-name">힙지로</div>
      </div>
      <div class="story">
        <router-link to="/map">
          <div class="story-image">
            <img src="../../assets/mainpage/hot_place_ex2.png" />
          </div>
        </router-link>
        <div class="story-name">연남동</div>
      </div>
      <div class="story">
        <router-link to="/map">
          <div class="story-image">
            <img src="../../assets/mainpage/hot_place_ex3.png" />
          </div>
        </router-link>
        <div class="story-name">송리단길</div>
      </div>
    </div>
  </v-content>
</template>
<script></script>
<style>
.story-container {
  display: flex;
}
.story {
  margin: 10px;
  padding: 20px;
}
.story-image {
  width: 100px;
  height: 100px;
  border-radius: 70%;
  overflow: hidden;
  border-style: solid;
  border-color: rgb(217, 217, 217);
}
.story-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.story-name {
  text-align: center;
  margin: 10px;
}
</style>
