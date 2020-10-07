package com.songpapeople.hashtagmap.config;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.instagram.domain.model.InstagramPost;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.instagram.domain.repository.instagramPost.InstagramPostRepository;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Generated
@Profile("data")
@RequiredArgsConstructor
@Configuration
public class WebDataConfiguration implements ApplicationRunner {
    private final PlaceRepository placeRepository;
    private final InstagramRepository instagramRepository;
    private final InstagramPostRepository instagramPostRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Place chosun = Place.builder()
                .kakaoId("11153410")
                .category(Category.RESTAURANT)
                .location(new Location(new Point("37.4862834222378", "127.099360450322"), "서울 강남구 광평로 246"))
                .placeName("조선면옥")
                .placeUrl("http://place.map.kakao.com/11153410")
                .build();
        Place bunong = Place.builder()
                .kakaoId("16047151")
                .category(Category.RESTAURANT)
                .location(new Location(new Point("37.5004068277696", "127.091394994148"), "서울 송파구 백제고분로28길 29"))
                .placeName("부농정육식당")
                .placeUrl("http://place.map.kakao.com/16047151")
                .build();
        List<Place> places = Arrays.asList(chosun, bunong);
        placeRepository.saveAll(places);

        Instagram chosunInsta = Instagram.builder()
                .hashtagCount(1417L)
                .hashtagName("조선면옥")
                .place(chosun)
                .build();
        Instagram bunongInsta = Instagram.builder()
                .hashtagCount(833L)
                .hashtagName("부농정육식당")
                .place(bunong)
                .build();
        List<Instagram> instagrams = Arrays.asList(chosunInsta, bunongInsta);
        instagramRepository.saveAll(instagrams);

