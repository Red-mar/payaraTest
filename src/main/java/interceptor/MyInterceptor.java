package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class MyInterceptor {
    @AroundInvoke
    public Object methodInterceptor(InvocationContext ctx) throws Exception {
        System.out.println(" Intercepting call to: " + ctx.getMethod().getName());
        return ctx.proceed();
    }
}
