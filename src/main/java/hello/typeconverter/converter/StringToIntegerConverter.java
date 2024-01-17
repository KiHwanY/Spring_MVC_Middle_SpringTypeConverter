package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        log.info("convert source={}", source);
        return Integer.valueOf(source);
    }

    /*
    *   String -> Integer로 변환하기 때문에 소스가 String이 된다.
    *   이 문자를 Integer.valueOf(source)를 사용해서 숫자로 변경한 다음에 변경된 숫자를 반환하면 된다.
    *
    * */
}
