
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

object commonSidebar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.4*/("""
"""),_display_(/*2.2*/defining(play.core.PlayVersion.current)/*2.41*/ { version =>_display_(Seq[Any](format.raw/*2.54*/("""
"""),format.raw/*3.1*/("""<h3>Table of Contents</h3>
<ul>
  <li><a href=""""),_display_(/*5.17*/routes/*5.23*/.HomeController.index()),format.raw/*5.46*/("""#Introduction">Welcome</a>
  <li><a href=""""),_display_(/*6.17*/routes/*6.23*/.HomeController.explore()),format.raw/*6.48*/("""">Play application overview</a>
  <li><a href=""""),_display_(/*7.17*/routes/*7.23*/.HomeController.tutorial()),format.raw/*7.49*/("""">Implementing Hello World</a>
</ul>
<h3>Related Resources</h3>
<ul>
  <li><a href="https://playframework.com/documentation/"""),_display_(/*11.57*/version),format.raw/*11.64*/("""" target="_blank">Play documentation</a></li>
  <li><a href="https://discuss.lightbend.com/c/play/" target="_blank">Forum</a></li>
  <li><a href="//gitter.im/playframework/playframework" target="_blank">Gitter Channel</a></li>
  <li><a href="//stackoverflow.com/questions/tagged/playframework" target="_blank">Stackoverflow</a></li>
  <li><a href="//lightbend.com/how" target="_blank">Professional support</a></li>
</ul>
""")))}))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/commonSidebar.scala.html
                  HASH: 14d6acec0efa9315129bc69b708bbc2de6e0a648
                  MATRIX: 730->1|826->3|854->6|901->45|951->58|979->60|1055->110|1069->116|1112->139|1182->183|1196->189|1241->214|1316->263|1330->269|1376->295|1532->424|1560->431
                  LINES: 21->1|26->1|27->2|27->2|27->2|28->3|30->5|30->5|30->5|31->6|31->6|31->6|32->7|32->7|32->7|36->11|36->11
                  -- GENERATED --
              */
          