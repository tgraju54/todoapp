package todoapp

import Label.Tlabel
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import groovy.json.JsonSlurper
import net.minidev.json.JSONObject
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.FacebookApi
import org.scribe.builder.api.FacebookCustomApi
import org.scribe.extractors.AccessTokenExtractor
import org.scribe.extractors.JsonTokenExtractor
import org.scribe.model.OAuthConstants
import org.scribe.model.OAuthRequest
import org.scribe.model.RequestTuner
import org.scribe.model.Response
import org.scribe.model.Token
import org.scribe.model.Verb
import org.scribe.model.Verifier
import org.scribe.oauth.OAuth20ServiceImpl
import org.scribe.oauth.OAuthService
import org.mozilla.javascript.ast.XmlString

import javax.xml.ws.Service
import java.util.concurrent.TimeUnit

@Secured(['permitAll'])

class FacebookoauthController   {
    def GetuserService
    def springSecurityService

    String apiKey = "268818280312096"
    String apiSecret = "22cd044d940367282c75f3a81def8dc1"
    String callBackUrl = "http://localhost:8080/facebookoauth/login"
    String baseUrl = "https://graph.facebook.com/";
    Token requestToken = null

    def index() {
        Token facebookAccessToken = null;
        OAuthService service = new ServiceBuilder()
                .provider(FacebookCustomApi.class)
                .apiKey(apiKey)
                .scope('public_profile,email')
                .apiSecret(apiSecret)
                .callback(callBackUrl)
                .build();


        String authUrl = service.getAuthorizationUrl(requestToken);
        redirect(url: authUrl.toURL())



    }


    def login() {



        OAuthService service = new ServiceBuilder()
                .provider(FacebookCustomApi.class)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .scope('public_profile,email')
                .callback(callBackUrl)
                .build();

        Verifier v = new Verifier(params.code);
        Token accessToken= service.getAccessToken(null,v)
        OAuthRequest request=new OAuthRequest(Verb.GET,"https://graph.facebook.com/me?fields=id,name,email")
        service.signRequest(accessToken, request);
        Response response=request.send()

        def sulpher=new JsonSlurper()
        def result = sulpher.parseText(response.getBody())
        def decide= GetuserService.checkuser(result.id)
        if(decide==3)
        {
            [name:result.name,email:result.email,id:result.id]
        }
        else
        {
            springSecurityService.reauthenticate(result.id)
            redirect(controller: "dashboard", action: "index")
        }

    }
    def setup()
    {
        def usernew=new User(username:params.username,email:params.email,password:params.password,uname: params.uname)
        usernew.save()
        def adminRole = Role.findOrSaveWhere(authority: 'ROLE_USER')
        UserRole.create(usernew,adminRole,true)
        new Tlabel(user: usernew.id, lname: 'Genral').save()
        springSecurityService.reauthenticate(params.username,params.password)
        redirect(controller: "dashboard", action: "loginservice")
    }




}