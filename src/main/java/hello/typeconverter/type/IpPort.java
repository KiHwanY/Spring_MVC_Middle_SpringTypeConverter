package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;


// 사용자 정의 타입 컨버터

// 타입 컨버터 이해를 돕기 위해 조금 다른 컨버터를 준비해보았다.
// 127.0.0.1:8080과 같은, IP,PORT를 입력하면 IpPort 객체로 변환하는 컨버터를 만들어보자.
@Getter
@EqualsAndHashCode
public class IpPort {
    // 롬복의 @EqualsAndHashCode를 넣으면 모든 필드를 사용해서 equals(),hashcode()를 생성한다.
    // 따라서 모든 필드의 값이 같다면 a.equals(b)의 결과가 참이 된다.
    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
