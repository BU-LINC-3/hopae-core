package kr.hopae.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/debug")
public class DebugController {

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public Map<String, Object> debugSession(HttpSession session) {

        Map<String, Object> map = new HashMap<>();

        map.put("LoginInfo", session.getAttribute("LoginInfo"));
        map.put("Alias", session.getAttribute("Alias"));
        map.put("ConnectionId", session.getAttribute("ConnectionId"));
        map.put("SchemaId", session.getAttribute("SchemaId"));
        map.put("CredDefId", session.getAttribute("CredDefId"));
        map.put("CredExId", session.getAttribute("CredExId"));

        return map;
    }
}