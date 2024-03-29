package com.msa2024.users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@WebFilter(filterName = "RequestBodyReuseFilter", urlPatterns = "/*")
public class RequestBodyReuseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 요청이 POST나 PUT 메서드인 경우에만 요청 본문을 저장
        if ("POST".equalsIgnoreCase(httpRequest.getMethod()) || "PUT".equalsIgnoreCase(httpRequest.getMethod())) {
            RequestWrapper requestWrapper = new RequestWrapper(httpRequest);
            chain.doFilter(requestWrapper, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필요한 경우 초기화 코드 추가
    }

    @Override
    public void destroy() {
        // 필요한 경우 종료 코드 추가
    }

    private static class RequestWrapper extends HttpServletRequestWrapper {
        private final String body;

        public RequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            body = request.getReader().lines().collect(Collectors.joining());
        }

        @Override
        public BufferedReader getReader() {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }

        @Override
        public ServletInputStream getInputStream() {
            return new ServletInputStream() {
                private boolean finished = false;
                private int index = 0;

                @Override
                public int read() throws IOException {
                    if (finished) {
                        return -1;
                    }
                    if (index == body.length()) {
                        finished = true;
                        return -1;
                    }
                    return body.charAt(index++);
                }

				@Override
				public boolean isFinished() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean isReady() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void setReadListener(ReadListener readListener) {
					// TODO Auto-generated method stub
					
				}
            };
        }
    }
}