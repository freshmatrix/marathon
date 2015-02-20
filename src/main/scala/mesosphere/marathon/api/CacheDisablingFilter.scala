package mesosphere.marathon.api

import javax.servlet._
import javax.servlet.http.HttpServletResponse

class CacheDisablingFilter extends Filter {
  override def init(filterConfig: FilterConfig): Unit = {}

  override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain): Unit = {
    response match {
      case httpResponse: HttpServletResponse => httpResponse.setHeader("Cache-Control", "no-cache")
      case _                                 => // ignore other responses
    }

    chain.doFilter(request, response)
  }

  override def destroy(): Unit = {}
}
