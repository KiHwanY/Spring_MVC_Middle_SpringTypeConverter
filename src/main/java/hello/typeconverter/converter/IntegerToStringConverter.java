package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IntegerToStringConverter implements Converter<Integer, String> {

    @Override
    public String convert(Integer source) {
        log.info("convert source={}", source);
        return String.valueOf(source);
    }
    /*
    *   이번에는 숫자를 문자로 변환하는 타입 컨버터이다.
    *   앞의 컨버터와 반대의 일을 한다.
    *   이번에는 숫자가 입력되기 때문에 소스가 Integer가 된다.
    *   String.valueOf(source) 를 사용해서 문자로 변경한 다음 변경된 문자를 반환하면 된다.
    *
    * */
}
