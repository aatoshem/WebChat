package com.leidos.webchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
public class LoginController {

//    @RequestMapping("/securedPage")
//    public String securedPage(Model model,
//                              @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
//                              @AuthenticationPrincipal OAuth2User oauth2User) {
//        model.addAttribute("userName", oauth2User.getName());
//        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
//        model.addAttribute("userAttributes", oauth2User.getAttributes());
//        return "securedPage";
//    }

    @RequestMapping("/")
    public String index(Model model, Principal principal) {
        return "index";
    }

}
