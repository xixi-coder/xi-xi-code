package com.example.xixi.netty.helloWorld.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
public class MyHttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static final HttpDataFactory HTTP_DATA_FACTORY = new DefaultHttpDataFactory(DefaultHttpDataFactory.MAXSIZE);


    static {
        DiskFileUpload.baseDirectory = "/Users/shiheng/IdeaProjects/xi-xi-code/src/main/java/com/example/xixi/netty/fileupload";
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        //HttpRequest request = fullHttpRequest;
        //String uri = fullHttpRequest.uri();
        //获取method
        HttpMethod method = fullHttpRequest.method();
        //根据method解析参数，封装数据，
        if (HttpMethod.GET.equals(method)) {
            parseGet(fullHttpRequest);
        }else if (HttpMethod.POST.equals(method)) {
            parsePost(fullHttpRequest);
        }else {
            log.error("{} method is not supported ,please change http method for get or post!");
        }
        //service
        //response client
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h3>success</h3>");
        sb.append("</body>");
        sb.append("</html>");
        writeResponse(ctx,fullHttpRequest,HttpResponseStatus.OK,sb.toString());
    }

    private void writeResponse(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest, HttpResponseStatus status, String msg) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,status);
        response.content().writeBytes(msg.getBytes(StandardCharsets.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/html;charset=utf-8");
        HttpUtil.setContentLength(response,response.content().readableBytes());
        boolean keepAlive = HttpUtil.isKeepAlive(fullHttpRequest);
        if (keepAlive) {
            response.headers().set(HttpHeaderNames.CONNECTION,"keep-alive");
            ctx.writeAndFlush(response);
        }else {
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

    private void parsePost(FullHttpRequest fullHttpRequest) {
        //获取content-type
        String contentType = getContentType(fullHttpRequest);
        switch (contentType) {
            case "application/json":
                parseJson(fullHttpRequest.content());
                break;
            case "application/x-www-form-urlencoded":
                parseForm(fullHttpRequest);
                break;
            case "multipart/form-data":
                parseMultipart(fullHttpRequest);
                break;
            default:
        }

    }

    private void parseMultipart(FullHttpRequest fullHttpRequest) {
        HttpPostRequestDecoder postRequestDecoder = new HttpPostRequestDecoder(HTTP_DATA_FACTORY,fullHttpRequest);
        //判断是否是multipart
        if (postRequestDecoder.isMultipart()) {
            //获取 body中的数据
            List<InterfaceHttpData> bodyHttpDatas = postRequestDecoder.getBodyHttpDatas();
            bodyHttpDatas.forEach(dataItem ->{
                //获取数据项的类型
                InterfaceHttpData.HttpDataType dataType = dataItem.getHttpDataType();
                //判断是普通表达项还是文件上传项
                if (dataType.equals(InterfaceHttpData.HttpDataType.Attribute)) {
                    //普通表单项 直接获取数据
                    Attribute attribute = (Attribute) dataItem;
                    try {
                        log.info("表单项名称:{},表单项值:{}",attribute.getName(),attribute.getValue());
                    } catch (IOException e) {
                        log.error("获取表单项数据错误,msg={}",e.getMessage());
                    }

                }else if (dataType.equals(InterfaceHttpData.HttpDataType.FileUpload)) {
                    //文件上传项 处理待上传的数据
                    FileUpload fileUpload = (FileUpload) dataItem;
                    //获取原始文件名称
                    String filename = fileUpload.getFilename();
                    //获取表单name属性
                    String name = fileUpload.getName();
                    log.info("文件名称:{},表单项名称:{}",filename,name);
                    //将文件数据保存到磁盘
                    if (fileUpload.isCompleted()) {
                        try {
                            String path = DiskFileUpload.baseDirectory + File.separator + filename;
                            //File file = fileUpload.getFile();
                            fileUpload.renameTo(new File(path));
                        } catch (IOException e) {
                            log.error("文件转存失败,msg={}",e.getMessage());
                        }
                    }
                }else {

                }
            });
        }
    }

    private void parseForm(FullHttpRequest fullHttpRequest) {
        //post请求时uri中也可能携带参数
        parseKVstr(fullHttpRequest.uri(),true);
        //解析请求体中的表单数据
        parseFormData(fullHttpRequest.content());
    }

    private void parseFormData(ByteBuf body) {
        String bodystr = body.toString(StandardCharsets.UTF_8);
        parseKVstr(bodystr,false);
    }

    private void parseJson(ByteBuf jsonbody) {
        String jsonstr = jsonbody.toString(StandardCharsets.UTF_8);
        //使用json工具反序列化
        JSONObject jsonObject = JSONObject.parseObject(jsonstr);
        //打印 json数据
        jsonObject.entrySet().stream().forEach(entry ->{
            log.info("json key={},json value={}",entry.getKey(),entry.getValue());
        });
    }

    private String getContentType(FullHttpRequest request) {
        HttpHeaders headers = request.headers();
        String contentType = headers.get(HttpHeaderNames.CONTENT_TYPE);// text/plain;charset=UTF-8
        //List<String> acceptEncoding = headers.getAll(HttpHeaderNames.ACCEPT_ENCODING);//accept-encoding:gzip, deflate, br
        return contentType.split(";")[0];
    }

    private void parseGet(FullHttpRequest request) {
        //通过uri解析请求参数
        parseKVstr(request.uri(),true);
    }

    private void parseKVstr(String str,boolean hasPath) {
        //通过QueryStringDecoder解析kv字符串
        QueryStringDecoder qsd = new QueryStringDecoder(str, StandardCharsets.UTF_8,hasPath);////get请求的uri是: path?k=v
        Map<String, List<String>> parameters = qsd.parameters();
        //封装参数，执行业务 此处打印即可
        if (parameters!=null && parameters.size() > 0) {
            parameters.entrySet().stream().forEach(entry->{
                log.info("参数名:{},参数值:{}",entry.getKey(),entry.getValue());
            });
        }
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("MyHttpServerHandler Exception,{}",cause.getMessage());
    }
}
