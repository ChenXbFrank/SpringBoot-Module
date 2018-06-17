package springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import springbootdemo.model.User;
import springbootdemo.repository.UserRepository;

import java.util.Collection;

/**
 *  路由器函数配置
 * Created by 81046 on 2018-06-16
 */
@Configuration
public class RouterFunctionConfiguration {

    /**
     * Servlet
     *      请求接口：ServletRequest或者HttpServletRequest
     *      响应接口：ServletResponse或者HttpServletResponse
     *  Spring 5.0 重新定义了服务请求和响应接口
     *      请求接口：ServletRequest
     *      响应接口：ServletResponse
     *  即可支持Servlet 规范，也可以支持自定义，比如 Netty （Web Server）
     *
     *  以本例：
     *      定义GET请求，并且返回所有的用户对象 URI：/user/findAll
     *      Flux 是0-N个对象集合
     *      Mono 是0-1个对象集合
     *      Reactive 中的Flux 或者 Mono 它是异步处理 （非阻塞）
     *      集合对象基本上是同步处理 （阻塞）
     *      Flux 或者 Mono 都是 Publisher
     */
    @Bean
    public RouterFunction<ServerResponse> userFindAll(UserRepository userRepository){
        return RouterFunctions.route(RequestPredicates.GET("/user/list"),
                request -> {
                    //返回所有的用户
                    Collection<User> users = userRepository.findAll();
                    Flux<User> userFlux = Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux,User.class);
                });
    }
}
