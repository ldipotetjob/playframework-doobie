
package views.csv

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.csv._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._
/*5.2*/import databases.model.FootballMatch

object football extends _root_.play.twirl.api.BaseScalaTemplate[databases.formats.CsvFormat.Appendable,_root_.play.twirl.api.Format[databases.formats.CsvFormat.Appendable]](databases.formats.CsvFormat) with _root_.play.twirl.api.Template1[Seq[databases.model.FootballMatch],databases.formats.CsvFormat.Appendable] {

  /**/
  def apply/*7.2*/(matches: Seq[databases.model.FootballMatch]):databases.formats.CsvFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.47*/("""league, season, audience
"""),_display_(/*8.2*/for(matchgame <- matches) yield /*8.27*/ {_display_(Seq[Any](format.raw/*8.29*/("""
"""),_display_(/*9.2*/matchgame/*9.11*/.league),format.raw/*9.18*/(""","""),_display_(/*9.20*/matchgame/*9.29*/.season),format.raw/*9.36*/(""","""),_display_(/*9.38*/matchgame/*9.47*/.audience)))}))
      }
    }
  }

  def render(matches:Seq[databases.model.FootballMatch]): databases.formats.CsvFormat.Appendable = apply(matches)

  def f:((Seq[databases.model.FootballMatch]) => databases.formats.CsvFormat.Appendable) = (matches) => apply(matches)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/football.scala.csv
                  HASH: 3283ab5fbbca6ad3060b50fade60d902e10cfc3a
                  MATRIX: 430->134|810->173|952->218|1003->244|1043->269|1082->271|1109->273|1126->282|1153->289|1181->291|1198->300|1225->307|1253->309|1270->318
                  LINES: 17->5|22->7|27->7|28->8|28->8|28->8|29->9|29->9|29->9|29->9|29->9|29->9|29->9|29->9
                  -- GENERATED --
              */
          