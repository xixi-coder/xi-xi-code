//package com.example.xixi.binfashizhan.chapter02;
//
//
//import java.util.concurrent.atomic.AtomicLong;
//import java.math.BigInteger;
//import java.util.concurrent.atomic.*;
//import javax.servlet.*;
///**
// * @author : xi-xi
// */
//public class CountingFactorizer extends GenericServlet implements Servlet {
//    private final AtomicLong count = new AtomicLong(0);
//
//    public long getCount() { return count.get(); }
//
//    public void service(ServletRequest req, ServletResponse resp) {
//        BigInteger i = extractFromRequest(req);
//        BigInteger[] factors = factor(i);
//        count.incrementAndGet();
//        encodeIntoResponse(resp, factors);
//    }
//
//    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {}
//    BigInteger extractFromRequest(ServletRequest req) {return null; }
//    BigInteger[] factor(BigInteger i) { return null; }
//}
