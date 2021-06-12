package pl.kinga.wristapp.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/")
@Controller
class IndexController {

    @GetMapping
    fun home() = "redirect:users"

}
