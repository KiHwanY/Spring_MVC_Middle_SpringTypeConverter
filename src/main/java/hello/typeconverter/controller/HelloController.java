package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) { // 문자 타입을 숫자 타입으로 변경
        String data = request.getParameter("data");//문자 타입 조회
        Integer intValue = Integer.valueOf(data); //숫자 타입으로 변경
        System.out.println("intValue = " + intValue);
        return "ok";

        /*
        *   [분석]
        *
        *   String data = request.getParameter("data")
        *   HTTP 요청 파라미터는 모두 문자로 처리된다. \
        *   따라서 요청 파라미터를 자바에서 다른 타입으로 변환해서 사용하고 싶으면 다음과 같이 숫자 타입으로 변환하는 과정을 거쳐야 한다.
        *   Integer intValue = Integer.valueOf(data)
        *
        * */
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) { // Spring MVC가 제공하는 @RequestParam을 사용함
        System.out.println("data = " + data);
        return "ok";

        /*
        *   앞서 보았듯이 HTTP 쿼리 스트링으로 전달하는 data=10 부분에서 10은 숫자 10이 아니라 문자 10이다.
        *   스프링이 제공하는 @RequestParam을 사용하면 이 문자 10을 Integer 타입의 숫자 10으로 편리하게 받을 수 있다.
        *
        *   [이것은 스프링이 중간에서 타입을 변환해주었기 때문이다.]
        *
        *
        *   - 스프링의 타입 변환 적용 예
        *   -> 스프링 MVC 요청 파라미터
        *       -> @RequestParam , @ModelAttribute , @PathVariable
        *   -> @Value 등으로 YML 정보 읽기
        *   -> XML 에 넣은 스프링 빈 정보를 변환
        *   -> 뷰를 렌더링 할 때
        *
        *
        *   - 스프링과 타입 변환
        *   -> 이렇게 타입을 변환해야 하는 경우는 상당히 많다.
        *   개발자가 직접 하나하나 타입 변환을 해야 한다면, 생각만 해도 괴로울 것이다.
        *   스프링이 중간에 타입 변환기를 사용해서 타입을 String ->Integer 로 변환해주었기 때문에 개발자는 편리하게 해당 타입을 바로 받을 수 있다.
        *   앞에서는 문자를 숫자로 변경하는 예시를 들었지만, 반대로 숫자를 문자로 변경하는 것도 가능하고,
        *   Boolean 타입을 숫자로 변경하는 것도 가능하다 .
        *   만약 개발자가 새로운 타입을 만들어서 변환하고 싶으면 어떻게 하면 될까 ?
        *
        *
        *   - 컨버터 인터페이스 (인터페이스 참고)
        *   ex) public interface Converter<S , T> {
        *           T convert(S source);
        *       }
        *
        *   -> 스프링은 확장 가능한 컨버터 인터페이스를 제공한다.
        *      개발자는 스프링에 추가적인 타입 변환이 필요하면 이 컨버터 인터페이스를 구현해서 등록하면 된다.
        *      이 컨버터 인터페이스는 모든 타입에 적용할 수 있다.
        *      필요하면 X -> Y 타입으로 변환하는 컨버터 인터페이스를 만들고, 또 Y -> X 타입으로 변환하는 컨버터 인터페이스를 만들어서 등록하면 된다.
        *
        *      예를 들어서 문자로 "true" 가 오면 Boolean 타입으로 받고 싶으면 String -> Boolean 타입으로 변환되도록
        *      컨버터 인터페이스를 만들어서 등록하고, 반대로 적용하고 싶으면 Boolean -> String 타입으로 변환되도록 컨버터를 추가로 만들어서 등록하면 된다.
        *
        *
        * */
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        System.out.println("ipPort IP = " + ipPort.getIp());
        System.out.println("ipPort PORT = " + ipPort.getPort());
        return "ok";
    }
}
