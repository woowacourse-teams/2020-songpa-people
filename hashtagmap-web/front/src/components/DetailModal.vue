<template>
  <v-row justify="center">
    <v-dialog
      :value="isShowDetailModal"
      @input="initCloseDetailModal"
      max-width="70%"
    >
      <v-card>
        <div>
          <p class="place-title">{{ nowPlaceByDetailModal.placeName }}</p>
          <p class="hashtag-count">{{ makeHashtagCount }}k</p>
          <p class="buttons">
            <v-btn class="instagram-page-button">
              {{ nowPlaceByDetailModal.placeName }}
            </v-btn>
            <v-btn class="detail-info">상세 정보</v-btn>
          </p>
          <div class="image-container">
            <a
              :href="image.postUrl"
              v-for="image in dummyImages"
              :key="image.id"
            >
              <img
                class="post-image"
                alt="인스타그램 인기 게시물 이미지"
                :src="image.imagePath"
              />
            </a>
          </div>
        </div>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapMutations, mapState } from "vuex";

export default {
  data() {
    return {
      dummyImages: [
        {
          id: 1,
          imagePath:
            "https://scontent-gmp1-1.cdninstagram.com/v/t51.2885-15/e35/p1080x1080/116586949_288690075566886_2591824595784220711_n.jpg?_nc_ht=scontent-gmp1-1.cdninstagram.com&_nc_cat=106&_nc_ohc=GCFheA6r1OAAX_uYd8y&oh=fedd26cc8fc8c17f6ad8487c04540e60&oe=5F517F0C",
          postUrl: "https://www.instagram.com/p/CDV9eMShoRR",
        },
        {
          id: 2,
          imagePath:
            "https://scontent-gmp1-1.cdninstagram.com/v/t51.2885-15/fr/e15/s1080x1080/116532920_734499834005497_6420876009432203001_n.jpg?_nc_ht=scontent-gmp1-1.cdninstagram.com&_nc_cat=1&_nc_ohc=bKhIrRkdfQ8AX8clL1x&oh=b82e656a0559864623770c4768cb57b0&oe=5F4F154C",
          postUrl: "https://www.instagram.com/p/CDVbpg5gKIN",
        },
        {
          id: 3,
          imagePath:
            "https://scontent-gmp1-1.cdninstagram.com/v/t51.2885-15/e35/116508309_3116008508488674_8175869108062201632_n.jpg?_nc_ht=scontent-gmp1-1.cdninstagram.com&_nc_cat=1&_nc_ohc=ykzVAIzZLTUAX95L6uA&oh=e0023e7b1f113c093866bbdaffefa29e&oe=5F51C8B3",
          postUrl: "https://www.instagram.com/p/CDZGkJ_pFts",
        },
        {
          id: 4,
          imagePath:
            "https://scontent-gmp1-1.cdninstagram.com/v/t51.2885-15/e35/116375189_746748629407304_102289454963169672_n.jpg?_nc_ht=scontent-gmp1-1.cdninstagram.com&_nc_cat=1&_nc_ohc=EC09D95vMIwAX9_3GqH&oh=a119ce1df8e516e4651801cdc544f73b&oe=5F29F183",
          postUrl: "https://www.instagram.com/p/CDQ8sYjpy36",
        },
        {
          id: 5,
          imagePath:
            "https://scontent-gmp1-1.cdninstagram.com/v/t51.2885-15/e35/116252913_622840731947761_6994056466750873473_n.jpg?_nc_ht=scontent-gmp1-1.cdninstagram.com&_nc_cat=1&_nc_ohc=MKfdLkADciwAX_xVfm0&oh=df4d8c3b9d6e2a2341e027a4e6d28a22&oe=5F2A17C4",
          postUrl: "https://www.instagram.com/p/CDWBmGvDxHU",
        },
        {
          id: 6,
          imagePath:
            "https://scontent-gmp1-1.cdninstagram.com/v/t51.2885-15/e15/116426837_217472916239448_6285945929357681886_n.jpg?_nc_ht=scontent-gmp1-1.cdninstagram.com&_nc_cat=1&_nc_ohc=P2-gEnm8S3MAX-zSCep&oh=7ff05b6ef536fdfc57cd33667ab39dc4&oe=5F522E09",
          postUrl: "https://www.instagram.com/p/CDYSK_4BLd6",
        },
        {
          id: 7,
          imagePath:
            "https://scontent-gmp1-1.cdninstagram.com/v/t51.2885-15/e35/116330459_715221762376305_5232927128958844571_n.jpg?_nc_ht=scontent-gmp1-1.cdninstagram.com&_nc_cat=1&_nc_ohc=Yo8nfPFLgDMAX_iUbHc&oh=2df0aa565de872c73aa93f6778d1a092&oe=5F513E52",
          postUrl: "https://www.instagram.com/p/CDX4ulvJdpN",
        },
        {
          id: 8,
          imagePath:
            "https://scontent-gmp1-1.cdninstagram.com/v/t51.2885-15/e35/116942721_902050866988927_5322253690406958939_n.jpg?_nc_ht=scontent-gmp1-1.cdninstagram.com&_nc_cat=107&_nc_ohc=1osViLtwlwsAX8oqVFW&oh=3c629e46da2229c7814dd232859d8010&oe=5F2A099C",
          postUrl: "https://www.instagram.com/p/CDU2oSMp34f",
        },
        {
          id: 9,
          imagePath:
            "https://scontent-gmp1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/115992996_291073775457534_3483287473590989714_n.jpg?_nc_ht=scontent-gmp1-1.cdninstagram.com&_nc_cat=106&_nc_ohc=g84clhwSl1sAX8XrwWG&oh=56a7a19d4ea7ed4dd206f30ae38a6be2&oe=5F529069",
          postUrl: "https://www.instagram.com/p/CDQUlq3JiT7",
        },
      ],
    };
  },
  computed: {
    ...mapState(["isShowDetailModal", "nowPlaceByDetailModal"]),
    makeHashtagCount() {
      return (this.nowPlaceByDetailModal.hashtagCount / 1000).toFixed(0);
    },
  },
  methods: {
    ...mapMutations(["initCloseDetailModal"]),
  },
};
</script>

<style scoped>
.place-title {
  font-size: 5vw;
}

.hashtag-count {
  margin-top: -3vw;
  font-size: 4vw;
}

.buttons {
  margin-top: -1.5vw;
}

.instagram-page-button,
.detail-info {
  height: 3.5vw;
  font-size: 1.5vw;
  margin-right: 0.7vw;
  border: fuchsia 1.5px solid;
  box-sizing: content-box;
}

.image-container {
  margin-top: 3vw;
}

.post-image {
  margin: 0.3vw;
  max-width: 30%;
  overflow: hidden;
  height: auto;
}
</style>
