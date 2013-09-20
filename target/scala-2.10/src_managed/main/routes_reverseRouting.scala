// @SOURCE:/home/kpw/workspace/simplecoder/conf/routes
// @HASH:dd7e0d19c7846ca9cc9bdf90894f4bf3aab8e866
// @DATE:Fri Sep 06 05:48:14 EDT 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:11
// @LINE:8
// @LINE:6
package controllers {

// @LINE:8
// @LINE:6
class ReverseApplication {
    

// @LINE:8
def reverse(latlng:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "r/" + implicitly[PathBindable[String]].unbind("latlng", dynamicString(latlng)))
}
                                                

// @LINE:6
def query(query:String, bound:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "q/" + implicitly[PathBindable[String]].unbind("query", dynamicString(query)) + "/" + implicitly[PathBindable[String]].unbind("bound", dynamicString(bound)))
}
                                                
    
}
                          

// @LINE:11
class ReverseAssets {
    

// @LINE:11
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          
}
                  


// @LINE:11
// @LINE:8
// @LINE:6
package controllers.javascript {

// @LINE:8
// @LINE:6
class ReverseApplication {
    

// @LINE:8
def reverse : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.reverse",
   """
      function(latlng) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "r/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("latlng", encodeURIComponent(latlng))})
      }
   """
)
                        

// @LINE:6
def query : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.query",
   """
      function(query,bound) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "q/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("query", encodeURIComponent(query)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("bound", encodeURIComponent(bound))})
      }
   """
)
                        
    
}
              

// @LINE:11
class ReverseAssets {
    

// @LINE:11
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              
}
        


// @LINE:11
// @LINE:8
// @LINE:6
package controllers.ref {

// @LINE:8
// @LINE:6
class ReverseApplication {
    

// @LINE:8
def reverse(latlng:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.reverse(latlng), HandlerDef(this, "controllers.Application", "reverse", Seq(classOf[String]), "GET", """""", _prefix + """r/$latlng<[^/]+>""")
)
                      

// @LINE:6
def query(query:String, bound:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.query(query, bound), HandlerDef(this, "controllers.Application", "query", Seq(classOf[String], classOf[String]), "GET", """ Home page""", _prefix + """q/$query<[^/]+>/$bound<[^/]+>""")
)
                      
    
}
                          

// @LINE:11
class ReverseAssets {
    

// @LINE:11
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          
}
                  
      