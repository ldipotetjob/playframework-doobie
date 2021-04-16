
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object explore extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.4*/("""

"""),_display_(/*3.2*/main("Hello World")/*3.21*/ {_display_(Seq[Any](format.raw/*3.23*/("""
"""),_display_(/*4.2*/defining(play.core.PlayVersion.current)/*4.41*/ { version =>_display_(Seq[Any](format.raw/*4.54*/("""

"""),format.raw/*6.1*/("""<section id="content">
  <div class="wrapper doc">
    <article>
      <h2>Play application overview</h2>

      <p>This tutorial is implemented as a Play application that demonstrates Play's basics. We started with the Play
        Scala seed template, which set up the application project structure and the configuration to build with sbt.
        We added stylesheets with Play's colors and a Table of Contents.</p>
      <p>Let's start by looking at what happens at runtime. When you entered the server name and port number, <a target="play-docs"
          href="http://localhost:9000/">http://localhost:9000/</a>, in your browser:</p>
      <ul>
        <li>The browser requested the root <code>/</code> URI from the HTTP server using the <code>GET</code> method.</li>
        <li>The Play internal HTTP Server received the request.</li>
        <li>Play resolved the request using the <code>routes</code> file, which maps URIs to controller action methods.</li>
        <li>The action method used Twirl templates to render the <code>index</code> page.</li>
        <li>The HTTP server returned the response as an HTML page.</li>
      </ul>
      <p> At a high level, the flow looks something like this:</p>
      <p><img src="assets/images/play-request-response.png" alt="Request and response" class="small-5 medium-4 large-3" /></p>
      <h3>Explore the project</h3>
      <p>Next, let's look at the tutorial project to locate the implementation for the following:</p>
      <ul>
        <li>The controller action method that defines how to handle a request to the root URI.</li>
        <li>The <code>conf/routes</code> file that maps the request to the controller method.</li>
        <li>The Twirl template that the action method calls to render the HTML markup.</li>
      </ul>
      <p>Using a command window or a GUI, start with the top-level project directory. The following directories contain
        application components:</p>

      <blockquote>Note: When changing directories in Windows shells, substitute <code>/</code> for <code>\</code> in
        path names.</blockquote>
      <p>
        <ol>
          <li>The <code>app</code> subdirectory is where you put your Scala code and packages. It contains directories
            for <code>controllers</code> and <code>views</code>, which will be familiar to those experienced with the
            Model View Controller (MVC) architecture. Since this simple project does not need an external data
            repository, it does not contain a <code>models</code> directory, but this is where you would add it. You
            could also add a <code>service</code> package and <code>utils</code> here. </li>
          <li>The <code>public</code> subdirectory contains directories for <code>images</code>, <code>javascripts</code>,
            and <code>stylesheets</code>.</li>
          <li>The <code>conf</code> directory contains application configuration. For a more detailed explanation of
            the project's structure, see <a href="https://www.playframework.com/documentation/"""),_display_(/*47.96*/version),format.raw/*47.103*/("""/Anatomy#The-Play-application-layout"
              target="blank" />Play Application Layout</a>.</li>

          <li>
            <p>To locate the controller action method, open <code>app/controllers/HomeController.scala</code> file with
            your favorite text editor.</p>

            <p>The <code>Homecontroller</code> class includes the <code>index</code> action method, as shown below.
              This is a very simple action method that generate an HTML page from the <code>index.scala.html</code>
              Twirl template file.</p>
              <pre><code class="language-scala">def index() = Action """),format.raw/*57.70*/("""{"""),format.raw/*57.71*/(""" """),format.raw/*57.72*/("""implicit request: Request[AnyContent] =>
  Ok(views.html.index())
"""),format.raw/*59.1*/("""}"""),format.raw/*59.2*/("""</code></pre>
          </li>
          <li>To view the route that maps the browser request to the controller method, open the <code>conf/routes</code>
            file.
            <p>A route consists of an HTTP method, a path, and an action. This control over the URL schema makes it
              easy to
              design clean, human-readable, bookmarkable URLs. The following line maps a GET request for the root URL
              <code>/</code>
              to the <code>index</code> action in <code>HomeController</code>:</p>
            <code>GET     /                           controllers.HomeController.index</code></li>
          <li>Open <code>app/views/index.scala.html</code> with your text editor.
            <p>The main directive in this file calls the main template <code>main.scala.html</code> with the string
              <code>"Welcome"</code>
              to generate the page.
              You can open <code>app/views/main.scala.html</code> to see how a <code>String</code> parameter sets the
              page
              title.</p>
          </li>
        </ol>
      </p>
      <h3 id="next-steps">Next steps</h3>
      <p>With this overview of the tutorial application, you are ready to <a href=""""),_display_(/*80.85*/routes/*80.91*/.HomeController.tutorial()),format.raw/*80.117*/("""">add
          your own "Hello World" greeting</a>.</p>
    </article>
    <aside>
      """),_display_(/*84.8*/commonSidebar()),format.raw/*84.23*/("""
    """),format.raw/*85.5*/("""</aside>
  </div>
</section>

""")))}),format.raw/*89.2*/("""
""")))}),format.raw/*90.2*/("""
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/explore.scala.html
                  HASH: 11e50c41dd14043a239e500e5d9a2e180b049c15
                  MATRIX: 724->1|820->3|850->8|877->27|916->29|944->32|991->71|1041->84|1071->88|4201->3191|4230->3198|4891->3831|4920->3832|4949->3833|5044->3901|5072->3902|6357->5160|6372->5166|6420->5192|6541->5287|6577->5302|6610->5308|6675->5343|6708->5346
                  LINES: 21->1|26->1|28->3|28->3|28->3|29->4|29->4|29->4|31->6|72->47|72->47|82->57|82->57|82->57|84->59|84->59|105->80|105->80|105->80|109->84|109->84|110->85|114->89|115->90
                  -- GENERATED --
              */
          