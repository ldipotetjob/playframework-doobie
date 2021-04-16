
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

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template is called from the `index` template. This template
 * handles the rendering of the page header and body tags. It takes
 * two arguments, a `String` for the title of the page and an `Html`
 * object to insert into the body of the page.
 */
  def apply/*7.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.32*/("""

"""),format.raw/*9.1*/("""<!DOCTYPE html>
<html lang="en">

<head>
    <title>"""),_display_(/*13.13*/title),format.raw/*13.18*/("""</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" media="screen" href='"""),_display_(/*15.50*/routes/*15.56*/.Assets.versioned("stylesheets/main.css")),format.raw/*15.97*/("""'>
    <link rel="stylesheet" media="screen" href='"""),_display_(/*16.50*/routes/*16.56*/.Assets.versioned("stylesheets/prism.css")),format.raw/*16.98*/("""'>
    <link rel="shortcut icon" type="image/png" href='"""),_display_(/*17.55*/routes/*17.61*/.Assets.versioned("images/favicon.png")),format.raw/*17.100*/("""'>
    <script src='"""),_display_(/*18.19*/routes/*18.25*/.Assets.versioned("javascripts/hello.js")),format.raw/*18.66*/("""' type="text/javascript"></script>
    <script src='"""),_display_(/*19.19*/routes/*19.25*/.Assets.versioned("javascripts/prism.js")),format.raw/*19.66*/("""' type="text/javascript"></script>
</head>

<body>
    <section id="top">
        <div class="wrapper">
            <img class="resize" src="assets/images/play_icon_reverse.svg" alt="logo" />
            <h1>Play Hello World Web Tutorial</h1>
        </div>
    </section>
    """),_display_(/*29.6*/content),format.raw/*29.13*/("""
"""),format.raw/*30.1*/("""</body>

</html>"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/main.scala.html
                  HASH: be49df457fee414c7efc8e689a2e3033a3af8ec7
                  MATRIX: 992->266|1117->296|1147->300|1231->357|1257->362|1419->497|1434->503|1496->544|1576->597|1591->603|1654->645|1739->703|1754->709|1815->748|1864->770|1879->776|1941->817|2022->871|2037->877|2099->918|2413->1206|2441->1213|2470->1215
                  LINES: 26->7|31->7|33->9|37->13|37->13|39->15|39->15|39->15|40->16|40->16|40->16|41->17|41->17|41->17|42->18|42->18|42->18|43->19|43->19|43->19|53->29|53->29|54->30
                  -- GENERATED --
              */
          