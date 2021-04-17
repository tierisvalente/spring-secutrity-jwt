package dev.tieris.springsecurity.configuration

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CORSFilter : Filter {

    companion object {
        var VALID_METHODS = "DELETE, HEAD, GET, OPTIONS, POST, PUT"
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilter(req: ServletRequest, resp: ServletResponse, chain: FilterChain) {
        val httpReq = req as HttpServletRequest
        val httpResp = resp as HttpServletResponse

        // No Origin header present means this is not a cross-domain request
        val origin = httpReq.getHeader("Origin")
        if (origin == null) {
            // Return standard response if OPTIONS request w/o Origin header
            if ("OPTIONS".equals(httpReq.method, ignoreCase = true)) {
                httpResp.setHeader("Allow", VALID_METHODS)
                httpResp.status = 200
                return
            }
        } else {
            // This is a cross-domain request, add headers allowing access
            httpResp.setHeader("Access-Control-Allow-Origin", origin)
            httpResp.setHeader("Access-Control-Allow-Methods", VALID_METHODS)
            val headers = httpReq.getHeader("Access-Control-Request-Headers")

            if (headers != null) httpResp.setHeader("Access-Control-Allow-Headers", headers)

            // Allow caching cross-domain permission
            httpResp.setHeader("Access-Control-Max-Age", "3600")
        }

        // Pass request down the chain, except for OPTIONS
        if (!"OPTIONS".equals(httpReq.method, ignoreCase = true)) {
            chain.doFilter(req, resp)
        }
    }

    @Throws(ServletException::class)
    override fun init(config: FilterConfig?) {}

    override fun destroy() {}
}