package hello.typeconverter;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /*
    *   스프링은 내부에서 ConversionServaice를 제공한다.
    *   우리는 WebMvcConfigure 가 제공하는 addFormatters()를 사용해서 추가하고 싶은 컨버터를 등록하면 된다.
    *   이렇게 하면 스프링은 내부에서 사용하는 ConversionService에 컨버터를 추가해준다.
    * */

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //주석처리 우선순위
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        //추가
        registry.addFormatter(new MyNumberFormatter());

        /*
        *   [ 주의 ]
        *
        *   StringToIntegerConverter, IntegerToStringConverter를 꼭 주석처리 하자.
        *   MyNumberFormatter도 숫자 -> 문자, 문자 -> 숫자로 변경하기 때문에 둘의 기능이 겹친다.
        *   우선순위는 컨버터가 우선하므로 포멧터가 적용되지 않고, 컨버터가 적용된다.
        *
        *
        *   [ 실행 - 객체 -> 문자]
        *
        *   • ${number}: 10000
            • ${{number}}: 10,000
            *
            *   컨버전 서비스를 적용한 결과 MyNumberFormatter가 적용되어서 10,000 문자가 출력된 것을 확인할 수 있다.
            *
            *
            [ 실행 - 문자 -> 객체]

            MyNumberFormatter : text=10,000, locale=ko_KR
            data = 10000
            *
            *   "10,000" 이라는 포맷팅 된 문자가 Integer 타입의 숫자 10000으로 정상 변환 된 것을 확인할 수 있다.
        * */
    }
}
