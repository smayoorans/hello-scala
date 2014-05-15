package com.guru.mayoo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMethod, RequestMapping}

import java.lang.String

@Controller
class HomeController {

  @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
  def getIndexPage: String = {
    "index"
  }

}
