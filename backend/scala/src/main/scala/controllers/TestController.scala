package controllers

import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/customers"))
class TestController {
  @GetMapping(value = Array("/{id}"),
    produces = Array("application/json"))
  def getCustomer(@PathVariable("id") id: Long): Long = id

}
