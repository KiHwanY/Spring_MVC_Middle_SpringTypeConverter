package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {
    /*
    *   ConversionService는 개별 컨버터를 모아두고 그것들을 묶어서 편리하게 사용할 수 있는 기능을 제공한다.
    *
    *   DefaultConversionService 는 ConversionService 인터페이스를 구현했는데, 추가로 컨버터를 등록하는 기능도 제공한다.
    *
    *   [등록과 사용 분리]
    *
    *   컨버터를 등록할 때는 StringToIntegerConverter 같은 타입 컨버터를 명확하게 알아야 한다.
    *   반면에 컨버터를 사용하는 입장에서는 타입 컨버터를 전혀 몰라도 된다.
    *   타입 컨버터들은 모두 컨버젼 서비스 내부에 숨어서 제공된다.
    *   물론 컨버젼 서비스를 등록하는 부분과 사용하는 부분을 분리하고 의존관계 주입을 사용해야 한다.
    * */

    @Test
    void conversionService() {
        //등록
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        //사용
        assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");

        /*
        *   [컨버젼 서비스 사용]
        *   Integer value = conversionService.convert("10" , Integer.class)
        *
        *   [인터페이스 분리 원칙 - ISP(Interface Segregation Principle)]
        *   인터페이스 분리 원칙은 클라이언트가 자신이 이용하지 않는 메서드에 의존하지 않아야 한다.
        *
        *   DefaultConversionService 는 두 인터페이스를 구현했다.
        *       -> ConversionService : 컨버터 사용에 초점
        *       -> ConverterRegistry : 컨버터 등록에 초점
        *
        *   이렇게 인터페이스를 분리하면 컨버터를 사용하는 클라이언트와 컨버터를 등록하고 관리하는 클라이언트의 관심사를 명확하게 분리할 수 있다.
        *   특히 컨버터를 사용하는 클라이언트는 ConversionService만 의존하면 되므로,
        *   컨버터를 어떻게 등록하고 관리하는지는 전혀 몰라도 된다.
        *   결과적으로 컨버터를 사용하는 클라이언트는 꼭 필요한 메서드만 알게 된다.
        *   이렇게 인터페이스를 분리하는 것을 ISP라 한다.
        *
        * */
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        String ipPortString = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(ipPortString).isEqualTo("127.0.0.1:8080");

    }
}
