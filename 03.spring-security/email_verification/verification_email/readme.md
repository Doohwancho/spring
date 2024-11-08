# A. what 
1. email 인증 POST 요청 보내면,
2. email에 6자리 코드 보내고 
3. 그 코드 + email 로 /verify POST 요청 보내면 
4. 6자리 코드 verify 해준다.

# B. how to setup?
1. google 계정에 2 step verification 인증한다.
2. google_security -> app passwords -> 16자리 password 발급받음
3. application.yml에 username: 구글 이메일, password:에 발급받은 16자리 패스워드 입력 
