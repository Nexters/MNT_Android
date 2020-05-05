# Frutto

<img src="/app/src/main/res/drawable/app_icon.png" weight="200" height="200" align="center"/>

- - -

## UI

<table>
   <tr>
     <th align="center">
       <img width="200" alt="1" src="https://user-images.githubusercontent.com/11826495/79090162-b0494100-7d83-11ea-8796-89e51cfe932d.gif"/>
       <br><br>[Login]
     </th>
     <th align="center">
       <img width="200" alt="1" src="https://user-images.githubusercontent.com/11826495/79090198-d0790000-7d83-11ea-83bb-238788e867e2.gif"/>
       <br><br>[Participant_Main] 
    </th>
     <th align="center">
      <img width="200" alt="1" src="https://user-images.githubusercontent.com/11826495/79090210-da9afe80-7d83-11ea-931a-f7673474108e.gif"/>
       <br><br>[SendTextMission]
    </th>
     <th align="center">
      <img width="200" alt="1" src="https://user-images.githubusercontent.com/11826495/79090227-e981b100-7d83-11ea-94df-47986a12f694.gif"/>
       <br><br>[SendImageMission]
    </th>
  </tr>
  <tr>
     <th align="center">
       <img width="200" alt="1" src="https://user-images.githubusercontent.com/11826495/79090260-0c13ca00-7d84-11ea-9303-71f5e753de1e.gif"/>
       <br><br>[MakeRoom]
     </th>
     <th align="center">
       <img width="200" alt="1" src="https://user-images.githubusercontent.com/11826495/79090287-1e8e0380-7d84-11ea-8d36-1c11881cb54e.gif"/>
       <br><br>[SendRoomKakaoLink] 
    </th>
     <th align="center">
      <img width="200" alt="1" src="https://user-images.githubusercontent.com/11826495/79090365-657bf900-7d84-11ea-997b-1d4c6b51aa95.gif"/>
       <br><br>[Manager_Main]
    </th>
     <th align="center">
      <img width="200" alt="1" src="https://user-images.githubusercontent.com/11826495/79090313-3a91a500-7d84-11ea-9661-09fe4ef7fc15.gif"/>
       <br><br>[MakeMission]
    </th>
  </tr>
</table>

- - -

## 프로젝트 목표
* 앱을 이용하여 온라인/오프라인 커뮤니티 활동을 더 즐겁게 만든다.
* 활동내의 구성원에 대해 빨리 알 수 있으며, 친해지게 만든다.
* 익명으로 미션을 수행하기 때문에, 모임간의 주제가 되기 쉽다.

- - -

## 활용 기술
* [MVVM](https://wlgusdn700.tistory.com/2?category=827157)/[DataBinding](https://wlgusdn700.tistory.com/3?category=827157)
  * Activity가 많아 중복되는 코드를 줄이고, UI 코드를 줄이기 위해 사용
  * 협업하는 데에 있어 정해진 규칙을 따라 개발하므로써 의사소통, 유지보수, 개발 시간 단축을 위해 사용
* BindingAdapter : DataBinding와 리사이클러 뷰를 연동할 때 Item을 편하게 넣기 위해 사용
* [RxJava](https://wlgusdn700.tistory.com/4?category=827157) / [RxAndroid](https://wlgusdn700.tistory.com/5?category=827157) : 서버와 HTTP 통신이 많으므로, Rx를 이용해 안드로이드 내 비동기작업에서의 Thread관리를 위해 사용
* [Retrofit](https://wlgusdn700.tistory.com/9?category=827157) : 서버와의 HTTP통신을 짧은 코드와 간편화하기 위해 사용
* Glide : 이미지 삽입, 관리 등을 간편화하기 위해 사용
* FireBase : Firebase Cloud Message를 위해 사용
* Kakao Login/[Kakao Link](https://wlgusdn700.tistory.com/14?category=827157) : 사용자의 신원확인을 위해 Login을 사용 / 생성된 방을 공유하기 위해 Link를 사용
* Recycler View : 리스트 UI 사용과 확장성, 가독성 등의 측면등을 위해 사용
* Fragment : Activity내의 하나의 ViewModel을 사용하기 위해 Fragment 단위로 공유하기 위해 사용







