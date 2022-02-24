//package com.example.xixi.binfashizhan.chapter02;
//
//import javax.servlet.GenericServlet;
//import javax.servlet.Servlet;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.annotation.WebServlet;
//import java.math.BigInteger;
//
///**
// * @author : xi-xi
// */
//@WebServlet( name = "GenericServletDemoServlet",
//        urlPatterns = { "/generic" }
//        )
//public class SynchronizedFactorizer extends GenericServlet implements Servlet {
//    private BigInteger lastNumber;
//    private BigInteger[] lastFactors;
//
//    public synchronized void service(ServletRequest req,
//                                     ServletResponse resp) {
//        BigInteger i = extractFromRequest(req);
//        if (i.equals(lastNumber))
//            encodeIntoResponse(resp, lastFactors);
//        else {
//            BigInteger[] factors = factor(i);
//            lastNumber = i;
//            lastFactors = factors;
//            encodeIntoResponse(resp, factors);
//        }
//    }
//
//    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
//    }
//
//    BigInteger extractFromRequest(ServletRequest req) {
//        return new BigInteger("7");
//    }
//
//    BigInteger[] factor(BigInteger i) {
//        // Doesn't really factor
//        return new BigInteger[] { i };
//    }
//}
//
