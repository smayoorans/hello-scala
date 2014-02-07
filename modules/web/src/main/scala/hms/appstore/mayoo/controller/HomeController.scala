package hms.appstore.mayoo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod}
import org.springframework.ui.ModelMap

import javax.servlet.http.HttpServletRequest
import java.lang.String
import com.typesafe.scalalogging.slf4j.Logging

@Controller
class HomeController extends Logging {

  @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
  def getIndexPage: String = {
    "index"
  }

  @RequestMapping(value= Array("/home"),method = Array(RequestMethod.GET))
  def getNewlyAddedAppsPage(modelMap : ModelMap, request : HttpServletRequest): String = {
    val page = request.getParameter("page")

    val username = "Hello"

//    val a = AesCtrExample.testMain

    modelMap.addAttribute("username", username)


    logger.debug("logging............")
    "home"

  }

}