        List<InstagramPost> instagramPosts = Arrays.asList(
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/99083033_237791104182174_2036988678828563666_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=106&_nc_ohc=Cmtr4wKuRngAX8m1K2D&oh=a32e97cbf0d3ba2b43c84aa9167b83b6&oe=5F520150")
                        .postUrl("https://www.instagram.com/p/CAj3iXUFKU9")
                        .instagram(chosunInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/103837279_566837130865367_6997603446644439186_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=111&_nc_ohc=T-jduZOOBcoAX-mnAXF&oh=d8c8040277812a58adc303a20cbc2268&oe=5F54F25A")
                        .postUrl("https://www.instagram.com/p/CBcnf8BlH-b")
                        .instagram(chosunInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://static.cdninstagram.com/rsrc.php/null.jpg")
                        .postUrl("https://www.instagram.com/p/CBXP4N3JTQrYDmxVjsGGQLnvJOy90fo2FqqrwY0")
                        .instagram(chosunInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/p1080x1080/101655963_561119384546893_5409389833462911970_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=101&_nc_ohc=5kl18-jKQK4AX8O0_rv&oh=e9e7d5adb449eab1b43cbb8d3ac5b979&oe=5F53D0CB")
                        .postUrl("https://www.instagram.com/p/CA21QKdnL70")
                        .instagram(chosunInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/75300776_2530161483732198_1010947955058895594_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=106&_nc_ohc=R0GXHgda-xYAX-9IDxm&oh=fe82239d452889bbfbd9bb800fb190fd&oe=5F553F6A")
                        .postUrl("https://www.instagram.com/p/B5RW8d4hhvr")
                        .instagram(chosunInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/67442721_2106195082819996_883699845474376857_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=104&_nc_ohc=aYpYdEwqbLcAX_53g7x&oh=42fa32e9540a288de763f6ffd4a7d56a&oe=5F53E4AC")
                        .postUrl("https://www.instagram.com/p/B2bhCXnhW_l")
                        .instagram(chosunInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/58057868_124491852072281_8160483128670694031_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=105&_nc_ohc=62nYkoh4PLwAX_SMDCo&oh=d8200b06686bd6fb76f2d6e2c139ef3d&oe=5F545CBB")
                        .postUrl("https://www.instagram.com/p/BxNEzuqAZzX")
                        .instagram(chosunInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/66843458_354023898874278_3783916168675392360_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=106&_nc_ohc=rs21ZbncjS8AX8NWNP2&oh=55b4121590c1eb9e48c9b2826ceb2bf9&oe=5F5242E7")
                        .postUrl("https://www.instagram.com/p/B1VZSyXBO_a")
                        .instagram(chosunInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/108215260_136574031410559_6059577777927002882_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=103&_nc_ohc=iZqrpwJ8utIAX863pnq&oh=3f5defffe7e4ad8e8d2f417cf7f90a7f&oe=5F552AE1")
                        .postUrl("https://www.instagram.com/p/CCirindjcsX")
                        .instagram(chosunInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/110732548_291065345474119_4360412485848590531_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=107&_nc_ohc=Ew6LKWryXDcAX-VU7Ua&oh=95349d0be1d6bf011d454a2e2e035d4a&oe=5F52B082")
                        .postUrl("https://www.instagram.com/p/CC06ypHHmbg")
                        .instagram(bunongInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/106914226_3431809586852420_1822193869298175084_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=108&_nc_ohc=2R2vQgxx-fAAX8n7uCD&oh=1d77302d05625955c762bd44792e6ac8&oe=5F5565F8")
                        .postUrl("https://www.instagram.com/p/CCXPVMphQg6")
                        .instagram(bunongInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/67331518_1834417783371545_392030638445414096_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=105&_nc_ohc=ka3M_66c9pAAX8vVkyI&oh=ae3a0199b7a897fc822ab5323ea58409&oe=5F527D90")
                        .postUrl("https://www.instagram.com/p/B1Z1VDbhbZg")
                        .instagram(bunongInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/19761977_164161360793903_5685001747311886336_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=111&_nc_ohc=YruqkL7Hu4QAX_P-Ye-&oh=f6711d2952fcacd9b3d8a8a8e90ee4f3&oe=5F53DD30")
                        .postUrl("https://www.instagram.com/p/BWPtYt3FxMS")
                        .instagram(bunongInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/106368175_616259819272598_916476563808432840_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=102&_nc_ohc=y1Q5sp3X4LMAX_Gheq6&oh=5d83affab9a12538bd14b5f621ddb11f&oe=5F548D47")
                        .postUrl("https://www.instagram.com/p/CCGrUjenzbr")
                        .instagram(bunongInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/103111198_2986778138057993_1535905161758517674_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=105&_nc_ohc=DJ4yBsKQFN8AX-49UWw&oh=cb273ab14a9638cd241ab340a0978971&oe=5F52B7D5")
                        .postUrl("https://www.instagram.com/p/CBg8tONB-co")
                        .instagram(bunongInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/102379068_149754953280017_2574672376004380378_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=105&_nc_ohc=3yhXHff-lZQAX_yXTPq&oh=b5de5079f7192c31250971c6c69d9b8c&oe=5F54FA5E")
                        .postUrl("https://www.instagram.com/p/CBEopKPBSop")
                        .instagram(bunongInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/74577646_140734120568965_3052315820959524898_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=101&_nc_ohc=Z1Vo1j-Y35IAX-bkLY-&oh=3d9ea5f2c001b525663ec381fd294188&oe=5F53C3ED")
                        .postUrl("https://www.instagram.com/p/B4aE19pBfQV")
                        .instagram(bunongInsta)
                        .build(),
                InstagramPost.builder()
                        .imageUrl("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/67351109_153167062463701_605920826508755255_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=101&_nc_ohc=fejDPs-PmvYAX-yzE7Z&oh=962a9f63b36bf33fc62e781b5d158cfa&oe=5F53711F")
                        .postUrl("https://www.instagram.com/p/B1vziP6lkwV")
                        .instagram(bunongInsta)
                        .build()
        );
        instagramPostRepository.saveAll(instagramPosts);
    }
}
