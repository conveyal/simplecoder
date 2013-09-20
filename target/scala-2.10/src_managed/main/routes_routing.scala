// @SOURCE:/home/kpw/workspace/simplecoder/conf/routes
// @HASH:dd7e0d19c7846ca9cc9bdf90894f4bf3aab8e866
// @DATE:Fri Sep 06 05:48:14 EDT 2013


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix  
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" } 


// @LINE:6
private[this] lazy val controllers_Application_query0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("q/"),DynamicPart("query", """[^/]+""",true),StaticPart("/"),DynamicPart("bound", """[^/]+""",true))))
        

// @LINE:8
private[this] lazy val controllers_Application_reverse1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("r/"),DynamicPart("latlng", """[^/]+""",true))))
        

// @LINE:11
private[this] lazy val controllers_Assets_at2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """q/$query<[^/]+>/$bound<[^/]+>""","""controllers.Application.query(query:String, bound:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """r/$latlng<[^/]+>""","""controllers.Application.reverse(latlng:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
       
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Application_query0(params) => {
   call(params.fromPath[String]("query", None), params.fromPath[String]("bound", None)) { (query, bound) =>
        invokeHandler(controllers.Application.query(query, bound), HandlerDef(this, "controllers.Application", "query", Seq(classOf[String], classOf[String]),"GET", """ Home page""", Routes.prefix + """q/$query<[^/]+>/$bound<[^/]+>"""))
   }
}
        

// @LINE:8
case controllers_Application_reverse1(params) => {
   call(params.fromPath[String]("latlng", None)) { (latlng) =>
        invokeHandler(controllers.Application.reverse(latlng), HandlerDef(this, "controllers.Application", "reverse", Seq(classOf[String]),"GET", """""", Routes.prefix + """r/$latlng<[^/]+>"""))
   }
}
        

// @LINE:11
case controllers_Assets_at2(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}
    
}
        