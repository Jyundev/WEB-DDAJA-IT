<!-- https://lazymankook.tistory.com/30 -->
DAO (Data Access Object) 
- DB를 사용해 데이터를 조회하거나 조작하는 기능을 담당
- 비즈니스 로직이나 DB와 관련없는 코드들 분리하기 위해 사용 

DTO(Data Transfer Object) 
- Database에서 Data를 얻어 Service나 Controller 등으로 보낼 때 사용하는 객체 
- 데이터를 주고받을 포맷
Service 
- 비지니스 로직이 들어가는 부분
- Controller가 Request를 받으면 적절한 Service에 전달하고, 전달 받은 Service는 비즈니스 로직을 처리

=> DAO로 데이터베이스를 접근하고, DTO로 데이터를 전달받은 다음, 적절한 처리를 해 반환한다. 

<!-- https://velog.io/@limsubin/Spring-Security-JWT-%EC%9D%84-%EA%B5%AC%ED%98%84%ED%95%B4%EB%B3%B4%EC%9E%90 -->

<!-- https://rlaehddnd0422.tistory.com/135 -->

<!-- https://velog.io/@yaaloo/Spring-Security-JWT -->

Security & JWT

JWT = Base64 Encode(Header + payload + Signature)
- Header : 토큰의 타입, JWT 생성에 사용될 해쉬 알고리즘을 저장합니다.
- Payload : 토큰에서 사용할 정보의 조각들인 Claim 이 담겨있습니다. (실제 JWT 를 통해서 알 수 있는 데이터)
- Signature : 시그니처는 "정의한 Header와 Palyoad를 Base64로 인코딩한 값 + 서버의 Secret Key(Base64 인코딩은 선택)"을 Header에서 정의한 알고리즘으로 해시화한 값입니다.

=>  JWT = Base64 Encode(Header + payload + Signature(HS_Algorithm(Base64Encode(Header) + Base64Encode(Payload) + Server's Secret Key))

 
1. 클라이언트가 서버에 로그인 요청

 
2. 서버는 클라이언트의 ID와 PW를 확인 후, Header, Payload, Signature를 정의한 후 Access Token과 Refresh Token를 생성하여 클라이언트에게 발급

Access Token : 클라이언트가 갖고있는 실제로 유저의 정보가 담긴 토큰으로, 클라이언트에서 요청이 오면 서버에서 해당 토큰에 있는 정보를 활용하여 사용자 정보에 맞게 응답을 진행합니다.

Refresh Token: 새로운 Access Token을 재발급해주기 위해 사용하는 토큰. 해당 토큰은 보통 데이터베이스에 유저 정보와 같이 기록됩니다.
 

3. 클라이언트는 서버로부터 받은 JWT(Access Token, Refresh Token)을 로컬 저장소에 저장하고 이 후 요청할 때 액세스 토큰을 헤더에 넣어 요청을 보냅니다.

4. 서버에서는 액세스 토큰을 검증하고, 문제가 없으면 응답해줍니다.

5. 만약 클라이언트의 액세스 토큰의 유효기간이 지나면 리프레시 토큰을 서버에 전달하여 액세스 토큰을 재발급 요청합니다.

6. 리프레시 토큰을 검증하여 액세스 토큰을 재발급하여 클라이언트에 전달합니다.


----

1. /join api로 서버에 회원 가입 요청
2. 일반 유저임으로 ROLE_USER 권한으로 db에 저장
2. /authenticate rest로 해당 계정 jwt token 발급
3. 위에 발급 받은 jwt와 함께 hasAnyRole 권한이 부여된 /user rest 요청